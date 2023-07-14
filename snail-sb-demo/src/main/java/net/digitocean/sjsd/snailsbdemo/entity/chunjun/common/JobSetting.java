package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置任务整体的环境设定，
 * 包含 speed，errorLimit，metricPluginConf，restore，log
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSetting {

    /**
     * 任务并发数及速率限制
     */
    private JobSettingSpeed speed;
}
