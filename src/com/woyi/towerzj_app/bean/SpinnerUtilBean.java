package com.woyi.towerzj_app.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 此类描述的是：spinner数据填充
 * 
 * @author: 罗然
 * @version: 2015-7-19 下午3:25:23
 * @ClassName: SpinnerUtilBean
 * @项目： towerzj_app
 * @包：com.woyi.towerzj_app.bean
 */
public class SpinnerUtilBean {

	private static List<Option> sblx;// 设备类型
	private static List<Option> sz;// 设置
	private static List<Option> ttlx;// 铁塔类型
	private static List<Option> ttpt;// 铁塔平台
	private static List<Option> jflx;// 机房类型
	private static List<Option> jfjg;// 机房结构
	private static List<Option> ktcj;// 空调厂家
	private static List<Option> dh;
	private static List<Option> xdcpp;// 蓄电池品牌 带输入
	private static List<Option> kgdypp;// 开关电源品牌 带输入
	private static List<Option> pdxpp;// 配电箱品牌 带输入
	private static List<Option> txlx;// 天线类型
	private static List<Option> dhcj;// 动环厂家
	private static List<Option> txzt;// 天线状态
	private static List<Option> txpt;// 天线平台
	private static List<Option> physicalType;// 站点类型

	private static List<Option> guardCj;
	private static List<Option> FsuType;


	private static List<Option> roomys;//机房钥匙
	private static List<Option> roomysjj;//钥匙是否交接
	//动环   厂家联动
	private static List<Option> dhFsyCjType;
	private static List<Option> dhZjCjType;
	private static List<Option> dhMjCjType;

	/**
	 * 
	 * 此方法描述的是：动环厂家联动
	 * @Title: getDhFsyCjType 
	 * @author: 罗然
	 * @return
	 * @return List<Option>    返回类型
	 * @version: 2015-8-24
	上午9:35:08
	 */
	public static List<Option> getDhFsyCjType(){
		if (dhFsyCjType == null) {
			dhFsyCjType = new ArrayList<Option>();


			dhFsyCjType.add(new Option("DTM-2830", "杭州大光明"));
			dhFsyCjType.add(new Option("ASC-100-SR", "艾赛通讯"));
			dhFsyCjType.add(new Option("ASC-100-CR8", "艾赛通讯"));
			dhFsyCjType.add(new Option("ASC-100-CR16", "艾赛通讯"));
			dhFsyCjType.add(new Option("DAM-2160", "广东高新兴"));
			dhFsyCjType.add(new Option("DAM-2160F", "广东高新兴"));
			dhFsyCjType.add(new Option("DAM-2160I", "广东高新兴"));
			dhFsyCjType.add(new Option("DAM-2160B", "广东高新兴"));
			dhFsyCjType.add(new Option("DAM-2160G", "广东高新兴"));
			dhFsyCjType.add(new Option("DAM-216I-U", "广东高新兴"));
			dhFsyCjType.add(new Option("ZXM10 MISUE", "中兴力维"));
			dhFsyCjType.add(new Option("ZXM10 SISU", "中兴力维"));
			dhFsyCjType.add(new Option("ZXM10 EISU", "中兴力维"));
			dhFsyCjType.add(new Option("ZXM10 CFMU", "中兴力维"));
			dhFsyCjType.add(new Option("BSMS-10", "深圳大诚"));
			dhFsyCjType.add(new Option("BSMS-9", "深圳大诚"));
			dhFsyCjType.add(new Option("BSMS-3", "深圳大诚"));
			dhFsyCjType.add(new Option("BSMS-3P", "深圳大诚"));
			dhFsyCjType.add(new Option("IDU", "深圳艾默生"));
			dhFsyCjType.add(new Option("IPLU", "深圳艾默生"));
			dhFsyCjType.add(new Option("IDA", "深圳艾默生"));
			dhFsyCjType.add(new Option("MCR-7A", "深圳艾默生"));
			dhFsyCjType.add(new Option("DCD8071", "广州邦讯"));
			dhFsyCjType.add(new Option("DCD8021", "广州邦讯"));
			dhFsyCjType.add(new Option("KD801", "科研所（康达）"));
			dhFsyCjType.add(new Option("KD801-N", "科研所（康达）"));
			dhFsyCjType.add(new Option("NC-608", "科研所（康达）"));
			dhFsyCjType.add(new Option("BSMS-10", "宁波银鸽"));
			dhFsyCjType.add(new Option("BSMS-9", "宁波银鸽"));
			dhFsyCjType.add(new Option("MTS-IIID", "宁波银鸽"));
			dhFsyCjType.add(new Option("MTS-IIIC", "宁波银鸽"));
			dhFsyCjType.add(new Option("SANYO", "艾苏威尔"));
			dhFsyCjType.add(new Option("DTSF8800型", "杭州明特"));
			dhFsyCjType.add(new Option("KT-800", "杭州思勤"));
			dhFsyCjType.add(new Option("无", "无"));
		}
		return dhFsyCjType;
	}

