package net.digitocean.sjsd.snailsbdemo.entity.chunjun.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置纯钧（chunjun）同步任务类型（离线同步、实时采集）和断点续传功能
 *
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSettingRestore {

    /**
     * 是否为实时采集任务（非必填）
     * 默认值：false
     */
    private Boolean isStream = Boolean.FALSE;

    /**
     * 是否开启断点续传（非必填）
     * 默认值：false
     */
    private Boolean isRestore  = Boolean.FALSE;;

    /**
     * 断点续传字段名称（开启断点续传后必填）
     * 默认值：无
     */
    private String restoreColumnName;

    /**
     * 断点续传字段索引 ID（开启断点续传后必填）
     * 默认值：无
     */
    private Integer restoreColumnIndex;


}
