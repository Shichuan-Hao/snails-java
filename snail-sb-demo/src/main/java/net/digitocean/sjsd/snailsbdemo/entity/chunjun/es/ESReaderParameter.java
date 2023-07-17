package net.digitocean.sjsd.snailsbdemo.entity.chunjun.es;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentParameterColumn;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentReaderParameter;

import java.util.List;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 15:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ESReaderParameter extends JobContentReaderParameter {

    /**
     * 描述：Elasticsearch地址，单个节点地址采用 host:port 形式，多个节点的地址用逗号连接<br/>
     * 必选：是<br/>
     * 默认值：无<br/>
     */
    private String address;

    /**
     * 描述：要查询的索引名称<br/>
     * 必选：否<br/>
     * 默认值：无<br/>
     */
    private String index;

    /**
     * 描述：要查询的类型<br/>
     * 必选：是<br/>
     * 默认值：无<br/>
     */
    private String type;

    /**
     * 描述：每次读取数据条数<br/>
     * 必选：否<br/>
     * 默认值：10<br/>
     */
    private Integer batchSize;

    /**
     * 描述：连接超时时间<br/>
     * 必选：否<br/>
     * 默认值：无<br/>
     */
    private Integer timeout;

    /**
     * 描述：读取 Elasticsearch 的查询结果的若干个列，每列形式如下：<br/>
     * - name：字段名称，可使用多级格式查找<br/>
     * - type：字段类型，当name没有指定时，则返回常量列，值为value指定<br/>
     * - value：常量列的值<br/>
     * 必选：是<br/>
     * 默认值：无<br/>
     */
    private List<JobContentParameterColumn> column;

}
