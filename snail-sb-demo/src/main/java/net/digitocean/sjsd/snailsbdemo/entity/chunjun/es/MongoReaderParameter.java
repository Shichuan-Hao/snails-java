package net.digitocean.sjsd.snailsbdemo.entity.chunjun.es;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentReaderParameter;

/**
 * @author: haoshichuan
 * @date: 2023/7/14 13:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MongoReaderParameter extends JobContentReaderParameter {

    /**
     * 描述：MongoDB数据库连接的URL字符串，详细请参考 MongoDB 官方文档<br/>
     * 必选：<br/>
     * 默认值：<br/>
     */
    private String url;

    /**
     * 描述：MongoDB 的地址和端口，格式为 IP1:port，可填写多个地址，以英文逗号分隔<br/>
     * 必选：否<br/>
     * 默认值：无<br/>
     */
    private String hostPorts;

    /**
     * 描述：数据库名称<br/>
     * 必选：否<br/>
     * 默认值：无<br/>
     */
    private String database;

    /**
     * 描述：集合名称<br/>
     * 必选：是<br/>
     * 默认值：无<br/>
     */
    private String collectionName;

    /**
     * 描述：每次读取的数据条数，通过调整此参数来优化读取速率<br/>
     * 必选：否<br/>
     * 默认值：100<br/>
     */
    private Integer fetchSize = 100;

    /**
     * 描述: 过滤条件，通过该配置型来限制返回 MongoDB 数据范围，语法请参考 MongoDB 查询语法<br/>
     * 必选：否<br/>
     * 默认值：无<br/>
     */
    private String filter;

}
