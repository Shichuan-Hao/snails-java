package net.digitocean.sjtc.middleware.bigdata.kudu.api;


import org.apache.hadoop.security.UserGroupInformation;

/**
 * kudu kerberos auth
 *
 * @author: haoshichuan
 * @date: 2023/7/6 17:53
 */
public class KuduKerberosAuth {

    /**
     * 初始化访问Kerberos访问
     *
     * @param debug 是否启用Kerberos的Debug模式
     */
    public static void initKerberosENV(Boolean debug) {
        try {
            System.setProperty("java.security.krb5.conf", "C:\\Users\\Administrator\\Desktop\\Kudu\\krb5.conf");
            System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
            if (debug) {
                System.setProperty("sun.security.krb5.debug", "true");
            }
            UserGroupInformation.loginUserFromKeytab("pdTenant@AIWEN.COM", "C:\\Users\\Administrator\\Desktop\\Kudu\\pdTenant.keytab");
            System.out.println(UserGroupInformation.getCurrentUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
