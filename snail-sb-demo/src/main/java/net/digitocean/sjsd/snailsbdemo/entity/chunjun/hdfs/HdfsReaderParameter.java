package net.digitocean.sjsd.snailsbdemo.entity.chunjun.hdfs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentReaderParameter;

import java.util.Map;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HdfsReaderParameter extends JobContentReaderParameter {

    /**
     * 描述：
     * 必选：
     * 默认值：
     */
    private String path;

    /**
     *
     */
    private Map<String, String> hadoopConfig;

    /**
     *
     */
    private String defaultFS;

    /**
     *
     */
    private String encoding;

    /**
     *
     */
    private String fileType;

}
