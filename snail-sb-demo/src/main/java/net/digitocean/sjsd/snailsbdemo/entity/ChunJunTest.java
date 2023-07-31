package net.digitocean.sjsd.snailsbdemo.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.Job;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContent;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentParameterColumn;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentReader;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobContentWriter;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobSetting;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.JobSettingSpeed;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.common.Root;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.es.HbaseReaderParameter;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.es.SQLReaderParameterConnection;
import net.digitocean.sjsd.snailsbdemo.entity.chunjun.greenplum.GreenplumWriterParameter;
import net.digitocean.sjsd.snailsbdemo.entity.sftp.SSHInfo;
import net.digitocean.sjsd.snailsbdemo.utils.SSHRemoteUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: haoshichuan
 * @date: 2023/7/14 18:01
 */
@Slf4j
public class ChunJunTest {

    static String TMP_DIR = "java.io.tmpdir";
    static String CONFIG_TMP_DIR = "/data/temp/chunjun/config/";

    static void hbaseToGp(String localName) {
        // hbase to greenplum
        // hbase reader
        Map<String, String> hbaseConfig = Maps.newHashMap();
        hbaseConfig.put("hbase.zookeeper.property.clientPort", "2188");
        hbaseConfig.put("hbase.rootdir", "hdfs://192.168.8.63:9000/hbase");
        hbaseConfig.put("hbase.cluster.distributed", "true");
        hbaseConfig.put("hbase.zookeeper.quorum", "192.168.8.63,192.168.8.64,192.168.8.65");
        hbaseConfig.put("zookeeper.znode.parent", "/hbase");

        List<JobContentParameterColumn> hbaseColumns = Lists.newArrayList();
        JobContentParameterColumn hbaseColumn1 = new JobContentParameterColumn();
        hbaseColumn1.setName("f1:id");
        hbaseColumn1.setType("string");
        JobContentParameterColumn hbaseColumn2 = new JobContentParameterColumn();
        hbaseColumn2.setName("f1:name");
        hbaseColumn2.setType("string");
        JobContentParameterColumn hbaseColumn3 = new JobContentParameterColumn();
        hbaseColumn3.setName("f1:age");
        hbaseColumn3.setType("string");
        hbaseColumns.add(hbaseColumn1);
        hbaseColumns.add(hbaseColumn2);
        hbaseColumns.add(hbaseColumn3);

        HbaseReaderParameter hbaseReaderParameter = new HbaseReaderParameter();
        hbaseReaderParameter.setHbaseConfig(hbaseConfig);
        hbaseReaderParameter.setTable("gpdata");
        hbaseReaderParameter.setColumn(hbaseColumns);

        JobContentReader hbaseJonContentReader = new JobContentReader();
        hbaseJonContentReader.setName("hbasereader");
        hbaseJonContentReader.setParameter(hbaseReaderParameter);

        // gp writer
        List<String> gpTable = Lists.newArrayList();
        gpTable.add("stu");

        List<SQLReaderParameterConnection> gpConnections = Lists.newArrayList();
        SQLReaderParameterConnection gpConnection = new SQLReaderParameterConnection();
        gpConnection.setTable(gpTable);
        //gpConnection.setJdbcUrl("jdbc:postgresql://192.168.9.30:5436/postgres?useSSL=false");
        gpConnection.setJdbcUrl("jdbc:pivotal:greenplum://192.168.9.30:5436;DatabaseName=postgres");
        gpConnections.add(gpConnection);

        List<JobContentParameterColumn> gpColumns = Lists.newArrayList();
        JobContentParameterColumn column1 = new JobContentParameterColumn();
        column1.setName("id");
        column1.setType("integer");
        JobContentParameterColumn column2 = new JobContentParameterColumn();
        column2.setName("name");
        column2.setType("varchar");
        JobContentParameterColumn column3 = new JobContentParameterColumn();
        column3.setName("age");
        column3.setType("integer");
        gpColumns.add(column1);
        gpColumns.add(column2);
        gpColumns.add(column3);

        GreenplumWriterParameter gpWriterParameter = new GreenplumWriterParameter();
        gpWriterParameter.setUsername("gpadmin");
        gpWriterParameter.setPassword("aw@2023");
        gpWriterParameter.setConnection(gpConnections);
        gpWriterParameter.setColumn(gpColumns);

        JobContentWriter gpJobContentWriter = new JobContentWriter();
        // greenplumwriter | postgresqlwriter
        gpJobContentWriter.setName("greenplumwriter");
        gpJobContentWriter.setParameter(gpWriterParameter);

        JobContent jobContent = new JobContent();
        jobContent.setReader(hbaseJonContentReader);
        jobContent.setWriter(gpJobContentWriter);

        List<JobContent> jobContents = Lists.newArrayList();
        jobContents.add(jobContent);

        JobSettingSpeed jobSettingSpeed = new JobSettingSpeed();
        jobSettingSpeed.setChannel(1);
        jobSettingSpeed.setBytes(0L);

        JobSetting jobSetting = new JobSetting();
        jobSetting.setSpeed(jobSettingSpeed);

        // jon
        Job job = new Job();
        job.setContent(jobContents);
        job.setSetting(jobSetting);

        Root root = new Root();
        root.setJob(job);

        // 生成 json
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 将 JSON 字符串写入 JSON 文件
            objectMapper.writeValue(new File(localName), root);
            log.info("\u001B[35mJSON 文件生成成功 ！\u001B[0m");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        // 生成文件
        String localJsonFileName = "hbase_to_greenplum_" + System.currentTimeMillis() + ".json";
        String localJsonFilePath = System.getProperty(TMP_DIR) + localJsonFileName;
        log.info("\u001B[35m本地地址：{}\u001B[0m", localJsonFilePath);
        hbaseToGp(localJsonFilePath);

        String hostUser = "root";
        String host_30 = "192.168.9.30";
        String password_30 = "%j*da^szp";
        String host_116 = "192.168.8.116";
        String password_116 = "3MKZ9Kq$PLW";

        SSHInfo sshInfo = new SSHInfo();
        sshInfo.setHost(host_30);
        sshInfo.setPort(22);
        sshInfo.setUsername(hostUser);
        sshInfo.setPassword(password_30);
        sshInfo.setTimeout(3000);
        SSHRemoteUtils.getInstance().getSession(sshInfo);

        String remoteChunJunJsonPath = "/data/temp/chunjun/config/" + localJsonFileName;
        SSHRemoteUtils.getInstance().uploadFile(remoteChunJunJsonPath, localJsonFilePath);

        String chunJunRemotePath = "/data/bigdata/chunjun/chunjun-dist/bin/chunjun-standalone.sh";
        String chunjunCommand = String.format("sh %s -job %s", chunJunRemotePath, remoteChunJunJsonPath);

        // String chunJunBinStandalone = "sh /data/chunjun/bin/chunjun-local.sh -job " + "/data/temp/chunjun/config/hbase_to_greenplum.json";
        // String chunJunBinLocal2 = "docker logs -f --tail 100 246";
        log.info("\u001B[35mremote command: {}\u001B[0m", chunjunCommand);
        SSHRemoteUtils.getInstance().execCommand(chunjunCommand);
        SSHRemoteUtils.getInstance().closeSession();
    }
}
