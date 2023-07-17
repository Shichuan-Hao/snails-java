package net.digitocean.sjsd.snailsbdemo.entity.chunjun.es;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentReaderParameter;

import java.util.List;
import java.util.Map;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 15:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HbaseReaderParameter extends JobContentReaderParameter {

    /**
     *  描述：hbase的连接配置，以json的形式组织 (见hbase-site.xml)，开启 kerberos 的话参考<br/>
     *   <a href="https://alidocs.dingtalk.com/i/nodes/14lgGw3P8vLQKKvPF2OZaMxDV5daZ90D">文档</a><br/>
     *  必选：是<br/>
     *  默认值：无<br/>
     */
    private Map<String, String> hbaseConfig;

    /**
     * 描述：hbase 表名<br/>
     * 必选：是<br/>
     * 默认值：无<br/>
     */
    private String table;

    /**
     * 描述：指定 hbasereader 读取的 rowkey 范围<br/>
     * - startRowkey：指定开始 rowkey；<br/>
     * - endRowkey：指定结束 rowkey；<br/>
     * - isBinaryRowkey：指定配置的 startRowkey 和 endRowkey 转换为 byte[] 时的方式，默认值为 false, <br/>
     * --  若为 true，则调用 Bytes.toBytesBinary(rowkey) <br/>
     * --  若为 false：则调用 Bytes.toBytes(rowkey) <br/>
     *
     * 如果用户配置了 startRowkey 和 endRowkey，需要确保：startRowkey <= endRowkey<br/>
     *
     * 必选：否 <br/>
     * 默认值：无 <br/>
     */
    private List<HbaseReaderParameterRange> range;

    /**
     * 描述：一次RPC请求批量读取的 Results 数量 <br/>
     * 必选：无  <br/>
     * 默认值：256 <br/>
     */
    private Integer scanCacheSize;

    /**
     * 描述：每一个 result 中的列的数量 <br/>
     * 必选：无 <br/>
     * 默认值：100 <br/>
     */
    private Integer scanBatchSize;
}
