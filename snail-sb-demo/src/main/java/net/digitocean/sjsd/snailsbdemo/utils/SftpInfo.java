package net.digitocean.sjsd.snailsbdemo.utils;

import lombok.Data;

/**
 * @author: haoshichuan
 * @date: 2023/7/12 15:32
 */
@Data
public class SftpInfo {
    private String host;
    private int port = 22;
    private String username;
    private String password;

    private int timeout;
}
