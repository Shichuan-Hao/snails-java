package net.digitocean.sjtc.middleware.bigdata.kudu.impalajdbc;

import net.digitocean.sjtc.middleware.bigdata.kudu.api.KuduKerberosAuth;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: haoshichuan
 * @date: 2023/7/7 9:19
 */
public class GetImpalaClient {
    //驱动
    private static final String DRIVER = "com.cloudera.impala.jdbc41.Driver";
    //LDAP 认证
    private static final String LDAP_URL = "jdbc:impala://cdh-master03:25004/default;AuthMech=3;SSL=1;SSLTrustStore=/lvm/data3/zhc/cm-auto-global_truststore.jks";
    private static final String USER = "gree1";
    private static final String PASSWORD = "000000";
    //kerberos 认证
    private static final String KERBEROS_URL = "jdbc:impala://cdh-master03:25004/default;AuthMech=1;KrbRealm=GREE.IO;KrbHostFQDN=cdh-master03;KrbServiceName=impala;SSL=1;SSLTrustStore=D:/cdh/kudu/src/main/ssl/cm-auto-global_truststore.jks";

    //LADP认证
    public static Connection getKuduClientLDAP() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(LDAP_URL, USER, PASSWORD);
        System.out.println("这是LDAP认证");
        return connection;
    }

    //kerberos认证
    public static Connection getKuduClientKerberos() throws IOException {
        //kerberos 认证
        //KuduKerberosAuth.initKerberosENV(false);
        //KuduKerberosAuth.initKerberosEnv(false);
        KuduKerberosAuth.initKerberosENV(false);
        Connection client = null;
        try {
            client = (Connection) UserGroupInformation.getLoginUser().doAs(
                    new PrivilegedExceptionAction<Object>() {
                        @Override
                        public Object run() throws Exception {
                            Class.forName(DRIVER);
                            return   DriverManager.getConnection(KERBEROS_URL);
                        }
                    }
            );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是KERBEROS认证");
        return client;
    }
}