package com.woyi.towerzj_app.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.woyi.towerzj_app.bean.ResultUpList;
import com.woyi.towerzj_app.bean.form.ZcqcQueryForm;

public class Function {

	public static String URL = "http://111.1.58.64:8085/towerzj";//正式环境
//			public static String URL = "http://192.168.1.188:80/towerzj";
//		public static String URL = "http://111.1.58.64:8083/towerzj";//测试环境
	public final static String encode = "utf-8";

	/**
	 * 根据请求路径和参数请求服务端执行
	 * 
	 * @param url
	 * @param params
	 * @return 执行成功，返回成功消息Message，执行失败，返回失败原因
	 * @throws Exception
	 */
	public static String webRequest(String urlRequest,ArrayList<NameValuePair> params) throws Exception {
		String strResult = "";
		HttpPost httpRequest=new HttpPost(urlRequest);
		httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse httpResponse = new DefaultHttpClient()
		.execute(httpRequest);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			strResult = URLDecoder.decode(
					EntityUtils.toString(httpResponse.getEntity()), encode);
		} else {
			strResult = httpResponse.getStatusLine().toString();
		}
		return strResult;
	}

	/**
	 * 
	 * 此方法描述的是：登录
	 * 
	 * @Title: login
	 * @author: 罗然
	 * @param username
	 * @param pass
	 * @param versioncode
	 * @return
	 * @throws Exception
	 * @return String 返回类型
	 * @version: 2015-7-17 下午12:20:52
	 */
	public static String login(String username, String pass) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("code", username));
		params.add(new BasicNameValuePair("pass", pass));
		// params.add(new BasicNameValuePair("ch", ApplicationData.CH));
		params.add(new BasicNameValuePair("action", "login"));
		String urlRequest = URL + "/appUser.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：获取资产清查列表
	 * 
	 * @Title: getPhyList
	 * @author: 罗然
	 * @param formBean
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-19 上午9:05:37
	 */
	public static String getPhyList(ZcqcQueryForm formBean) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		ObjectMapper om = new ObjectMapper();
		params.add(new BasicNameValuePair("formBean", URLEncoder.encode(
				om.writeValueAsString(formBean), encode)));
		params.add(new BasicNameValuePair("action", "list"));
		String urlRequest = URL + "/appPhyInfo.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：塔桅列表
	 * 
	 * @Title: getMastList
	 * @author: 罗然
	 * @param idStr
	 * @return
	 * @throws Exception
	 * @return String 返回类型
	 * @version: 2015-7-19 下午2:22:24
	 */
	public static String getMastList(String idStr) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", idStr));
		params.add(new BasicNameValuePair("action", "list"));
		String urlRequest = URL + "/appMast.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：塔桅运营商
	 * 
	 * @Title: getMastcomList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-19 下午4:14:50
	 */
	public static String getMastcomList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "comlist"));
		String urlRequest = URL + "/appMast.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：机房
	 * 
	 * @Title: getRoomList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @throws Exception
	 * @return String 返回类型
	 * @version: 2015-7-19 下午5:17:20
	 */
	public static String getRoomList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "list"));
		String urlRequest = URL + "/appRoom.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：机房运营商
	 * 
	 * @Title: getRoomcomList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @throws Exception
	 * @return String 返回类型
	 * @version: 2015-7-19 下午5:49:27
	 */
	public static String getRoomcomList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "comlist"));
		String urlRequest = URL + "/appRoom.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：空调
	 * 
	 * @Title: getAirList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-19 下午6:14:31
	 */
	public static String getAirList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "list"));
		String urlRequest = URL + "/appAir.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：空调运营商
	 * 
	 * @Title: getAircomList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-19 下午6:31:43
	 */
	public static String getAircomList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "comlist"));
		String urlRequest = URL + "/appAir.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：蓄电池
	 * 
	 * @Title: getBatteryList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-19 下午6:51:02
	 */
	public static String getBatteryList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "list"));
		String urlRequest = URL + "/appBattery.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：蓄电池运营商
	 * 
	 * @Title: getBatterycomList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-19 下午7:03:27
	 */
	public static String getBatterycomList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "comlist"));
		String urlRequest = URL + "/appBattery.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：开关电源
	 * 
	 * @Title: getSwichList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-20 上午9:33:05
	 */
	public static String getSwichList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "list"));
		String urlRequest = URL + "/appSwitch.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：开关电源运营商
	 * 
	 * @Title: getSwichcomList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-20 上午9:38:48
	 */
	public static String getSwichcomList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "comlist"));
		String urlRequest = URL + "/appSwitch.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：配电箱
	 * 
	 * @Title: getPowerList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @throws Exception
	 * @return String 返回类型
	 * @version: 2015-7-20 上午10:03:37
	 */
	public static String getPowerList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "list"));
		String urlRequest = URL + "/appPower.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：配电箱运营商
	 * 
	 * @Title: getPowerComList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @throws Exception
	 * @return String 返回类型
	 * @version: 2015-7-20 上午10:04:10
	 */
	public static String getPowerComList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("action", "comlist"));
		String urlRequest = URL + "/appPower.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：问题
	 * 
	 * @Title: getProblemList
	 * @author: 罗然
	 * @param flag
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-20 上午10:33:16
	 */
	public static String getProblemList(String flag) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "problemlist"));
		String urlRequest = URL + flag;
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：获取服务器数据信息action=
	 * 
	 * @Title: getResultList
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-20 下午5:18:59
	 */
	public static String getResultList(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", physicCode));
		params.add(new BasicNameValuePair("userid", ApplicationData.user
				.getId() + ""));
		params.add(new BasicNameValuePair("action", "resultlist"));
		String urlRequest = URL + "/appPhyInfo.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：获取新版本
	 * 
	 * @Title: getMaxVersion
	 * @author: 罗然
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-23 下午2:44:17
	 */
	public static String getMaxVersion() throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "getMaxVersion"));
		String urlRequest = URL + "/appVersion.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 功能：获取塔桅机房对应关系数据
	 */
	public static String getmastRoomData() throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "reBeans"));
		String urlRequest = URL + "/appMast.xp?";
		return webRequest(urlRequest, params);

	}

	/**
	 * 
	 * 此方法描述的是：密码修改
	 * 
	 * @Title: updatePassByUserid
	 * @author: 罗然
	 * @param xmmStr
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-23 下午3:36:00
	 */
	public static String updatePassByUserid(String xmmStr) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", ApplicationData.user
				.getId() + ""));
		params.add(new BasicNameValuePair("xmm", xmmStr));
		params.add(new BasicNameValuePair("action", "updatePassByUserid"));
		String urlRequest = URL + "/appUser.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：上传数据
	 * 
	 * @Title: upLoad
	 * @author: 罗然
	 * @param upBean
	 * @return
	 * @return String 返回类型
	 * @version: 2015-7-27 下午7:15:49
	 */
	public static String upLoad(ResultUpList upBean) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		ObjectMapper om = new ObjectMapper();
		params.add(new BasicNameValuePair("formBean", URLEncoder.encode(
				om.writeValueAsString(upBean), encode)));
		params.add(new BasicNameValuePair("username", ApplicationData.user
				.getUsername()));
		params.add(new BasicNameValuePair("action", "uploadinfo"));
		String urlRequest = URL + "/appPhyInfo.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * 此方法描述的是：上传完成后更改服务器状态
	 * @Title: upstats 
	 * @author: 罗然
	 * @param physicCode
	 * @return
	 * @return String    返回类型
	 * @version: 2015-8-24
	下午6:22:36
	 */
	public static String upstats(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("physiccode", physicCode));
		params.add(new BasicNameValuePair("action", "updateStatus"));
		String urlRequest = URL + "/appPhyInfo.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	* 此方法描述的是：图片检查
	* @Title: checkPic 
	* @author: 罗然
	* @param physicCode
	* @return
	* @return String    返回类型
	* @version: 2015-8-28
	下午6:23:53 
	 */
	public static String checkPic(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("physiccode", physicCode));
		params.add(new BasicNameValuePair("action", "checkpic"));
		String urlRequest = URL + "/appProblem.xp?";
		return webRequest(urlRequest, params);
	}

}