	/**
	 * 
	 * 此方法描述的是：动环主机厂家
	 * @Title: getDhZjCjType 
	 * @author: 罗然
	 * @return
	 * @return List<Option>    返回类型
	 * @version: 2015-8-24
	上午9:41:59
	 */
	public static List<Option> getDhZjCjType(){
		if (dhZjCjType == null) {
			dhZjCjType = new ArrayList<Option>();
			dhZjCjType.add(new Option("BASS-230", "广东高新兴"));
			dhZjCjType.add(new Option("BASS-260", "广东高新兴"));
			dhZjCjType.add(new Option("BASS-330", "广东高新兴"));
			dhZjCjType.add(new Option("DJ-03C", "杭州大光明"));
			dhZjCjType.add(new Option("DJ-05C", "杭州大光明"));
			dhZjCjType.add(new Option("601MB", "（科研所）康达"));
			dhZjCjType.add(new Option("无", "无"));
		}
		return dhZjCjType;
	}

	/**
	 * 
	 * 此方法描述的是：动环 门禁厂家
	 * @Title: getDhMjCjType 
	 * @author: 罗然
	 * @return
	 * @return List<Option>    返回类型
	 * @version: 2015-8-24
	上午9:45:10
	 */
	public static List<Option> getDhMjCjType(){
		if (dhMjCjType == null) {
			dhMjCjType = new ArrayList<Option>();

			dhMjCjType.add(new Option("BASS-260", "广东高新兴"));
			dhMjCjType.add(new Option("BASS-26R", "广东高新兴"));
			dhMjCjType.add(new Option("DJ-3100", "杭州大光明"));
			dhMjCjType.add(new Option("DJ-03C", "杭州大光明"));
			dhMjCjType.add(new Option("DJ-05C", "杭州大光明"));
			dhMjCjType.add(new Option("YNK-800", "杭州友你康"));
			dhMjCjType.add(new Option("KDDS", "科研所(康达)"));
			dhMjCjType.add(new Option("ZXM10 ACUC", "中兴力维"));
			dhMjCjType.add(new Option("创力门禁", "浙江创力"));
			dhMjCjType.add(new Option("中恒门禁", "中恒"));
			dhMjCjType.add(new Option("YNK-800", "杭州友你康"));
			dhMjCjType.add(new Option("MJ-08", "宁波银鸽"));
			dhMjCjType.add(new Option("无", "无"));
		}
		return dhMjCjType;
	}

	public static List<Option> getFsuType() {
		if (FsuType == null) {
			FsuType = new ArrayList<Option>();
			FsuType.add(new Option("请选择FSU型号", "请选择FSU型号"));
			FsuType.add(new Option("DTM-2830", "DTM-2830"));
			FsuType.add(new Option("ASC-100-SR", "ASC-100-SR"));
			FsuType.add(new Option("CR-8", "CR-8"));
			FsuType.add(new Option("DAM-2160", "DAM-2160"));
			FsuType.add(new Option("DAM-2160F", "DAM-2160F"));
			FsuType.add(new Option("DAM-2160B", "DAM-2160B"));
			FsuType.add(new Option("DAM-2160G", "DAM-2160G"));
			FsuType.add(new Option("DAM-216I-U", "DAM-216I-U"));
			FsuType.add(new Option("MISUE", "MISUE"));
			FsuType.add(new Option("SISU", "SISU"));
			FsuType.add(new Option("EISU", "EISU"));
			FsuType.add(new Option("CFMU", "CFMU"));
			FsuType.add(new Option("大诚FSU", "大诚FSU"));
			FsuType.add(new Option("邦讯FSU", "邦讯FSU"));
			FsuType.add(new Option("邮电FSU", "邮电FSU"));
			FsuType.add(new Option("KD801", "KD801"));
			FsuType.add(new Option("KD801-N", "KD801-N"));
			FsuType.add(new Option("其他", "其他"));
		}
		return FsuType;
	}

