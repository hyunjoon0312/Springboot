package org.bigdatacenter.datalink.dto;

public class InfoDTO {

	private String IRB;
	private String Rname;
	private String requestORG;
	private String linkedORG;
	
	public String getIRB() {
		return IRB;
	}
	public void setIRB(String iRB) {
		IRB = iRB;
	}
	public String getRname() {
		return Rname;
	}
	public void setRname(String rname) {
		Rname = rname;
	}
	public String getRequestORG() {
		return requestORG;
	}
	public void setRequestORG(String requestORG) {
		this.requestORG = requestORG;
	}
	public String getLinkedORG() {
		return linkedORG;
	}
	public void setLinkedORG(String linkedORG) {
		this.linkedORG = linkedORG;
	}
	@Override
	public String toString() {
		return "InfoDTO [IRB=" + IRB + ", Rname=" + Rname + ", requestORG=" + requestORG + ", linkedORG=" + linkedORG
				+ "]";
	}
	
}
