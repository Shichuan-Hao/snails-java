package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 配置纯钧（chunjun）任务并发数以及速率限制
 *
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobSettingSpeed {

    /**
     * 任务并发数（非必填）
     * 默认值：1
     */
    private Integer channel;

    /**
     * source 并发度（非必填）
     * 默认值：-1，代表采用全局并行度
     */
    private Integer readerChannel;

    /**
     * sink 并行度（非必填）
     * 默认值：-1，代表采用全局并发度
     */
    private Integer writerChannel;

    /**
     * 任务速度（非必填）？
     * 默认值: 0
     * byte > 0 表示开启任务限速
     */
    private Long bytes;

    /**
     * 是否强制进行 rebalance（非必填）
     * 默认值：false
     * 提示：开启会消化性能
     */
    private Boolean rebalance;
}
