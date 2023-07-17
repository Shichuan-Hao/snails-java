package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author: haoshichuan
 * @date: 2023/7/15 10:23
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Root {

    private Job job;
}
