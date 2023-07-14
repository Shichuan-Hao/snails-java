package net.digitocean.sjsd.snailsbdemo.entity.chunjun.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SQLReaderParameterConnection {

    /**
     * 描述：针对关系型数据库的 jdbc 连接字符串
     */
    private List<String> jdbcUrl;

    /**
     * 描述：表
     */
    private List<String> table;


    /**
     * 描述：这个是干啥的还不清楚
     */
    private String schema;
}
