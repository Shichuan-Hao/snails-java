package net.digitocean.sjtc.middleware.bigdata.kudu.api;

import org.apache.hadoop.security.UserGroupInformation;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduScanner;
import org.apache.kudu.client.KuduTable;
import org.apache.kudu.client.RowResult;
import org.apache.kudu.client.RowResultIterator;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;

/**
 * @author: haoshichuan
 * @date: 2023/7/7 9:18
 */
public class KuduClientTest {

    private static final String KUDU_MASTERS = System.getProperty("kuduMasters", "cdh-master01:7051,cdh-master02:7051,cdh-master03:7051");

    //    private static final String KUDU_MASTERS = System.getProperty("kuduMasters", "sns-cdh-namenode2:7051,sns-cdh-namenode1:7051,sns-cdh-datanode1:7051");
    /**
     * 获取kudu表里面的数据
     * */
    static void getTableData(){

        System.out.println("-----------------------------------------------");
        System.out.println("Will try to connect to Kudu master(s) at " + KUDU_MASTERS);
        System.out.println("-----------------------------------------------");
        try {
            KuduClient client = UserGroupInformation.getLoginUser().doAs(
                    new PrivilegedExceptionAction<KuduClient>() {
                        @Override
                        public KuduClient run() throws Exception {
                            return new KuduClient.KuduClientBuilder(KUDU_MASTERS).build();
                        }
                    }
            );
            KuduTable table = client.openTable("impala::kudutest.kudu_table");
            KuduScanner kuduScanner = client.newScannerBuilder(table).build();
            while (kuduScanner.hasMoreRows()) {
                RowResultIterator rowResultIterator = kuduScanner.nextRows();
                while (rowResultIterator.hasNext()) {
                    RowResult rowResult = rowResultIterator.next();
                    System.out.println(rowResult.getString("name"));
                }
            }
            try {
                client.close();
            } catch (KuduException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
