package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置纯钧（chunjun）中定义的插件日志的保存与记录
 *
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSettingLog {

    /**
     * 是否保存日志记录（非必填）
     * 默认值：false
     */
    private Boolean isLogger =  Boolean.FALSE;

    /**
     * 日志级别（非必填）
     * 默认值：info
     */
    private String level;

    /**
     * 服务器上日志保存路径（非必填）
     * 默认值：无
     * 如：/tmp/dtstack/chunjun/
     */
    private String path;

    /**
     * 日志输出格式
     * 默认值:
     *    log4j：%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{60} %X{sourceThread} - %msg%n
     *  logback: %d{yyyy-MM-dd HH:mm:ss,SSS} %-5p %-60c %x - %m%n
     */
    private String pattern;


}
