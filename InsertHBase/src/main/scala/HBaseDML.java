
import net.sf.json.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HBaseDML {

	public static Configuration configuration;
	public static Connection connection;
	public static Admin admin;
	
	public static void main(String[] args) throws IOException {
		//String s="{\"law_operations\":[],\"branchs\":[],\"equitys\":[],\"shareholderinfoall\":[{\"report_value\":[{\"shareholderinfobasic\":{\"company_control_shares\":\"企业选择不公示\",\"report_company_email\":\"hong-ying.yang@foxconn.com\",\"company_business_activities\":\"研发、生产、加工新型电子元器件\",\"report_people_nums\":\"企业选择不公示\",\"investment_inf\":\"否\",\"report_www_shop\":\"否\",\"report_reg_status\":\"开业\",\"report_company_name\":\"国基电子（上海）有限公司\",\"ifnotguaranteeinfo\":\"否\",\"report_company_tel\":\"021-61206688\",\"report_address\":\"上海市松江工业区南乐路1925号\",\"postal_code\":\"201611\",\"report_social_credit_code\":\"913100007503167687\",\"ifnotchangeshares\":\"是\"}},{\"shareholderinfoshare\":[{\"share_way\":\"货币\",\"share_real_amount\":\"58798.8947\",\"share_real_date\":\"2007-3-30\",\"share_date\":\"2007-3-30\",\"shareholder\":\"富士康工业互联网股份有限公司\",\"share_amount\":\"58798.8947\",\"share_real_way\":\"货币\"}]},{\"shareholderinfoinvest\":[]},{\"shareholderinfoasset\":{\"total_asset\":\"企业选择不公示\",\"total_debt\":\"企业选择不公示\",\"total_equity\":\"企业选择不公示\",\"total_sale\":\"企业选择不公示\",\"business_income\":\"企业选择不公示\",\"net_profit\":\"企业选择不公示\",\"total_profit\":\"企业选择不公示\",\"total_tax\":\"企业选择不公示\"}}],\"report_year\":\"2017\"},{\"report_value\":[{\"shareholderinfobasic\":{\"company_control_shares\":\"企业选择不公示\",\"report_company_email\":\"hong-ying.yang@foxconn.com\",\"company_business_activities\":\"研发、生产、加工新型电子元器件\",\"report_people_nums\":\"企业选择不公示\",\"investment_inf\":\"是\",\"report_www_shop\":\"否\",\"report_reg_status\":\"开业\",\"report_company_name\":\"国基电子（上海）有限公司\",\"ifnotguaranteeinfo\":\"否\",\"report_company_tel\":\"021-61206688\",\"report_address\":\"上海市松江工业区南乐路1925号\",\"postal_code\":\"201611\",\"report_social_credit_code\":\"913100007503167687\",\"ifnotchangeshares\":\"否\"}},{\"shareholderinfoshare\":[{\"share_way\":\"货币\",\"share_real_amount\":\"7340\",\"share_real_date\":\"2007-3-30\",\"share_date\":\"2007-3-30\",\"shareholder\":\"AMBIT MICROSYEMS (CAYMAN) LTD.\",\"share_amount\":\"7340\",\"share_real_way\":\"货币\"}]},{\"shareholderinfoinvest\":[{\"invest_social_credit_code\":\"330421000146267\",\"invest_company_name\":\"国基电子商务（嘉善）有限公司\"},{\"invest_social_credit_code\":\"91330106MA27W0BG2F\",\"invest_company_name\":\"鸿富创新（杭州）有限公司\"},{\"invest_social_credit_code\":\"510109000145926\",\"invest_company_name\":\"成都市准时达供应链管理有限公司\"}]},{\"shareholderinfoasset\":{\"total_asset\":\"企业选择不公示\",\"total_debt\":\"企业选择不公示\",\"total_equity\":\"企业选择不公示\",\"total_sale\":\"企业选择不公示\",\"business_income\":\"企业选择不公示\",\"net_profit\":\"企业选择不公示\",\"total_profit\":\"企业选择不公示\",\"total_tax\":\"企业选择不公示\"}}],\"report_year\":\"2016\"},{\"report_value\":[{\"shareholderinfobasic\":{\"report_reg_status\":\"开业\",\"report_company_name\":\"国基电子（上海）有限公司\",\"ifnotguaranteeinfo\":\"\",\"report_company_tel\":\"021-61206688\",\"report_company_email\":\"hong-ying.yang@foxconn.com\",\"report_address\":\"上海市松江工业区南乐路1925号\",\"postal_code\":\"201611\",\"report_people_nums\":\"企业选择不公示\",\"investment_inf\":\"是\",\"report_social_credit_code\":\"913100007503167687\",\"report_www_shop\":\"否\",\"ifnotchangeshares\":\"否\"}},{\"shareholderinfoshare\":[{\"share_way\":\"货币\",\"share_real_amount\":\"7340\",\"share_real_date\":\"2007-3-30\",\"share_date\":\"2007-3-30\",\"shareholder\":\"AMBIT  MICROSYEMS (CAYMAN) LTD.\",\"share_amount\":\"7340\",\"share_real_way\":\"货币\"}]},{\"shareholderinfoinvest\":[{\"invest_social_credit_code\":\"330421000146267\",\"invest_company_name\":\"国基电子商务（嘉善）有限公司\"},{\"invest_social_credit_code\":\"91330106MA27W0BG2F\",\"invest_company_name\":\"鸿富创新（杭州）有限公司\"},{\"invest_social_credit_code\":\"510109000145926\",\"invest_company_name\":\"成都市准时达供应链管理有限公司\"}]},{\"shareholderinfoasset\":{\"total_asset\":\"企业选择不公示\",\"total_debt\":\"企业选择不公示\",\"total_equity\":\"企业选择不公示\",\"total_sale\":\"企业选择不公示\",\"business_income\":\"企业选择不公示\",\"net_profit\":\"企业选择不公示\",\"total_profit\":\"企业选择不公示\",\"total_tax\":\"企业选择不公示\"}}],\"report_year\":\"2015\"},{\"report_value\":[{\"shareholderinfobasic\":{\"report_reg_status\":\"开业\",\"report_company_name\":\"国基电子（上海）有限公司\",\"ifnotguaranteeinfo\":\"\",\"report_company_tel\":\"021-61206688\",\"report_company_email\":\"sjyhy_yang@hotmail.com\",\"report_address\":\"上海市松江工业区南乐路1925号\",\"postal_code\":\"201611\",\"report_people_nums\":\"企业选择不公示\",\"investment_inf\":\"否\",\"report_social_credit_code\":\"310000400342995\",\"report_www_shop\":\"否\",\"ifnotchangeshares\":\"否\"}},{\"shareholderinfoshare\":[{\"share_way\":\"货币\",\"share_real_amount\":\"7340\",\"share_real_date\":\"2007-3-30\",\"share_date\":\"2007-3-30\",\"shareholder\":\"AMBIT  MICROSYEMS (CAYMAN) LTD.\",\"share_amount\":\"7340\",\"share_real_way\":\"货币\"}]},{\"shareholderinfoinvest\":[]},{\"shareholderinfoasset\":{\"total_asset\":\"企业选择不公示\",\"total_debt\":\"企业选择不公示\",\"total_equity\":\"企业选择不公示\",\"total_sale\":\"企业选择不公示\",\"business_income\":\"企业选择不公示\",\"net_profit\":\"企业选择不公示\",\"total_profit\":\"企业选择不公示\",\"total_tax\":\"企业选择不公示\"}}],\"report_year\":\"2014\"},{\"report_value\":[{\"shareholderinfobasic\":{\"report_reg_status\":\"开业\",\"report_company_name\":\"国基电子（上海）有限公司\",\"ifnotguaranteeinfo\":\"\",\"report_company_tel\":\"021-61206688-21155\",\"report_company_email\":\"\",\"report_address\":\"上海市松江工业区南乐路1925号\",\"postal_code\":\"201611\",\"report_people_nums\":\"企业选择不公示\",\"investment_inf\":\"是\",\"report_social_credit_code\":\"310000400342995\",\"report_www_shop\":\"否\",\"ifnotchangeshares\":\"否\"}},{\"shareholderinfoshare\":[{\"share_way\":\"货币\",\"share_real_amount\":\"7340\",\"share_real_date\":\"2007-7-31\",\"share_date\":\"2007-12-31\",\"shareholder\":\"AMBIT MICROSYSTEMS (CAYMAN) LIMITED\",\"share_amount\":\"7340\",\"share_real_way\":\"货币\"}]},{\"shareholderinfoinvest\":[{\"invest_social_credit_code\":\"310227001362087\",\"invest_company_name\":\"上海富泰通国际物流有限公司\"}]},{\"shareholderinfoasset\":{\"total_asset\":\"企业选择不公示\",\"total_debt\":\"企业选择不公示\",\"total_equity\":\"企业选择不公示\",\"total_sale\":\"企业选择不公示\",\"business_income\":\"企业选择不公示\",\"net_profit\":\"企业选择不公示\",\"total_profit\":\"企业选择不公示\",\"total_tax\":\"企业选择不公示\"}}],\"report_year\":\"2013\"}],\"md5_id\":\"438ffd925fc2c49021db70c3f6b8dc6a\",\"sign\":{\"crawler_web\":\"http://www.sgs.gov.cn/notice/notice/view\",\"crawler_version\":\"1.0.0\",\"big_data\":\"1.0.0\",\"crawler_type\":\"工商管理数据\",\"words_version\":\"1.0.0\"},\"company_all\":{\"company_type\":\"有限责任公司（外商投资企业法人独资）\",\"legal_person\":\"黄干\",\"address\":\"上海市松江工业区南乐路1925号\",\"business_term\":\"2003-5-22至2053-5-21\",\"reg_capital_num\":58798.8947,\"industry\":\"\",\"reg_capital_type\":\"人民币\",\"approved_date\":\"2003-5-22\",\"reg_organs\":\"松江市监局\",\"business_scope\":\"生产、加工新型电子元器件（片式电子元器件、电力电子器件，光电子器件、敏感元器件及传感器生产、功率放大器，低噪声放大器、无线传输模块），数字音、视频编码解码设备（STB机顶盒，客户端接入媒体网关（IAD,MTA），视讯电话，语音分线器），网络、通信系统设备，异步转移模式（ATM）及IP数据通信系统，网络交换机，路由器，数字微波同步系统传输设备，光同步、光交叉系列传输连接设备，笔记本计算机、液晶显示器、手机外壳及模块，计算机散热装置及模块；精密模具及模具标准件，线缆、连接器、电子零件；计算机，服务器，游戏机、音乐播放器、掌上计算机、电话机、数码相机，投影机，DVD播放机，便携式DVD播放机，音乐播放器（MP3），视频播放器（MP4\\\\MP5）及上述商品的周边产品和零配件；卫星电视广播地面接收设施（产品100%出口），卫星导航定位接收设备，汽车零部件；包装印刷，工程塑料与塑料合金生产，废旧塑料的消解及再利用。以上产品的零配件生产和相关软件的开发，以及对以上产品的维修和商业性检，销售公司自产产品，并从事上述产品加工技术的研究、开发及中试（包括与国内科研院所合作研发），转让自研成果，仓储服务，从事货物及技术的进出口业务。\\r\\n【依法须经批准的项目，经相关部门批准后方可开展经营活动】\",\"reg_date\":\"2003-5-22\",\"capital_type_id\":\"1000\",\"regist_status_id\":\"null\",\"reg_status\":\"存续（在营、开业、在册）\",\"company_name\":\"国基电子（上海）有限公司\",\"social_credit_code\":\"913100007503167687\",\"uuid_num\":\"nfc_corvFBwYWx13fEy22iaGiW4MoAVu&tab=01\"},\"keymans\":[{\"keyman_job\":\"董事\",\"keyman_name\":\"郑弘孟\"},{\"keyman_job\":\"董事长\",\"keyman_name\":\"黄干\"},{\"keyman_job\":\"监事\",\"keyman_name\":\"徐能钤\"},{\"keyman_job\":\"董事\",\"keyman_name\":\"凌致平\"}],\"changes\":[],\"address_detail\":{\"comp_lat\":\"31.008935\",\"province\":\"上海市\",\"city\":\"上海市\",\"province_id\":\"310000\",\"county\":\"松江区\",\"comp_lng\":\"121.275927\",\"area_id\":\"310117\",\"city_id\":\"310100\"},\"checks\":[],\"company_info_id\":\"091f363a238e11e9a067005056c00008\",\"judicial_assistance\":[],\"permissions\":[{\"permission_office\":\"松江区环境保护局\",\"permission_content\":\"关于国基电子（上海）有限公司X射线装置使用项目环境影响报告表的许可\",\"permission_validite\":\"2017年4月11日\",\"permission_number\":\"松环保许辐[2017]4号\",\"permission_validityform\":\"2099年12月31日\",\"permission_name\":\"生产、销售、使用放射性同位素和加速器以及放射源的射线装置的单位的环境影响评价的审批\"},{\"permission_office\":\"松江区环境保护局\",\"permission_content\":\"关于国基电子（上海）有限公司新增1台射线装置环境影响登记表的许可\",\"permission_validite\":\"2015年11月9日\",\"permission_number\":\"松环保许辐[2015]21号\",\"permission_validityform\":\"2099年12月31日\",\"permission_name\":\"生产、销售、使用放射性同位素和加速器以及放射源的射线装置的单位的环境影响评价的审批\"},{\"permission_office\":\"松江区环境保护局\",\"permission_content\":\"关于国基电子（上海）有限公司新增自屏蔽工业X射线装置使用项目环境影响报告表的许可\",\"permission_validite\":\"2018年1月3日\",\"permission_number\":\"松环保许辐[2018]1号\",\"permission_validityform\":\"2099年12月31日\",\"permission_name\":\"生产、销售、使用放射性同位素和加速器以及放射源的射线装置的单位的环境影响评价的审批\"},{\"permission_office\":\"松江区环境保护局\",\"permission_content\":\"关于国基电子（上海）有限公司扩建项目验收的许可\",\"permission_validite\":\"2016年11月14日\",\"permission_number\":\"松环保许管[2016]1210号\",\"permission_validityform\":\"2099年12月31日\",\"permission_name\":\"建设项目环保竣工验收的审批事项\"},{\"permission_office\":\"松江区环境保护局\",\"permission_content\":\"关于国基电子（上海）有限公司扩建项目环境影响报告表的许可\",\"permission_validite\":\"2015年11月10日\",\"permission_number\":\"松环保许管[2015]1025号\",\"permission_validityform\":\"2099年12月31日\",\"permission_name\":\"建设项目环境影响评价文件的审批\"},{\"permission_office\":\"上海市无线电管理局\",\"permission_content\":\"无线电频率使用许可\",\"permission_validite\":\"2015年12月4日\",\"permission_number\":\"频字2015-091\",\"permission_validityform\":\"2018年12月31日\",\"permission_name\":\"无线电频率使用许可\"},{\"permission_office\":\"\",\"permission_content\":\"生产、加工新型电子元器件{片式电子元器件（信号滤波器）、电力电子器件（数位/宽频调制解调器，逆变器，直流转直流电源供应器，交流转直流电源供应器），光电子器件、敏感元器件及传感器生产（光纤数字收发模块，光收发转换器，光发射模块用光次组件，光接收模块用光次组件，光发射/接收模块用电次组件，激光器筒形封装，光接收筒形封装，光电集线器）、功率放大器，低噪声放大器、无线传输模块}，数字音、视频编解码设备{STB机顶盒，客户端接入媒体网关（IAD，MTA），视讯电话，语音分线器}，网络、通信系统设备，异步转移模式（ATM）及IP数据通信系统，网络交换机，路由器，数字微波同步系列传输设备，光同步、光交叉系列传输连接设备，笔记本计算机外壳及模块、液晶显示器外壳及模块、手机外壳及模块，电脑散热装置及模块; 精密模具及模具标准件，线缆、连接器、电子零件；电脑，工作组级服务器、部门级服务器、企业级服务器、通用型服务器和专用型（功能型）服务器、台式服务器和机架式服务器以及刀片服务器、CISC（复杂指令集）架构的服务器和RISC（精简指令集）架构的服务器，游戏机？音乐播放器？掌上计算机？电话机，数码相机，投影机，DVD播放机，便携式DVD播放机，音乐播放器(MP3)，视频播放器(MP4 / MP5）及上述商品的周边产品和零配件；卫星电视广播地面接收设施（产品100%出口），卫星导航定位接收设备；包装印刷；工程塑料与塑料合金生产，废旧塑料的消解及再利用。以上产品的零配件生产和相关软件的开发，以及对以上产品的维修和商业性检测，销售公司自产产品，并从事上述产品加工技术的研究、开发及中试（包括与国内科研院所合作研发），转让自研成果，并提供同类商品(不含废旧塑料)的批发、进出口、佣金代理（拍卖除外），仓储及相关的售后技术咨询服务。\",\"permission_validite\":\"2014年11月10日\",\"permission_number\":\"\",\"permission_validityform\":\"\",\"permission_name\":\"对权限内外商投资企业变更的审批（资质类事项）\"},{\"permission_office\":\"松江\",\"permission_content\":\"\",\"permission_validite\":\"2014年11月17日\",\"permission_number\":\"松环保许管[2014]1321号\",\"permission_validityform\":\"9999年9月9日\",\"permission_name\":\"建设项目环保竣工验收的审批事项\"},{\"permission_office\":\"松江\",\"permission_content\":\"\",\"permission_validite\":\"2015年1月19日\",\"permission_number\":\"松环保许辐[2015]3号\",\"permission_validityform\":\"9999年9月9日\",\"permission_name\":\"建设项目环保竣工验收的审批事项\"},{\"permission_office\":\"松江区人力资源和社会保障局\",\"permission_content\":\"根据《关于企业实行不定时工作制和综合计算工时工作制的审批办法》(劳部发[94]503号)的规定，经研究，同意你单位下列岗位人员自2017年08月01日至2018年07月31日期间实行其他工作时间制度:外勤人员（销售、采购）、消防值班岗位实行不定时工作制。电子元件生产、随线质检、仓库出货收料岗位实行以年为周期的综合计算工时工作制。\",\"permission_validite\":\"2017年8月1日\",\"permission_number\":\"松人社工时审2017第0684号\",\"permission_validityform\":\"2018年7月31日\",\"permission_name\":\"准予企业实行其他工作时间制度决定书\"},{\"permission_office\":\"松江区环境保护局\",\"permission_content\":\"关于国基电子（上海）有限公司基于大数据物联网的智能改造项目环境影响登记表的许可\",\"permission_validite\":\"2016年6月21日\",\"permission_number\":\"松环保许管[2016]535号\",\"permission_validityform\":\"2099年12月31日\",\"permission_name\":\"建设项目环境影响评价文件的审批\"},{\"permission_office\":\"\",\"permission_content\":\"#65380;电话机，数码相机，投影机，DVD播放机，便携式DVD播放机，音乐播放器(MP3)，视频播放器(MP4 / MP5）及上述商品的周边产品和零配件；卫星电视广播地面接收设施（产品100%出口），卫星导航定位接收设备；汽车零部件；包装印刷，工程塑料与塑料合金生产，废旧塑料的消解及再利用。以上产品的零配件生产和相关软件的开发，以及对以上产品的维修和商业性检测，销售公司自产产品，并从事上述产品加工技术的研究、开发及中试（包括与国内科研院所合作研发），转让自研成果，并提供同类商品的批发、进出口、佣金代理（拍卖除外），仓储及相关的售后技术咨询服务。\",\"permission_validite\":\"2015年9月30日\",\"permission_number\":\"\",\"permission_validityform\":\"\",\"permission_name\":\"对权限内外商投资企业变更的审批（资质类事项）\"}],\"punishments\":[],\"grab_time\":\"2019-01-29\",\"pledges\":[],\"stockholder_pledges\":[{\"stockholder_name\":\"富士康工业互联网股份有限公司\",\"stockholder_license_type\":\"企业法人营业执照(外资)\",\"stockholder_type\":\"外商投资企业\",\"stockholder_namber\":\"914403003296132911\"}],\"abnormal_operations\":[]}";
        //HBaseDML.insertJson("companyinfo","efba1284eda9417c97376a519461cbec","info",s);
		//getData("test003", "8e084f2cf24311e88a83107b44802a04"); //online
		getData("companyinfo", "86eea59a2c1911e98d54005056ad4278");
		//listTables();
	}

	/**
	 * 初始化链接
	 */
	public static void init() {
		configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.property.clientPort", "2181");
	    configuration.set("hbase.zookeeper.quorum", "192.168.20.102,192.168.20.103,192.168.20.104");
	    configuration.set("hbase.master", "192.168.20.102:60000");

		try {
			connection = ConnectionFactory.createConnection(configuration);
			admin = connection.getAdmin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 */
	public static void close() {
		try {
			if (null != admin) {
				admin.close();
			}
			if (null != connection) {
				connection.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void listTables() {
		init();
		HTableDescriptor hTableDescriptors[] = null;
		try {
			hTableDescriptors = admin.listTables();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (HTableDescriptor hTableDescriptor : hTableDescriptors) {
			println(hTableDescriptor.getNameAsString());
		}
		close();
	}

	public static void getData(String tableName, String rowKey, String colFamily, String col) throws IOException {
		init();
		Table table = connection.getTable(TableName.valueOf(tableName));
		Get get = new Get(Bytes.toBytes(rowKey));
		if (colFamily != null) {
			get.addFamily(Bytes.toBytes(colFamily));
		}
		if (colFamily != null && col != null) {
			get.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col));
		}
		Result result = table.get(get);
		showCell(result);
		table.close();
		close();
	}

	public static void showCell(Result result) {
		Cell[] cells = result.rawCells();
		for (Cell cell : cells) {
			println("RowName: " + new String(CellUtil.cloneRow(cell)) + " ");
			println("Timetamp: " + cell.getTimestamp() + " ");
			println("column Family: " + new String(CellUtil.cloneFamily(cell)) + " ");
			println("row Name: " + new String(CellUtil.cloneQualifier(cell)) + " ");
			println("value: " + new String(CellUtil.cloneValue(cell)) + " ");
			//println("value2: " + new String(ArrayUtils(CellUtil.cloneValue(cell)) + " "));
			//ArrayUtils.toString(arr, ",")
		}
	}

	private static void println(Object obj) {
		System.out.println(obj);
	}

	public static void getData(String tableName, String rowKey) throws IOException {
		getData(tableName, rowKey, null, null);
	}

	public static void insertJson(String tableName, String rowKey,String colFamily,String json) throws IOException {
		init();
		Table table = connection.getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));
		List<Put> putlist = new ArrayList<Put>();
		JSONObject jsonObject = JSONObject.fromObject(json);
		Iterator<?> iterator = jsonObject.keys();
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			String value = jsonObject.getString(key);
			put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(key), Bytes.toBytes(value));
			putlist.add(put);
		}
		table.put(putlist);
		table.close();
		close();
	}

	public static void hbinjson(String data) throws IOException {

		String rowkey = (com.alibaba.fastjson.JSONObject.parseObject(data)).getString("company_info_id");

		insertJson("companyinfo",rowkey,"info",data);

		System.out.println(rowkey);
	}
}
