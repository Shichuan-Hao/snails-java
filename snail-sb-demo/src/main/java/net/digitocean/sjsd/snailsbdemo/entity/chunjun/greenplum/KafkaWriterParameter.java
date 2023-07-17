package net.digitocean.sjsd.snailsbdemo.entity.chunjun.greenplum;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentWriterParameter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author: haoshichuan
 * @date: 2023/7/14 11:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaWriterParameter extends JobContentWriterParameter {


    /**
     * 描述：消息发送至 kafka 的 topic 名称，不支持多个 topic
     * 必填项：是
     */
    @NotNull
    private String topic;

    /**
     * 描述：字段映射配置。从 reader 插件传递到 writer 插件的的数据只包含其 value 属性，
     * 配置该参数后可将其还原成键值对类型json字符串输出。
     *
     * 注意：
     * 若配置该属性，则该配置中的字段个数必须不少于reader插件中读取的字段个数，否则该配置失效；
     * 映射关系按该配置中字段的先后顺序依次匹配；
     *
     * 必选：否
     */
    private List<String> tableFields;

    /**
     * 描述：kafka连接配置，支持所有 kafka.consumer.ProducerConfig.ProducerConfig 中定义的配置
     * 必填项：是
     */
    private Map<String, String> producerSettings;

}
