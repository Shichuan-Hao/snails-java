package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class JobContentReaderParameter {

    /**
     * 描述：数据源的用户名<br/>
     * 必选：否<br/>
     * 默认值：无<br/>
     */
    private String username;

    /**
     * 描述：数据源指定用户名的密码<br/>
     * 必选：否<br/>
     * 默认值：无<br/>
     */
    private String password;

    /**
     * 描述：字符编码<br/>
     * 必选：是<br/>
     * 默认值: UTF-8<br/>
     */
    private String encoding;

    /**
     * 描述：需要读取的字段 <br/>
     * 必选：是 <br/>
     * 默认值：无 <br/>
     */
    private List<JobContentParameterColumn> column;
}
