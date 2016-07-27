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

	public static String URL = "http://111.1.58.64:8085/towerzj";//��ʽ����
//			public static String URL = "http://192.168.1.188:80/towerzj";
//		public static String URL = "http://111.1.58.64:8083/towerzj";//���Ի���
	public final static String encode = "utf-8";

	/**
	 * ��������·���Ͳ�����������ִ��
	 * 
	 * @param url
	 * @param params
	 * @return ִ�гɹ������سɹ���ϢMessage��ִ��ʧ�ܣ�����ʧ��ԭ��
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
	 * �˷����������ǣ���¼
	 * 
	 * @Title: login
	 * @author: ��Ȼ
	 * @param username
	 * @param pass
	 * @param versioncode
	 * @return
	 * @throws Exception
	 * @return String ��������
	 * @version: 2015-7-17 ����12:20:52
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
	 * �˷����������ǣ���ȡ�ʲ�����б�
	 * 
	 * @Title: getPhyList
	 * @author: ��Ȼ
	 * @param formBean
	 * @return
	 * @return String ��������
	 * @version: 2015-7-19 ����9:05:37
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
	 * �˷����������ǣ���Φ�б�
	 * 
	 * @Title: getMastList
	 * @author: ��Ȼ
	 * @param idStr
	 * @return
	 * @throws Exception
	 * @return String ��������
	 * @version: 2015-7-19 ����2:22:24
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
	 * �˷����������ǣ���Φ��Ӫ��
	 * 
	 * @Title: getMastcomList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String ��������
	 * @version: 2015-7-19 ����4:14:50
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
	 * �˷����������ǣ�����
	 * 
	 * @Title: getRoomList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @throws Exception
	 * @return String ��������
	 * @version: 2015-7-19 ����5:17:20
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
	 * �˷����������ǣ�������Ӫ��
	 * 
	 * @Title: getRoomcomList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @throws Exception
	 * @return String ��������
	 * @version: 2015-7-19 ����5:49:27
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
	 * �˷����������ǣ��յ�
	 * 
	 * @Title: getAirList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String ��������
	 * @version: 2015-7-19 ����6:14:31
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
	 * �˷����������ǣ��յ���Ӫ��
	 * 
	 * @Title: getAircomList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String ��������
	 * @version: 2015-7-19 ����6:31:43
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
	 * �˷����������ǣ�����
	 * 
	 * @Title: getBatteryList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String ��������
	 * @version: 2015-7-19 ����6:51:02
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
	 * �˷����������ǣ�������Ӫ��
	 * 
	 * @Title: getBatterycomList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String ��������
	 * @version: 2015-7-19 ����7:03:27
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
	 * �˷����������ǣ����ص�Դ
	 * 
	 * @Title: getSwichList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String ��������
	 * @version: 2015-7-20 ����9:33:05
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
	 * �˷����������ǣ����ص�Դ��Ӫ��
	 * 
	 * @Title: getSwichcomList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String ��������
	 * @version: 2015-7-20 ����9:38:48
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
	 * �˷����������ǣ������
	 * 
	 * @Title: getPowerList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @throws Exception
	 * @return String ��������
	 * @version: 2015-7-20 ����10:03:37
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
	 * �˷����������ǣ��������Ӫ��
	 * 
	 * @Title: getPowerComList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @throws Exception
	 * @return String ��������
	 * @version: 2015-7-20 ����10:04:10
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
	 * �˷����������ǣ�����
	 * 
	 * @Title: getProblemList
	 * @author: ��Ȼ
	 * @param flag
	 * @return
	 * @return String ��������
	 * @version: 2015-7-20 ����10:33:16
	 */
	public static String getProblemList(String flag) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "problemlist"));
		String urlRequest = URL + flag;
		return webRequest(urlRequest, params);
	}

	/**
	 * 
	 * �˷����������ǣ���ȡ������������Ϣaction=
	 * 
	 * @Title: getResultList
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String ��������
	 * @version: 2015-7-20 ����5:18:59
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
	 * �˷����������ǣ���ȡ�°汾
	 * 
	 * @Title: getMaxVersion
	 * @author: ��Ȼ
	 * @return
	 * @return String ��������
	 * @version: 2015-7-23 ����2:44:17
	 */
	public static String getMaxVersion() throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "getMaxVersion"));
		String urlRequest = URL + "/appVersion.xp?";
		return webRequest(urlRequest, params);
	}

	/**
	 * ���ܣ���ȡ��Φ������Ӧ��ϵ����
	 */
	public static String getmastRoomData() throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "reBeans"));
		String urlRequest = URL + "/appMast.xp?";
		return webRequest(urlRequest, params);

	}

	/**
	 * 
	 * �˷����������ǣ������޸�
	 * 
	 * @Title: updatePassByUserid
	 * @author: ��Ȼ
	 * @param xmmStr
	 * @return
	 * @return String ��������
	 * @version: 2015-7-23 ����3:36:00
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
	 * �˷����������ǣ��ϴ�����
	 * 
	 * @Title: upLoad
	 * @author: ��Ȼ
	 * @param upBean
	 * @return
	 * @return String ��������
	 * @version: 2015-7-27 ����7:15:49
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
	 * �˷����������ǣ��ϴ���ɺ���ķ�����״̬
	 * @Title: upstats 
	 * @author: ��Ȼ
	 * @param physicCode
	 * @return
	 * @return String    ��������
	 * @version: 2015-8-24
	����6:22:36
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
	* �˷����������ǣ�ͼƬ���
	* @Title: checkPic 
	* @author: ��Ȼ
	* @param physicCode
	* @return
	* @return String    ��������
	* @version: 2015-8-28
	����6:23:53 
	 */
	public static String checkPic(String physicCode) throws Exception {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("physiccode", physicCode));
		params.add(new BasicNameValuePair("action", "checkpic"));
		String urlRequest = URL + "/appProblem.xp?";
		return webRequest(urlRequest, params);
	}

}