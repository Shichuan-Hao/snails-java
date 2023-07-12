package net.digitocean.sjsd.snailsbdemo.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author: haoshichuan
 * @date: 2023/7/12 11:58
 */
@Slf4j
public class SFTPChannel {

    private Session session = null;
    private Channel channel = null;

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final int timeout;

    public SFTPChannel(SftpInfo sftpInfo) {
        this.host = sftpInfo.getHost();
        this.port = sftpInfo.getPort();
        this.username = sftpInfo.getUsername();
        this.password = sftpInfo.getPassword();
        this.timeout = sftpInfo.getTimeout();
    }

    public void getSession() {
        log.info("the sftp info. host:{}, port:{}, username:{}, password:{}", host, port, username, password);
        // 创建 JSch 对象
        JSch jSch = new JSch();
        try {
            // 根据用户名，主机 ip，端口获取一个 Session 对象
            session = jSch.getSession(username, host, port);
            log.debug("Sftp Session 创建成功...");
            if (password != null) {
                // 设置密码
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            // 为 Session 对象设置 properties
            session.setConfig(config);
            // 设置 timeout 超时时间
            session.setTimeout(timeout);
            // 通过 Session 建立连接
            session.connect();
            log.debug("Sftp Session connected.");
            log.debug("Sftp Session 连接成功...详细信息: ftpHost:{}, as ftpUserName:{} and ftpPort:{}, returning: {}",
                    host, username, port, session);
        } catch (JSchException e) {
            log.error("获取 Sftp Session 信息失败，失败信息：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行 shell 命令
     *
     * @param command shell 命令
     * @return 返回执行 shell 命令的结果
     */
    public void execShell(String command) {
        try {
            BufferedReader reader = null;
            channel = getExecChannel();
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            // 建立SFTP通道的连接
            channel.connect();
            InputStream inputStream = channel.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                System.out.println(buf);
            }
        } catch (JSchException | IOException e) {
            log.info("执行命令失败，失败信息：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @param src  源文件绝对路径
     * @param dist 目标文件绝对路径
     */
    public void uploadFile(String src, String dist) {
        try {
            ChannelSftp channelSftp = null;
            FileInputStream fileInputStream = null;
            channelSftp = getSFTPChannel();
            fileInputStream = new FileInputStream(src);
            channelSftp.put(fileInputStream, dist, ChannelSftp.OVERWRITE);
        } catch (FileNotFoundException | SftpException e) {
            log.error("上传文件失败，失败信息：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建 ChannelExec
     *
     * @return 返回 ChannelExec
     */
    private ChannelExec getExecChannel() {
        if (session == null) {
            getSession();
        }
        // 打开 SFTP 通道
        try {
            channel = session.openChannel("exec");
            log.debug("打开 ChannelExec 通道成功...returning: {}", channel);
            return (ChannelExec) channel;
        } catch (JSchException e) {
            log.error("打开 ChannelExec 通道失败，失败信息：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建 ChannelSftp
     *
     * @return 返回 ChannelExec
     */
    private ChannelSftp getSFTPChannel() {
        if (session == null) {
            getSession();
        }
        // 打开 SFTP 通道
        try {
            channel = session.openChannel("sftp");
            log.debug("打开 ChannelSftp 通道成功...returning: {}", channel);
            return (ChannelSftp) channel;
        } catch (JSchException e) {
            log.error("打开 ChannelSftp 通道失败，失败信息：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void closeChannel() {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
    // 实现文件上传调用 ChannelSftp 对象的 put 方法
    // put()：文件上传
    // get()： 文件下载
    // cd()： 进入指定目录
    // ls(): 得到指定目录下的文件列表
    // rename(): 重命名指定文件或目录
    // rm(): 删除指定文件
    // mkdir(): 创建目录
    // rmdir(): 删除目录
}
