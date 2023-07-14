package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobContent {
    /**
     * 用于配置数据的输入源，即数据从何而来
     */
    private JobContentReader reader;

    /**
     * 用于配置数据的输出源，即数据往何处去
     */
    private JobContentWriter writer;
}
