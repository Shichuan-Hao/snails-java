package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
