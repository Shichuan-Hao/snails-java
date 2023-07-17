package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author: haoshichuan
 * @date: 2023/7/14 13:35
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobContentParameterColumn {


    /**
     * 描述：字段名称
     * 必选：是
     * 默认值：无
     */
    private String name;


    /**
     * 描述：字段类型，需要和数据文件中实际的字段类型匹配
     * 必选：是
     * 默认值：无
     */
    private String type;


    /**
     * 描述：常量字段，将 value 的值作为常量列返回
     * 必选：否
     * 默认值：无
     */
    private String value;

    /**
     * 描述：字段在所有字段中的位置索引，从 0 开始计算，按照数组顺序依次读取，配置后读取指定字段列
     * 必选：否
     * 默认值：-1
     */
    private Integer index;

    /**
     * 描述：是否是分区字段，如果是分区字段，会自动从path上截取分区赋值，默认为fale
     * 必选：否
     * 默认值：false
     */
    private Boolean isPart;

    /**
     * 描述：按照指定格式，格式化日期
     * 必选：否
     * 默认值：无
     */
    private String format;


    /**
     * 描述：因为 MongoDB 支持数组类型，所以 MongoDB 读出来的数组类型要通过这个分隔符合并成字符串
     * 必选：否
     * 默认值：无
     */
    private String splitter;
}