	/**
	 * 动环厂家
	 * 
	 * @return
	 */
	public static List<Option> getGuardCj() {
		if (guardCj == null) {
			guardCj = new ArrayList<Option>();
			guardCj.add(new Option("请选择厂家类型", "请选择厂家类型"));
			guardCj.add(new Option("大光明", "大光明"));
			guardCj.add(new Option("艾赛", "艾赛"));
			guardCj.add(new Option("高新兴", "高新兴"));
			guardCj.add(new Option("中兴", "中兴"));
			guardCj.add(new Option("深圳大诚", "深圳大诚"));
			guardCj.add(new Option("广州邦讯", "广州邦讯"));
			guardCj.add(new Option("邮电科研所", "邮电科研所"));
			guardCj.add(new Option("康达", "康达"));
			guardCj.add(new Option("其他", "其他"));
		}
		return guardCj;
	}

	public static List<Option> getPhysucalType() {
		if (physicalType == null) {
			physicalType = new ArrayList<Option>();
			physicalType.add(new Option("正常站", "正常站"));
			physicalType.add(new Option("铁塔资产", "铁塔资产"));
			physicalType.add(new Option("室分站", "室分站"));
			physicalType.add(new Option("拆除站", "拆除站"));
			physicalType.add(new Option("纠纷站", "纠纷站"));
			physicalType.add(new Option("搬迁站", "搬迁站"));
			physicalType.add(new Option("已退网站", "已退网站"));
			physicalType.add(new Option("共享站", "共享站"));
			physicalType.add(new Option("第三方租赁站", "第三方租赁站"));
			physicalType.add(new Option("铁塔站址重复", "铁塔站址重复"));
			physicalType.add(new Option("运营商站址重复", "运营商站址重复"));
			physicalType.add(new Option("未建站", "未建站"));
			physicalType.add(new Option("铁塔清查有误需删除", "铁塔清查有误需删除"));
			physicalType.add(new Option("运营商无资产", "运营商无资产"));
			physicalType.add(new Option("代维查无此站", "代维查无此站"));
			physicalType.add(new Option("运营商互称自有产权", "运营商互称自有产权"));
		}
		return physicalType;

	}

	public static List<Option> getDh() {
		if (dh == null) {
			dh = new ArrayList<Option>();
			dh.add(new Option("0", "0"));
			dh.add(new Option("1", "1"));
			dh.add(new Option("2", "2"));
			dh.add(new Option("3", "3"));
			dh.add(new Option("4", "4"));
			dh.add(new Option("5", "5"));

		}
		return dh;
	}

	public static List<Option> getdhcj() {
		if (dhcj == null) {
			dhcj = new ArrayList<Option>();
			dhcj.add(new Option("动环测试一", "动环测试一"));
			dhcj.add(new Option("动环测试二", "动环测试二"));
			dhcj.add(new Option("其他", "其他"));
		}
		return dhcj;
	}

	public static List<Option> getSblx() {
		if (sblx == null) {
			sblx = new ArrayList<Option>();
			sblx.add(new Option("0", "请选择"));
			sblx.add(new Option("1", "主资产"));
			sblx.add(new Option("2", "附属设备"));
			sblx.add(new Option("3", "设备重复"));
		}
		return sblx;
	}

	public static List<Option> getSz() {
		if (sz == null) {
			sz = new ArrayList<Option>();
			sz.add(new Option("主资产", "主资产"));
			sz.add(new Option("重复资产", "重复资产"));
		}
		return sz;
	}

