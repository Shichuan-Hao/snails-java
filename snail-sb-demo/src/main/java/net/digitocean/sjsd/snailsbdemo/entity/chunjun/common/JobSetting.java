package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 配置任务整体的环境设定，
 * 包含 speed，errorLimit，metricPluginConf，restore，log
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobSetting {

    private JobSettingErrorLimit errorLimit;
    private JobSettingLog log;
    private JobSettingMetricPluginConf metricPluginConf;

    private JobSettingRestore restore;

    /**
     * 任务并发数及速率限制
     */
    private JobSettingSpeed speed;

}
