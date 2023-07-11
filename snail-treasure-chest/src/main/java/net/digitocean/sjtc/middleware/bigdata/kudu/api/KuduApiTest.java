package net.digitocean.sjtc.middleware.bigdata.kudu.api;

import org.apache.kudu.ColumnSchema;
import org.apache.kudu.Schema;
import org.apache.kudu.Type;
import org.apache.kudu.client.CreateTableOptions;
import org.apache.kudu.client.Insert;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduScanner;
import org.apache.kudu.client.KuduSession;
import org.apache.kudu.client.KuduTable;
import org.apache.kudu.client.ListTablesResponse;
import org.apache.kudu.client.PartialRow;
import org.apache.kudu.client.RowResult;
import org.apache.kudu.client.RowResultIterator;
import org.apache.kudu.client.SessionConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: haoshichuan
 * @date: 2023/7/7 9:12
 */
public class KuduApiTest {

    /**
     * 获取 kudu 表里面的数据
     */
    public static void getTableData(KuduClient client, String database, String table, String columns) {
        try {
            KuduTable kudutable = client.openTable(table);
            KuduScanner kuduScanner = client.newScannerBuilder(kudutable).build();
            while (kuduScanner.hasMoreRows()) {
                RowResultIterator rowResultIterator = kuduScanner.nextRows();
                while (rowResultIterator.hasNext()) {
                    RowResult rowResult = rowResultIterator.next();
                    System.out.println(rowResult.getString(columns));
                }
            }
            try {
                client.close();
            } catch (KuduException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向kudu表里面插入数据
     */

    public static void upsertTableData(KuduClient client, String tableName, int numRows) {
        try {
            KuduTable kuduTable = client.openTable(tableName);
            KuduSession kuduSession = client.newSession();
            //设置Kudu提交数据方式，这里设置的为手动刷新，默认为自动提交
            kuduSession.setFlushMode(SessionConfiguration.FlushMode.MANUAL_FLUSH);
            for (int i = 0; i < numRows; i++) {
                String userInfo_str = "abcdef,ghigk";
                Insert upsert = kuduTable.newInsert();
                PartialRow row = upsert.getRow();
                String[] userInfo = userInfo_str.split(",");
                if (userInfo.length == 2) {
                    row.addString("id", userInfo[0]);
                    row.addString("name", userInfo[1]);
                }
                kuduSession.apply(upsert);
            }
            kuduSession.flush();
            kuduSession.close();
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个kudu 表
     */
    public static void createTableData(KuduClient client, String tableName) {
        List<ColumnSchema> columns = new ArrayList<>();
        //在添加列时可以指定每一列的压缩格式
        columns.add(new ColumnSchema.ColumnSchemaBuilder("id", Type.STRING).key(true).
                compressionAlgorithm(ColumnSchema.CompressionAlgorithm.SNAPPY).build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("name", Type.STRING).
                compressionAlgorithm(ColumnSchema.CompressionAlgorithm.SNAPPY).build());
        //Schema schema = new Schema(columns);
        Schema schema = new Schema(columns);
        CreateTableOptions createTableOptions = new CreateTableOptions();
        List<String> hashKeys = new ArrayList<>();
        hashKeys.add("id");
        int numBuckets = 8;
        createTableOptions.addHashPartitions(hashKeys, numBuckets);

        try {
            if (!client.tableExists(tableName)) {
                client.createTable(tableName, schema, createTableOptions);
            }
            System.out.println("成功创建Kudu表：" + tableName);
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出Kudu下所有的表
     *
     * @param client the client of kudu
     */
    public static void tableListShow(KuduClient client) {
        try {
            ListTablesResponse listTablesResponse = client.getTablesList();
            List<String> tblist = listTablesResponse.getTablesList();
            for (String tableName : tblist) {
                System.out.println(tableName);
            }
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表
     */
    public static void dropTableData(KuduClient client, String tableName) {
        try {
            client.deleteTable(tableName);
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }
}
