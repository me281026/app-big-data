import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jodd.http.HttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 高德的API封装类
 * 
 * @author wesley.zhang
 *
 */
	public class GeoHttpApi {
		private final static Logger logger = LoggerFactory.getLogger(GeoHttpApi.class);
		private final String url;
		private final String key;

		public GeoHttpApi(String url, String key) {
			super();
			this.url = url;
			this.key = key;
		}


	/**
	 * 查询地址信息
	 * 
	 * @param city
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public JSONObject findGeoInfoByAddress(String city, String address) throws IOException {
		final HttpRequest request = HttpRequest.get(url).query("key", key);
		address = StringUtils.trimToNull(address);
		if (StringUtils.isBlank(address))
			throw new IOException("address is null");
		request.query("address", address);
		city = StringUtils.trimToNull(city);
		if (StringUtils.isNotBlank(city))
			request.query("city", city);
//		System.out.println(request.send().bodyText());
		JSONObject geo = null;
		try {
			final long start = System.currentTimeMillis();
			final String body = request.send().bodyText();
			final long callTime = System.currentTimeMillis() - start;
//			final long callTime2 = TimeUnit.SECONDS.convert(callTime, TimeUnit.MILLISECONDS);
			if (callTime > 1000)
				logger.info("geo call time is {}ms", callTime);
			geo = JSON.parseObject(body);
		} catch (Exception e) {
			throw new IOException("高德API错误");
		}
		if (geo == null) {
			throw new IOException("高德API错误");
		}
		return geo;
	}

	public static JSONObject getAddressDetail(String city,String address) throws IOException {
        GeoHttpApi geoHttpApi = new GeoHttpApi("https://restapi.amap.com/v3/geocode/geo", "7c5aef61459541adcd1edbb5e1e9228e");
        JSONObject firstGeoCodeByAddress = geoHttpApi.findGeoInfoByAddress(city, address);
        JSONObject jsonObject = JSONObject.parseObject(firstGeoCodeByAddress.toJSONString());
        JSONObject rejo=new JSONObject();
        if ("1".equals(jsonObject.get("status"))) {
            JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
            try{
                JSONObject geocode = jsonArray.getJSONObject(0);
                if (geocode != null) {
                    rejo.put("province",geocode.get("province"));
                    rejo.put("city",geocode.get("city"));
                    rejo.put("county",geocode.get("district"));
                    rejo.put("area_id",geocode.get("adcode"));
                    String s1 = (String) geocode.get("location");
                    if(s1.contains(",")){
                        String[] split = s1.split(",");
                        if(split.length==2){
                            rejo.put("comp_lng",split[0]);
                            rejo.put("comp_lat",split[1]);
                        }
                    }
                }
            }catch (Exception e){
                System.out.println("地址缺失~");
            }
        }
        return rejo;
    }


	public static void main(String[] args) {

        JSONObject addressDetail = null;
        try {
            addressDetail = GeoHttpApi.getAddressDetail("", "武汉国际广场");
        } catch (IOException e) {
            System.out.println("获取地址失败~");
            e.printStackTrace();
        }
        System.out.println(addressDetail);
    }
}
