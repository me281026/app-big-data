import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.data.clean.core.entity.CrawlerData;
import org.junit.Test;

import java.io.File;

public class TxtTest {
    /*public static void main(String[] args) {
        FileUtil.readUtf8Lines(new File("C:\\Users\\app\\Desktop\\idmap.txt"), new LineHandler() {
            int index = 1;

            @Override
            public void handle(String s) {
                System.out.println(s);
                index++;
            }
        });
    }

    @Test
    public void underlineToHump() {
        String data = ResourceUtil.readUtf8Str("campinfo.json");
        JSONObject json = JSON.parseObject(data);
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.CamelCase;
//        System.out.println(ResourceUtil.getResource("campinfo.json").getPath());
//        File file = new File(ResourceUtil.getResource("campinfo2.json").getPath());
        File file = new File("campinfo2.json");
        FileUtil.writeUtf8String(JSON.toJSONString(json, config, SerializerFeature.PrettyFormat), file);
    }

    @Test
    public void underlineToHump2() throws JsonProcessingException {
        String data = ResourceUtil.readUtf8Str("campinfo.json");
        Object jsonOject = JsonUtils.convert(data);
        File file = new File("campinfo2.json");
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.CamelCase;
//        System.out.println(ResourceUtil.getResource("campinfo.json").getPath());
//        File file = new File(ResourceUtil.getResource("campinfo2.json").getPath());
        FileUtil.writeUtf8String(JSON.toJSONString(jsonOject, config, SerializerFeature.PrettyFormat), file);

    }


    @Test
    public void underlineToHump3() {
        String data = ResourceUtil.readUtf8Str("campinfo.json");
        CrawlerData crawlerData = JSON.parseObject(data, CrawlerData.class);
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
//        System.out.println(ResourceUtil.getResource("campinfo.json").getPath());
//        File file = new File(ResourceUtil.getResource("campinfo2.json").getPath());
        File file = new File("campinfo3.json");
        FileUtil.writeUtf8String(JSON.toJSONString(crawlerData, config, SerializerFeature.PrettyFormat), file);
    }*/


}
