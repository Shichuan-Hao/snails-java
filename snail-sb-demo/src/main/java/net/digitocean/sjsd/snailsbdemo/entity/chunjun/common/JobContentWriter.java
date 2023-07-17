package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobContentWriter {

    /**
     * 描述：write 插件名。
     * 当前支持的关系数据库插件包括：
     * - mysql:mysqlreader | oracle:oraclereader
     * - mongoDB:mysqlreader | elasticsearch:esreader
     * - hive:hdfsreader
     * - hbase:hbasereader
     */
    @NotNull(message = "reader name 不能为空")
    private String name;

    /**
     * 描述：数据源配置参数
     */
    @NotEmpty(message = "数据源配置参数不能为空")
    private JobContentWriterParameter parameter;
}
