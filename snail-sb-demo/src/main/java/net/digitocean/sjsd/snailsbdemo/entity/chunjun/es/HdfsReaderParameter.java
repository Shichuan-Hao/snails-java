package net.digitocean.sjsd.snailsbdemo.entity.chunjun.es;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentReaderParameter;

import java.util.Map;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HdfsReaderParameter extends JobContentReaderParameter {

    /**
     * 描述：读取的数据文件路径 path+filename <br/>
     * 必选：是 <br/>
     * 默认值：无 <br/>
     */
    private String path;

    /**
     * 描述：数据文件目录名称 <br/>
     * 必选：否 <br/>
     * 默认值：无 <br/>
     * 注意：不为空，则 hdfs 读取的路径为 path+filename <br/>
     */
    private String fileName;

    /**
     * 描述：文件的类型，目前只支持用户配置为 text、orc、parquet <br/>
     * - text：text file 文件格式 <br/>
     * - orc：orc file 文件格式 <br/>
     * - parquet：parquet文件格式 <br/>
     *
     * 必选：是 <br/>
     * 默认值：text <br/>
     */
    private String fileType;

    /**
     * 描述：Hadoop hdfs文件系统 namenode 节点地址 <br/>
     * - 格式：hdfs://ip:端口； <br/>
     * - 例如：hdfs://127.0.0.1:9000 <br/>
     *
     * 必选：是 <br/>
     * 默认值：text <br/>
     */
    private String defaultFS;

    /**
     * 描述：集群 HA 模式时需要填写的 core-site.xml 及 hdfs-site.xml 中的配置，开启 kerberos 时包含 kerberos 相关配置 <br/>
     * 必选：否 <br/>
     * 默认值：无 <br/>
     */
    private Map<String, String> hadoopConfig;



}
