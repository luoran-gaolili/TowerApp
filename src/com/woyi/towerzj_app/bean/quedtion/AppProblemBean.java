package com.woyi.towerzj_app.bean.quedtion;

import java.io.Serializable;

/**
 * 
* 此类描述的是：问题
* @author: 罗然
* @version: 2015-7-20 上午10:20:23
* @ClassName: AppProblemBean 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean.quedtion
 */
public class AppProblemBean implements Serializable{
	private String 	type;	//类型
	private String 	code;	//编码
	private String 	name;	//名称
	private String 	desc;	//
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
