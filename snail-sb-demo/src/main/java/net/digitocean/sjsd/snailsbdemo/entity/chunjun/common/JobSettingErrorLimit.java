package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 配置纯钧（chunjun）任务运行时数据读取写入的出错控制
 *
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobSettingErrorLimit {

    /**
     * 错误阈值（非必填）
     * 当错误记录数超过此阈值时任务失败
     * 任务并发数（非必填）
     * 默认值：1
     */
    private Integer record;

    /**
     * 错误比例阈值（非必填）
     * 当错误记录比例超过此阈值时任务失败
     * 默认值：0.0
     */
    private Double percentage;

}
