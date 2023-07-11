package net.digitocean.sjtc.middleware.bigdata.kudu.api;

import org.apache.kudu.client.KuduClient;

/**
 * the entry
 *
 * @author: haoshichuan
 * @date: 2023/7/7 9:10
 */
public class KuduApiMain {
    public static void main(String[] args) {
        /*
         * 通过kerberos 认证
         * */
        //KuduKerberosAuth.initKerberosEnv(false);
        KuduKerberosAuth.initKerberosENV(false);

        /*
         * 获取kudu客户端
         * */
        KuduClient client = GetKuduClient.getKuduClient();

        /*
         * 查询表中字段
         * */
        KuduApiTest.getTableData(client, "kudutest", "zhckudutest1", "id");

        /*
         * 创建一个表名
         * */
        //KuduApiTest.createTableData(client, "zhckudutest1");

        /*
         *列出kudu下的所有表
         * */
        //KuduApiTest.tableListShow(client);

        /*
         * 向指定的kudu表中upsert数据
         * */
        //KuduApiTest.upsertTableData(client, "zhckudutest1", 10);

        /*
         * 删除kudu表
         * */
        //KuduApiTest.dropTableData(client, "zhckudutest");
    }
}
