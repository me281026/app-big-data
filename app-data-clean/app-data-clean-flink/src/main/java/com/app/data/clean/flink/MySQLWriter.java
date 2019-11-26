package com.app.data.clean.flink;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MySQLWriter extends RichSinkFunction<String> implements Serializable {



    private static Logger logger = LoggerFactory.getLogger(MySQLWriter.class);

    private transient DruidDataSource dataSource;
    private transient PreparedStatement preparedStatement;
    private transient DruidPooledConnection connection;
    private static String drivername = "com.mysql.jdbc.Driver";
    private static String dburl = "jdbc:mysql://192.168.20.160:3306/app2";


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        Properties properties = new Properties();
        properties.put("driverClassName",drivername);
        properties.put("username","root");
        properties.put("password","abc123");
        properties.put("url",dburl);
        properties.put("minIdle","3");
        properties.put("initialSize","3");
        properties.put("maxActive","20");
        properties.put("maxWait","60000");
        properties.put("validationQuery","SELECT 'x'");
        properties.put("testOnBorrow","true");
        properties.put("testWhileIdle","true");
        properties.put("timeBetweenEvictionRunsMillis","60000");
        properties.put("poolPreparedStatements","true");
        properties.put("maxPoolPreparedStatementPerConnectionSize","20");
        dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        connection = dataSource.getConnection();
        //String sql = "insert into Student(id, name, password, age) values(?, ?, ?, ?)";
        String sql = "insert into bus_company_reg_address(id,delete_flag,code,area_id,city,city_id,company_id,country,ctiycode,district,province,province_id,reg_address,reg_location,towncode,township,company_info_id,md5id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        preparedStatement = this.connection.prepareStatement(sql);
        logger.info("打开MySQL连接");
    }



    @Override
    public void invoke(String string) throws Exception {

        try {
            logger.info("获取MySQL数据");
            //遍历数据集合
            JSONObject jsonObject = JSONObject.parseObject(string);
            String reg_address_detail = jsonObject.getString("reg_address_detail");
            String company_all = jsonObject.getString("company_all");
            JSONObject reg_address_detail_json = JSONObject.parseObject(reg_address_detail);
            JSONObject company_all_json = JSONObject.parseObject(company_all);
            preparedStatement.setLong(1,0l);
            preparedStatement.setInt(2,0);
            preparedStatement.setString(3,"");
            preparedStatement.setString(4,reg_address_detail_json.getString("area_id"));
            preparedStatement.setString(5,reg_address_detail_json.getString("city"));
            preparedStatement.setString(6,reg_address_detail_json.getString("city_id"));
            //preparedStatement.setLong(7,jsonObject.getLong("company_id"));
            preparedStatement.setLong(7,0);
            preparedStatement.setString(8,reg_address_detail_json.getString("country"));
            preparedStatement.setString(9,reg_address_detail_json.getString("ctiycode"));
            preparedStatement.setString(10,reg_address_detail_json.getString("district"));
            preparedStatement.setString(11,reg_address_detail_json.getString("province"));
            preparedStatement.setString(12,reg_address_detail_json.getString("province_id"));
            preparedStatement.setString(13,company_all_json.getString("reg_address"));
            preparedStatement.setString(14,reg_address_detail_json.getString("location"));
            preparedStatement.setString(15,reg_address_detail_json.getString("towncode"));
            preparedStatement.setString(16,reg_address_detail_json.getString("township"));
            preparedStatement.setString(17,jsonObject.getString("company_info_id"));
            preparedStatement.setString(18,jsonObject.getString("md5_id"));
            //插入数据
            preparedStatement.executeUpdate();
            //System.out.println("插入MySQL数据"+string);
            logger.info("插入数据");
        }catch (Exception e) {
            logger.error(e.getMessage());
            ////SendMailUtil.send("zww@app-tech.com", "MySQL Exception Info", e.getMessage());
        }

    }

    @Override
    public void close() throws Exception {
        super.close();
        //关闭连接和释放资源
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
