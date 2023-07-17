package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * <h1>纯钧（chunjun） 任务脚本配置抽象</h1>
 *
 * <p>
 *     一个完整的纯钧任务脚本配置包含 content setting 两个部分。
 *     content 用于配置任务的输入源与输出源，其中包含 reader、writer
 *     setting 配置任务整体幻想设定，其中包含 speed、errorLimit、metricPluginConf、restore、log、dirty
 * </p>
 *
 * @author: haoshichuan
 * @date: 2023/7/13 11:21
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Job {

    /**
     * 配置任务的输入源与输出源
     * 包含 reader，writer
     */
    List<JobContent> content;

    /**
     * 配置任务整体的环境设定，
     * 包含:
     * - speed
     * - errorLimit
     * - metricPluginConf
     * - restore
     * - log
     * - dirty
     */
    JobSetting setting;

}
