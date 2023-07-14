package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobContentReaderParameterColumn {

    /**
     * 描述：字段名称（必填）
     */
    private String name;

    /**
     * 描述：字段类型（必填）
     * 可以和数据库里的字段类型不一样，程序会做一次类型转换
     */
    private String type;

    /**
     * 描述：时间格式
     * 如果字段是时间字符串，可以指定时间的格式，将字段类型转为日期格式返回
     */
    private String format;

    /**
     * 描述：字段类型
     * 如果数据库里不存在指定的字段，则会把 value 的值作为常量列返回，
     * 如果指定的字段存在，当指定字段的值为 null 时，会以此 value 值作为默认值返回
     */
    private String value;
}