	public static List<Option> getTtlx() {
		if (ttlx == null) {
			ttlx = new ArrayList<Option>();
			ttlx.add(new Option("H杆", "H杆"));
			ttlx.add(new Option("抱杆", "抱杆"));
			ttlx.add(new Option("便携式塔房一体化", "便携式塔房一体化"));
			ttlx.add(new Option("单管塔", "单管塔"));
			ttlx.add(new Option("地面拉线塔", "地面拉线塔"));
			ttlx.add(new Option("地面增高架", "地面增高架"));
			ttlx.add(new Option("仿生树", "仿生树"));
			ttlx.add(new Option("角钢塔", "角钢塔"));
			ttlx.add(new Option("景观塔", "景观塔"));
			ttlx.add(new Option("路灯杆", "路灯杆"));
			ttlx.add(new Option("其他", "其他"));
			ttlx.add(new Option("三管塔", "三管塔"));
			ttlx.add(new Option("四管塔", "四管塔"));
			ttlx.add(new Option("屋顶增高架（不带格栅）", "屋顶增高架（不带格栅）"));
			ttlx.add(new Option("屋顶增高架（带格栅）", "屋顶增高架（带格栅）"));
			ttlx.add(new Option("屋面景观塔（含美化罩、美化一体天线等）", "屋面景观塔（含美化罩、美化一体天线等）"));
			ttlx.add(new Option("屋面拉线桅杆", "屋面拉线桅杆"));
			ttlx.add(new Option("屋面增高架(含楼顶斜撑)", "屋面增高架(含楼顶斜撑)"));
			ttlx.add(new Option("无", "无"));
		}
		return ttlx;
	}

	public static List<Option> getJflx() {
		if (jflx == null) {
			jflx = new ArrayList<Option>();
			jflx.add(new Option("彩钢板房", "彩钢板房"));
			jflx.add(new Option("土建", "土建"));
			jflx.add(new Option("无机房", "无机房"));
			jflx.add(new Option("一体化机房", "一体化机房"));
			jflx.add(new Option("一体化机柜", "一体化机柜"));
			jflx.add(new Option("租赁机房", "租赁机房"));
		}
		return jflx;
	}

	public static List<Option> getJfjg() {
		if (jfjg == null) {
			jfjg = new ArrayList<Option>();
			jfjg.add(new Option("集成仓", "集成仓"));
			jfjg.add(new Option("框架", "框架"));
			jfjg.add(new Option("一体化", "一体化"));
			jfjg.add(new Option("砖混", "砖混"));
		}
		return jfjg;
	}

	public static List<Option> getKtcj() {
		if (ktcj == null) {
			ktcj = new ArrayList<Option>();
			ktcj.add(new Option("大金", "大金"));
			ktcj.add(new Option("华凌", "华凌"));
			ktcj.add(new Option("华菱", "华菱"));
			ktcj.add(new Option("科龙", "科龙"));
			ktcj.add(new Option("三洋", "三洋"));
			ktcj.add(new Option("三菱 ", "三菱 "));
			ktcj.add(new Option("海信", "海信"));
			ktcj.add(new Option("海尔", "海尔"));
			ktcj.add(new Option("美的", "美的"));
			ktcj.add(new Option("松下", "松下"));
			ktcj.add(new Option("其他", "其他"));
		}
		return ktcj;
	}

	public static List<Option> getXdcpp() {
		if (xdcpp == null) {
			xdcpp = new ArrayList<Option>();
			xdcpp.add(new Option("双登", "双登"));
			xdcpp.add(new Option("南都", "南都"));
			xdcpp.add(new Option("灯塔", "灯塔"));
			xdcpp.add(new Option("理士", "理士"));
			xdcpp.add(new Option("华达 ", "华达 "));
			xdcpp.add(new Option("汤浅", "汤浅"));
			xdcpp.add(new Option("华日", "华日"));
			xdcpp.add(new Option("卧龙", "卧龙"));
			xdcpp.add(new Option("光宇", "光宇"));
			xdcpp.add(new Option("中达", "中达"));
			xdcpp.add(new Option("其他", "其他"));
		}
		return xdcpp;
	}

