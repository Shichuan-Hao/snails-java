package net.digitocean.sjsd.snailsbdemo.entity.chunjun.es;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentReaderParameter;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 15:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HbaseReaderParameterRange extends JobContentReaderParameter {

    /**
     * 描述：指定开始 rowkey<br/>
     * 必选：否 <br/>
     * 默认值：无
     */
    private String startRowkey;


    /**
     * 描述：指定结束 rowkey <br/>
     * 必选：否 <br/>
     * 默认值：无 <br/>
     */
    private String endRowKey;


    /**
     * 描述：指定配置的 startRowkey 和 endRowkey 转换为 byte[] 时的方式 <br/>
     * 必选：否 <br/>
     * 默认值：false <br/>
     */
    private Boolean isBinaryRowkey;
}
