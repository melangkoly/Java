package com.java.phone;

public class PBVO {
	private Long id;
	private String pbName;
	private String pbHp;
	private String pbTel;
	
	public PBVO() {
		
	}
	public PBVO(Long id, String pbName, String pbHp, String pbTel) {
		this.id = id;
		this.pbName = pbName;
		this.pbHp = pbHp;
		this.pbTel = pbTel;
		
		this.pbName = pbName == null ? " " : pbName;
		this.pbHp = pbHp == null ? " " : pbHp;
		this.pbTel = pbTel == null ? " " : pbTel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPbName() {
		return pbName;
	}
	public void setPbName(String pbName) {
		this.pbName = pbName;
	}
	public String getPbHp() {
		return pbHp;
	}
	public void setPbHp(String pbHp) {
		this.pbHp = pbHp;
	}
	public String getPbTel() {
		return pbTel;
	}
	public void setPbTel(String pbTel) {
		this.pbTel = pbTel;
	}
	@Override
	public String toString() {
		return "PBVO [id=" + id + ", pbName=" + pbName + ", pbHp=" + pbHp + ", pbTel=" + pbTel + "]";
	}
	
	
	
}
