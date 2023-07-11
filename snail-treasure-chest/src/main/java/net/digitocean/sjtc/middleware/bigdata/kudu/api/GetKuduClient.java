package net.digitocean.sjtc.middleware.bigdata.kudu.api;

import org.apache.hadoop.security.UserGroupInformation;
import org.apache.kudu.client.KuduClient;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;

/**
 * @author: haoshichuan
 * @date: 2023/7/7 9:08
 */
public class GetKuduClient {
    private static final String KUDU_MASTERS = System.getProperty("kuduMasters",
            "192.168.8.104:7051");

    public static KuduClient getKuduClient() {
        KuduClient client = null;
        try {
            client = UserGroupInformation.getLoginUser().doAs(
                    (PrivilegedExceptionAction<KuduClient>) () -> new KuduClient
                            .KuduClientBuilder(KUDU_MASTERS)
                            .build());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return client;
    }
}