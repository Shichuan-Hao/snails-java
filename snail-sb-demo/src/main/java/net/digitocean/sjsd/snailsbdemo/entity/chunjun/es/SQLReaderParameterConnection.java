package net.digitocean.sjsd.snailsbdemo.entity.chunjun.es;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SQLReaderParameterConnection {

    /**
     * 描述：针对关系型数据库的 jdbc 连接字符串
     */
    private String jdbcUrl;

    /**
     * 描述：表
     */
    private List<String> table;


    /**
     * 描述：数据库schema名
     */
    private String schema;
}
