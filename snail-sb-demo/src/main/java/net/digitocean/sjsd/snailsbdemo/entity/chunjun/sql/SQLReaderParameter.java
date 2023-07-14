package net.digitocean.sjsd.snailsbdemo.entity.chunjun.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentReaderParameter;

import java.util.List;

/**
 * @author: haoshichuan
 * @date: 2023/7/13 11:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SQLReaderParameter extends JobContentReaderParameter {

    /**
     * 数据源的用户名（必填）
     */
    private String username;

    /**
     * 数据源指定用户名的密码（必填）
     */
    private String password;

    /**
     * 描述：数据库连接参数（必填），
     *
     * 包含 jdbcUrl、schema、table 等参数
     */
    private List<SQLReaderParameterConnection> connection;

    /**
     * 描述：自定义的查询语句（非必填）
     * - 如果只指定字段不能满足需求时，可通过此参数指定查询的 sql，可以是任意复杂的查询语句。
     * 注意：
     * - 能是查询语句，否则会导致任务失败；
     * - 查询语句返回的字段需要和 column 列表里的字段对应；
     */
    private String customSql;

    /**
     * 描述：一次性从数据库中读取多少条数据，
     *
     * MySQL 默认一次将所有结果都读取到内存中，在数据量很大时可能会造成 OOM，
     * 设置这个参数可以控制每次读取 fetchSize 条数据，而不是默认的把所有数据一次读取出来；
     * 开启 fetchSize 需要满足：数据库版本要高于 5.0.2、连接参数 useCursorFetch=true。
     *
     * 注意：此参数的值不可设置过大，否则会读取超时，导致任务失败。
     */
    private Integer fetchSize = 1024;

    /**
     * 描述：筛选条件（非必填）
     * reader 插件根据指定的 column、table、where 条件拼接 SQL，并根据这个 SQL 进行数据抽取。
     * 在实际业务场景中，往往会选择当天的数据进行同步，可以将 where 条件指定为 gmt_create > time
     *
     * 注意：不可以将 where 条件指定为 limit 10，limit 不是 SQL 的合法 where 子句
     */
    private String where;

    /**
     * 描述：当 speed 配置中的 channel 大于 1 时指定此参数，Reader 插件根据并发数和此参数指
     * 定的字段拼接 sql，使每个并发读取不同的数据，提升读取速率（非必填）。
     *
     * 注意：
     * - 推荐 splitPk 使用表主键，因为表主键通常情况下比较均匀，因此切分出来的分片也不容易出现数据热点。
     * - 目前 splitPk 仅支持整形数据切分，不支持浮点、字符串、日期等其他类型。如果用户指定其他非支持类型，ChunJun 将报错。
     * - 如果 channel 大于 1 但是没有配置此参数，任务将置为失败。
     */
    private String splitPk;

    /**
     * 描述：增量字段（非必填），
     *
     * 可以是对应的增量字段名，也可以是纯数字，表示增量字段在 column 中的顺序位置（从 0 开始）
     */
    private String increColumn;

    /**
     * 描述: 增量查询起始位置
     */
    private String startLocation;

    /**
     * 描述：是否开启间隔轮询（非必填）
     * 开启后会根据 pollingInterval 轮询间隔时间周期性的从数据库拉取数据。
     * 开启间隔轮询还需配置参数：
     * - pollingInterval
     * - increColumn
     * - startLocation（可选）
     * -- 若不配置此参数 startLocation，任务启动时将会从数据库中查询增量字段最大值作为轮询的开始位置。
     */
    private Boolean polling;

    /**
     * 描述：轮询间隔时间，从数据库中拉取数据的间隔时间 （非必填）
     * 默认：5000 毫秒。
     */
    private Integer pollingInterval = 5000;

    /**
     * 描述：查询超时时间，单位秒（非必填）。
     * 注意：当数据量很大，或者从视图查询，或者自定义 sql 查询时，可通过此参数指定超时时间。
     * 默认值：1000
     */
    private Integer queryTimeOut = 1000;

    /**
     * 描述：用于标记是否保存 endLocation 位置的一条或多条数据
     * true：不保存，false(默认)：保存， 某些情况下可能出现最后几条数据被重复记录的情况，可以将此参数配置为 true
     */
    private Boolean userMaxFunc = Boolean.FALSE;

    /**
     * 发送查询累加器请求的间隔时间 （非必填）
     * 默认值：2
     */
    private Integer requestAccumulatorInterval = 2;


    /**
     * 分片记录数
     * 可以理解并发
     */
    private List<String> sliceRecordCount;
}
