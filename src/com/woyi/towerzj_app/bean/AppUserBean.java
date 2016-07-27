package com.woyi.towerzj_app.bean;

import java.util.Date;
import java.util.Map;

/**
 * 
* 此类描述的是：用户
* @author: 罗然
* @version: 2015-7-18 下午5:09:54
* @ClassName: AppUserBean 
* @项目： towerzj_app
* @包：com.woyi.towerzj_app.bean
 */
public class AppUserBean extends AppBaseBean{  
    private Long id;//  
    private String username;//用户名  
    private String password;//密码  
    private String truename;//姓名  
    private String  mobile;//手机  
    private Long roleid;//角色  
    private Date createtime;//创建时间  
    private Long departmentid;//部门  
    private Long manage;//管理权限1有 2无  
    private Long  enable;//可用标志，0=不可用，1=可用 
    
	//未映射库表
	private Date loginDate;//最新登陆时间
	private String department;//部门
	
	private Map<String,String> reMap;	//映射26站点信息
    
	/**
	 * IP地址
	 */
	private String ipAddr;

	/**
	 * Ftp连接参数
	 * 
	 * 
	 * 
	 */
	private String hostFtpName;
	private int portFtp;
	private String userFtpName;
	private String passWordFtp;
	private String pathFtp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Long getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}
	public Long getManage() {
		return manage;
	}
	public void setManage(Long manage) {
		this.manage = manage;
	}
	public Long getEnable() {
		return enable;
	}
	public void setEnable(Long enable) {
		this.enable = enable;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getHostFtpName() {
		return hostFtpName;
	}
	public void setHostFtpName(String hostFtpName) {
		this.hostFtpName = hostFtpName;
	}
	public int getPortFtp() {
		return portFtp;
	}
	public void setPortFtp(int portFtp) {
		this.portFtp = portFtp;
	}
	public String getUserFtpName() {
		return userFtpName;
	}
	public void setUserFtpName(String userFtpName) {
		this.userFtpName = userFtpName;
	}
	public String getPassWordFtp() {
		return passWordFtp;
	}
	public void setPassWordFtp(String passWordFtp) {
		this.passWordFtp = passWordFtp;
	}
	public String getPathFtp() {
		return pathFtp;
	}
	public void setPathFtp(String pathFtp) {
		this.pathFtp = pathFtp;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Map<String, String> getReMap() {
		return reMap;
	}
	public void setReMap(Map<String, String> reMap) {
		this.reMap = reMap;
	}

}
