package com.app.data.clean.flink;

import com.app.data.clean.service.SendMailUtil;
import net.sf.json.JSONObject;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FlinkSink extends RichSinkFunction<Object> implements Serializable {

    private Logger logger = LoggerFactory.getLogger(FlinkSink.class);

    private static final long serialVersionUID = 1L;

    private org.apache.hadoop.conf.Configuration configuration;
    private Connection connection;
    private Admin admin;
    private Table table = null;



    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        // 处理开始之前的操作
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");

        //configuration.set("hbase.zookeeper.quorum", "192.168.40.230,192.168.40.231,192.168.40.232");
        //configuration.set("hbase.master", "192.168.40.231:60000");
        configuration.set("hbase.zookeeper.quorum", "192.168.20.102,192.168.20.103,192.168.20.104");
        configuration.set("hbase.master", "192.168.20.102:60000,192.168.20.103:60000");

        configuration.setInt("hbase.rpc.timeout", 2000);

        configuration.setInt("hbase.client.operation.timeout", 3000);
        configuration.setInt("hbase.client.scanner.timeout.period", 2000);



        //设置缓存1m，当达到1m时数据会自动刷到hbase
        //params = new BufferedMutatorParams(TableName.valueOf("flink_test2"));
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
            //System.out.println("hbase初始化成功~");
        } catch (IOException e) {
            logger.error(e.getMessage());
            ////SendMailUtil.send("zww@app-tech.com", "HBase Exception Info", e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            if (null != table) {
                table.close();
            }
            if (null != admin) {
                admin.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (IOException e) {
            ////SendMailUtil.send("zww@app-tech.com", "HBase Exception Info", e.getMessage());
        }
    }

    @Override
    public void invoke(Object value) throws Exception {
        //写到回kafka


        //数据持久化,写入HBase
        try {
            String rowkey = (com.alibaba.fastjson.JSONObject.parseObject(value.toString())).getString("company_info_id");

            //insertJson("companyinfo_test", rowkey, "info", value.toString());
            insertJson("companyinfo", rowkey, "info", value.toString());
            //System.out.println("insertToHbase"+System.currentTimeMillis()+value.toString());
            logger.info(rowkey + "，地址：" + (com.alibaba.fastjson.JSONObject.parseObject(value.toString())).getString("address_detail"));
        } catch (Exception e) {
            logger.info("数据转json失败：" + value);
            ////SendMailUtil.send("zww@app-tech.com", "HBase Exception 数据转json失败", e.getMessage());
        }
    }

    public void insertJson(String tableName, String rowKey,String colFamily,String json) throws IOException {
        if(!admin.tableExists(TableName.valueOf(tableName))) {
            //如果不存在则需要创建表和族
            admin.createTable(new HTableDescriptor(tableName).addFamily(new HColumnDescriptor(colFamily)));
        }
        table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        List<Put> putlist = new ArrayList<Put>();
        JSONObject jsonObject = JSONObject.fromObject(json);
        Iterator<?> iterator = jsonObject.keys();

        //设置缓存1m，当达到5m时数据会自动刷到hbase
        //bufferedMutator.writeBufferSize(1024 * 1024*5); //设置缓存的大小

        while(iterator.hasNext()){
            String key = (String) iterator.next();
            String value = jsonObject.getString(key);
            put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(key), Bytes.toBytes(value));
            putlist.add(put);
        }
        table.put(putlist);

    }



}
