package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

/**
 * 配置纯钧（chunjun）指标相关信息。
 * <p>
 * 目前只应用于 Jdbc 插件中，在作业结束时将 StartLocation 和 EndLocation 指标发送到指定数据源中。
 * 目前支持 Prometheus 和 Mysql。具体配置如下所示：
 *
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobSettingMetricPluginConf {
    private String pluginName;

    private Map<String, String> pluginProp;

}
