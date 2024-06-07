/*
 * Created on Nov 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cris.cms.image.actionforms;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.struts.action.ActionForm;

/**
 * @author cms213
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoginForm extends ActionForm{
	
	/**
	 * @return Returns the railwayList.
	 */
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	

	
	private String method = null;
	private String backbtn = null;
	private String removebtn = null;
	private String output = null;
	private String crewid = null;
	private String finger = null;
	private String result = null;
	private String device_status = null;
	private String device_name = null;
	private String device_type = null;
	private String peer_ip = null;
	private String peer_ip_select = null;
	private String username = null;
	private String name = null;
	private String password = null;
	private String oldpassword = null;
	private String re_password = null;
	private String message = null;
	private String timeout = null;
	private ArrayList row;
	private ArrayList peers;
	private String ipaddress;
	private String subnetmask;
	private String gateway;
	private String netinterface;
	
	
	private String sms_name;
	private String sms_mobile;
	private String sms_designation;
	private String sms_group;
	
	private String[] smsselect;
	private String[] smsselectvalue;
	private String[] smsname;
	private String[] smsmobile;
	private String[] smsdesignation;
	private String[] smsgroup;

	
	
	
	
	
	public String[] getSmsselectvalue() {
		return smsselectvalue;
	}

	public void setSmsselectvalue(String[] smsselectvalue) {
		this.smsselectvalue = smsselectvalue;
	}

	public String getRemovebtn() {
		return removebtn;
	}

	public void setRemovebtn(String removebtn) {
		this.removebtn = removebtn;
	}

	public String[] getSmsselect() {
		return smsselect;
	}

	public void setSmsselect(String[] smsselect) {
		this.smsselect = smsselect;
	}

	public String getSms_name() {
		return sms_name;
	}

	public void setSms_name(String sms_name) {
		this.sms_name = sms_name;
	}

	public String getSms_mobile() {
		return sms_mobile;
	}

	public void setSms_mobile(String sms_mobile) {
		this.sms_mobile = sms_mobile;
	}

	public String getSms_designation() {
		return sms_designation;
	}

	public void setSms_designation(String sms_designation) {
		this.sms_designation = sms_designation;
	}

	public String getSms_group() {
		return sms_group;
	}

	public void setSms_group(String sms_group) {
		this.sms_group = sms_group;
	}

	public String getBackbtn() {
		return backbtn;
	}

	public void setBackbtn(String backbtn) {
		this.backbtn = backbtn;
	}

	public String getNetinterface() {
		return netinterface;
	}

	public void setNetinterface(String netinterface) {
		this.netinterface = netinterface;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getSubnetmask() {
		return subnetmask;
	}

	public void setSubnetmask(String subnetmask) {
		this.subnetmask = subnetmask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getRe_password() {
		return re_password;
	}

	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getCrewid() {
		return crewid;
	}

	public void setCrewid(String crewid) {
		this.crewid = crewid;
	}

	public String getFinger() {
		return finger;
	}

	public void setFinger(String finger) {
		this.finger = finger;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDevice_status() {
		return device_status;
	}

	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}

	public ArrayList getRow() {
		return row;
	}

	public void setRow(ArrayList row) {
		this.row = row;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getPeer_ip() {
		return peer_ip;
	}

	public void setPeer_ip(String peer_ip) {
		this.peer_ip = peer_ip;
	}

	public ArrayList getPeers() {
		return peers;
	}

	public void setPeers(ArrayList peers) {
		this.peers = peers;
	}

	public String getPeer_ip_select() {
		return peer_ip_select;
	}

	public void setPeer_ip_select(String peer_ip_select) {
		this.peer_ip_select = peer_ip_select;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	





	public String[] getSmsname() {
		return smsname;
	}

	public void setSmsname(String[] smsname) {
		this.smsname = smsname;
	}

	public String[] getSmsmobile() {
		return smsmobile;
	}

	public void setSmsmobile(String[] smsmobile) {
		this.smsmobile = smsmobile;
	}

	public String[] getSmsdesignation() {
		return smsdesignation;
	}

	public void setSmsdesignation(String[] smsdesignation) {
		this.smsdesignation = smsdesignation;
	}

	public String[] getSmsgroup() {
		return smsgroup;
	}

	public void setSmsgroup(String[] smsgroup) {
		this.smsgroup = smsgroup;
	}







	public class Myrow
	{
		
		private String smsselect;
		private String smsselectvalue;
		private String smsname;
		private String smsmobile;
		private String smsdesignation;
		private String smsgroup;
		
	
		
		public String getSmsselectvalue() {
			return smsselectvalue;
		}
		public void setSmsselectvalue(String smsselectvalue) {
			this.smsselectvalue = smsselectvalue;
		}
		public String getSmsselect() {
			return smsselect;
		}
		public void setSmsselect(String smsselect) {
			this.smsselect = smsselect;
		}
		public String getSmsname() {
			return smsname;
		}
		public void setSmsname(String smsname) {
			this.smsname = smsname;
		}
		public String getSmsmobile() {
			return smsmobile;
		}
		public void setSmsmobile(String smsmobile) {
			this.smsmobile = smsmobile;
		}
		public String getSmsdesignation() {
			return smsdesignation;
		}
		public void setSmsdesignation(String smsdesignation) {
			this.smsdesignation = smsdesignation;
		}
		public String getSmsgroup() {
			return smsgroup;
		}
		public void setSmsgroup(String smsgroup) {
			this.smsgroup = smsgroup;
		}
		
		
	
	
	
	}
	
	
	
	
	public ArrayList getMyrows()
	{
	int len=smsname.length;
	
	
	ArrayList selrow=new ArrayList();
	try
	{
	
		
		if(len!=0)
		{
			for(int i=0; i < smsname.length ;i++)
			{
			LoginForm.Myrow rowobj=this.new Myrow();
				try{
			
					//if(smsselect != null){	
						rowobj.smsselect = smsselect[i].trim();	
						//}

					if(smsselectvalue != null){	
							rowobj.smsselectvalue = (smsselectvalue[i]== null) ? "N" : "Y";	
					}
						
					if(smsname != null){	
					rowobj.smsname = (smsname[i]== null) ? "" : smsname[i].trim();	
					}
						
					if(smsmobile != null){	
					rowobj.smsmobile = (smsmobile[i]== null) ? "" : smsmobile[i].trim();	
					}
	
					if(smsdesignation != null){	
					rowobj.smsdesignation = (smsdesignation[i]== null) ? "" : smsdesignation[i].trim();	
					}
					if(smsgroup != null){	
					rowobj.smsgroup = (smsgroup[i]== null) ? "" : smsgroup[i].trim();	
					}
	
				}catch(Exception e)
				{	
				e.printStackTrace();
				}
				
			selrow.add(rowobj);
			}
		}
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return selrow;
	}




	
	
	
	
}