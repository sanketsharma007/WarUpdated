/*
 * Created on Nov 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.cris.cms.image.actionforms;

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
	private String back = null;
	private String ok = null;
	private String output = null;
	private String crewid = "TEST";
	private String crewname = null;
	private String crewdivision = null;
	private String crewzone = null;
	private String finger = null;
	private String first_finger = null;
	private String second_finger = null;
	private String reregistration = "false";
	private String timeout = "10";
	private String barepeat="false";
	private String crewstatus = null;
	private String signonid = null;
	private String camstatus = null;
	
	
	
	

	public String getCamstatus() {
		return camstatus;
	}

	public void setCamstatus(String camstatus) {
		this.camstatus = camstatus;
	}

	public String getCrewname() {
		return crewname;
	}

	public void setCrewname(String crewname) {
		this.crewname = crewname;
	}

	public String getCrewdivision() {
		return crewdivision;
	}

	public void setCrewdivision(String crewdivision) {
		this.crewdivision = crewdivision;
	}

	public String getCrewzone() {
		return crewzone;
	}

	public void setCrewzone(String crewzone) {
		this.crewzone = crewzone;
	}

	public String getCrewstatus() {
		return crewstatus;
	}

	public void setCrewstatus(String crewstatus) {
		this.crewstatus = crewstatus;
	}

	public String getSignonid() {
		return signonid;
	}

	public void setSignonid(String signonid) {
		this.signonid = signonid;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getBarepeat() {
		return barepeat;
	}

	public void setBarepeat(String barepeat) {
		this.barepeat = barepeat;
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

	public String getFirst_finger() {
		return first_finger;
	}

	public void setFirst_finger(String first_finger) {
		this.first_finger = first_finger;
	}

	public String getSecond_finger() {
		return second_finger;
	}

	public void setSecond_finger(String second_finger) {
		this.second_finger = second_finger;
	}

	public String getReregistration() {
		return reregistration;
	}

	public void setReregistration(String reregistration) {
		this.reregistration = reregistration;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	
	
	
}