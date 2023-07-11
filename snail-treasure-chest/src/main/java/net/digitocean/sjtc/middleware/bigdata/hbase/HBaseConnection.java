package net.digitocean.sjtc.middleware.bigdata.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: haoshichuan
 * @date: 2023/7/11 13:58
 */
@Slf4j
public final class HBaseConnection {

    private HBaseConnection() {
    }

    /**
     * 建立连接
     *
     * @return 建立连接
     */
    public static Connection getConnection() {
        // 获取配置
        Configuration configuration = getConfiguration();
        try {
            // 检查配置
            HBaseAdmin.available(configuration);
            return ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建表
     *
     * @param connection     connection
     * @param tableName      tableName
     * @param columnFamilies columnFamilies
     */
    public static void createTable(Connection connection, TableName tableName, String... columnFamilies) {
        try (Admin admin = connection.getAdmin()) {
            if (admin.tableExists(tableName)) {
                log.warn("table:{} exists!", tableName.getName());
            } else {
                TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(tableName);
                for (String columnFamily : columnFamilies) {
                    builder.setColumnFamily(ColumnFamilyDescriptorBuilder.of(columnFamily));
                }
                admin.createTable(builder.build());
                log.info("create table:{} success!", tableName.getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入数据
     *
     * @param connection
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @param data
     * @throws IOException
     */
    public static void put(Connection connection,
                           TableName tableName,
                           String rowKey,
                           String columnFamily,
                           String column,
                           String data) throws IOException {

        try (Table table = connection.getTable(tableName)) {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
            table.put(put);
        }
    }

    /**
     * 获取配置
     *
     * @return 返回 hbase 的配置
     */
    private static Configuration getConfiguration() {
        Properties props = null;

        try {
            props = PropertiesLoaderUtils.loadAllProperties("hbase.properties");
        } catch (IOException e) {
            log.error("reading the properties of hbase error, the error info is {}", props);
            throw new RuntimeException(e);
        }

        String clientPort = (String) props.get("hbase.zookeeper.property.clientPort");
        String quorum = (String) props.get("hbase.zookeeper.property.quorum");

        log.info("connect to zookeeper {}:{}", quorum, clientPort);

        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.property.clientPort", clientPort);
        config.set("hbase.zookeeper.quorum", quorum);

        return config;
    }
}
