package net.digitocean.sjsd.snailsbdemo.entity.chunjun.greenplum;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentParameterColumn;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentWriterParameter;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.es.SQLReaderParameterConnection;

import java.util.List;

/**
 * @author: haoshichuan
 * @date: 2023/7/14 11:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GreenplumWriterParameter extends JobContentWriterParameter {


    private String username;

    private String password;
    private String writeMode;

    private String mode;

    /**
     * 描述：读取 Elasticsearch 的查询结果的若干个列，每列形式如下：<br/>
     * - name：字段名称，可使用多级格式查找<br/>
     * - type：字段类型，当name没有指定时，则返回常量列，值为value指定<br/>
     * - value：常量列的值<br/>
     * 必选：是<br/>
     * 默认值：无<br/>
     */
    private List<JobContentParameterColumn> column;

    private List<SQLReaderParameterConnection> connection;



}