	public static List<Option> kgdypp() {
		if (kgdypp == null) {
			kgdypp = new ArrayList<Option>();
			kgdypp.add(new Option("中恒", "中恒"));
			kgdypp.add(new Option("艾默生", "艾默生"));
			kgdypp.add(new Option("中达", "中达"));
			kgdypp.add(new Option("中兴", "中兴"));
			kgdypp.add(new Option("施威特克", "施威特克"));
			kgdypp.add(new Option("中达电通", "中达电通"));
			kgdypp.add(new Option("杭州中恒电气股份有限公司", "杭州中恒电气股份有限公司"));
			kgdypp.add(new Option("易达", "易达"));
			kgdypp.add(new Option("华为", "华为"));
			kgdypp.add(new Option("艾默生网络能源有限公司", "艾默生网络能源有限公司"));
			kgdypp.add(new Option("其他", "其他"));
		}
		return kgdypp;
	}

	public static List<Option> pdxpp() {
		if (pdxpp == null) {
			pdxpp = new ArrayList<Option>();
			pdxpp.add(new Option("正泰", "正泰"));
			pdxpp.add(new Option("浙宝", "浙宝"));
			pdxpp.add(new Option("隆兴", "隆兴"));
			pdxpp.add(new Option("华通", "华通"));
			pdxpp.add(new Option("荣联", "荣联"));
			pdxpp.add(new Option("精益", "精益"));
			pdxpp.add(new Option("天洲", "天洲"));
			pdxpp.add(new Option("移动定制", "移动定制"));
			pdxpp.add(new Option("康普盾", "康普盾"));
			pdxpp.add(new Option("电信器材厂", "电信器材厂"));

			pdxpp.add(new Option("其他", "其他"));
		}
		return pdxpp;
	}

	public static List<Option> txlx() {
		if (txlx == null) {
			txlx = new ArrayList<Option>();
			txlx.add(new Option("移动2G", "移动2G"));
			txlx.add(new Option("移动3G", "移动3G"));
			txlx.add(new Option("移动4G", "移动4G"));
			txlx.add(new Option("联通2G", "联通2G"));
			txlx.add(new Option("联通3G", "联通3G"));
			txlx.add(new Option("联通4G", "联通4G"));
			txlx.add(new Option("电信2G", "电信2G"));
			txlx.add(new Option("电信3G", "电信3G"));
			txlx.add(new Option("电信4G", "电信4G"));
			txlx.add(new Option("其他", "其他"));
		}
		return txlx;
	}

	public static List<Option> getTxzt() {
		if (txzt == null) {
			txzt = new ArrayList<Option>();
			txzt.add(new Option("无", "无"));
			txzt.add(new Option("有", "有"));
		}
		return txzt;
	}

	public static List<Option> getTtpt() {
		if (ttpt == null) {
			ttpt = new ArrayList<Option>();
			ttpt.add(new Option("地面塔", "地面塔"));
			ttpt.add(new Option("屋顶塔", "屋顶塔"));
		}
		return ttpt;
	}

	public static List<Option> getTxpt() {
		if (txpt == null) {
			txpt = new ArrayList<Option>();
			txpt.add(new Option("0", "0"));
			txpt.add(new Option("1", "1"));
			txpt.add(new Option("2", "2"));
			txpt.add(new Option("3", "3"));
			txpt.add(new Option("4", "4"));
			txpt.add(new Option("5", "5"));
			txpt.add(new Option("6", "6"));
		}
		return txpt;
	}

	public static List<Option> getRoomys() {
		if (roomys == null) {
			roomys = new ArrayList<Option>();
			roomys.add(new Option("0", "请选择"));
			roomys.add(new Option("1", "门禁卡"));
			roomys.add(new Option("2", "通用钥匙"));
			roomys.add(new Option("3", "专用钥匙"));
			roomys.add(new Option("4", "物业钥匙"));
		}
		return roomys;
	}

	public static List<Option> getRoomysjj() {
		if (roomysjj == null) {
			roomysjj = new ArrayList<Option>();
			roomysjj.add(new Option("0", "请选择"));
			roomysjj.add(new Option("1", "是"));
			roomysjj.add(new Option("2", "否"));
		}
		return roomysjj;
	}

}
