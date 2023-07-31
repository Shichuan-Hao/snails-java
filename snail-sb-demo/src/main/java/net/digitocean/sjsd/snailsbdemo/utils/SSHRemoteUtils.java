package net.digitocean.sjsd.snailsbdemo.utils;

import cn.hutool.core.lang.Assert;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import lombok.extern.slf4j.Slf4j;
import net.digitocean.sjsd.snailsbdemo.entity.sftp.SSHInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author: haoshichuan
 * @date: 2023/7/15 11:40
 */
@Slf4j
public class SSHRemoteUtils {


    private Session session = null;
    private boolean isLogin = false;

    private static final String STRICT_HOST_KEY_CHECKING_KEY = "StrictHostKeyChecking";
    private static final String STRICT_HOST_KEY_CHECKING_VALUE = "no";

    private SSHRemoteUtils() {
    }

    private enum Holder {
        INSTANCE;

        private final SSHRemoteUtils instance;

        Holder() {
            this.instance = new SSHRemoteUtils();
        }

        private SSHRemoteUtils getInstance() {
            return instance;
        }
    }

    public static SSHRemoteUtils getInstance() {
        return Holder.INSTANCE.getInstance();
    }


    public void getSession(SSHInfo sshInfo) {
        if (isLogin) {
            return;
        }

        String host = sshInfo.getHost();
        Assert.notEmpty(host, "SSH 主机地址不能为空！");
        int port = sshInfo.getPort();
        Assert.isTrue(port > 0, "SSH 主机端口必选大于 0");
        String name = sshInfo.getUsername();
        Assert.notEmpty(name, "使用 SSH 的用户名不能为空！");
        log.info("获取 SSH 会话的信息...主机地址:{}, 主机端口:{}, 用户名:{}", host, port, name);

        // 创建 JSch 对象
        JSch jSch = new JSch();
        try {
            // 根据用户名，主机 ip，端口获取一个 Session 对象
            session = jSch.getSession(name, host, port);
            log.info("SSH 会话创建成功...");
            String password = sshInfo.getPassword();
            if (null != password) {
                session.setPassword(password);
            }
            // 为 Session 对象设置 properties
            Properties config = new Properties();
            config.put(STRICT_HOST_KEY_CHECKING_KEY, STRICT_HOST_KEY_CHECKING_VALUE);
            session.setConfig(config);
            // 设置超时
            session.setTimeout(sshInfo.getTimeout());
            // 通过 Session 建立连接
            session.connect();
            log.debug("SSH 会话连接成功...详细信息..主机地址：{}, 主机端口:{}，用户名:{} 以及会话信息: {}",
                    host, port, name, session);
            // 设置登陆状态
            isLogin = true;
        } catch (JSchException e) {
            log.error("获取 SSH 会话失败，失败原因：{}", e.getMessage());
            // 设置登陆状态为false
            isLogin = false;
            throw new RuntimeException(e);
        }
    }

    public void execCommand(String command) throws IOException {
        // 输入流(读)
        InputStream in = null;
        InputStream error = null;
        // 定义channel变量
        Channel channel = null;
        try {
            // 如果命令command不等于null
            if (command != null) {
                // 打开 channel 说明：exec用于执行命令 sftp用于文件处理
                channel = session.openChannel("exec");
                // 设置 command
                ((ChannelExec) channel).setCommand(command);
                // channel进行连接
                channel.connect();
                // 获取到输入流
                in = channel.getInputStream();
                error = ((ChannelExec) channel).getErrStream();
                processDataStream(in);
                processDataStream(error);
            }
        } catch (Exception e) {
            log.error("执行 [{}] 命令，失败原因：\n {}", command, e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                in.close();
            }
            if (error != null) {
                in.close();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    private void checkRemotePathExist(ChannelSftp sftp, String remoteDir) throws SftpException {
        try {
            sftp.ls(remoteDir);
            //sftp.mkdir();
        } catch (SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                sftp.mkdir(remoteDir);
            }
            log.info("由于文件目录不存在，创建远程目录：{}", remoteDir);
        }
    }

    /**
     * @param remoteDir   文件名字
     * @param uploadFile 要上传的文件
     */
    public void uploadFile(String remoteDir, String uploadFile) {
        // 定义channel变量
        ChannelSftp channel = null;
        try {
            // 打开channelSftp
            channel = (ChannelSftp) session.openChannel("sftp");
            // 远程连接
            channel.connect();
            //checkRemotePathExist(channel, remotePath);
            // 判断目录文件
            // 创建一个文件名称问 uploadFile 的文件
            File file = new File(uploadFile);
            // 将文件进行上传(sftp协议)
            // 将本地文件名为src的文件上传到目标服务器,目标文件名为dst,若dst为目录,则目标文件名将与src文件名相同.
            // 采用默认的传输模式:OVERWRITE
            channel.put(new FileInputStream(file), remoteDir, ChannelSftp.OVERWRITE);
            // 切断远程连接
            channel.exit();
            log.info("上传文件成功");
        } catch (JSchException | SftpException | FileNotFoundException e) {
            log.error("上传文件：{} 失败，失败原因：\n {}", remoteDir, e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    private void processDataStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String result = "";
            while ((result = br.readLine()) != null) {
                sb.append("\n").append(result);
                System.out.println(result);
            }
        } catch (Exception e) {
            log.error("获取数据流失败，失败原因：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void closeSession() {
        // 调用session的关闭连接的方法
        if (session != null) {
            // 如果session不为空,调用session的关闭连接的方法
            session.disconnect();
        }
    }


    public static void main(String[] args) throws IOException {
        // 1、首先远程连接ssh
        SSHInfo sshInfo = new SSHInfo();
        sshInfo.setHost("192.168.9.30");
        sshInfo.setPort(22);
        sshInfo.setUsername("root");
        sshInfo.setPassword("%j*da^szp");
        sshInfo.setTimeout(3000);
        SSHRemoteUtils.getInstance().getSession(sshInfo);

        // 2. 执行命令
        //String command1 = "ls -l";
        //String command2 = "java -version";
        //String command3 = "df -lh";
        //String command4 = "java";
        //String command5 = "cat /etc/profile";
        String command5 = "echo $FLINK_HOME";
        SSHRemoteUtils.getInstance().execCommand(command5);
        SSHRemoteUtils.getInstance().closeSession();
    }

}
