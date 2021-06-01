package com.java.phone;

public class PBVO {
	private Long id; // PK
	private String name;
	private String hp;
	private String tel;
	
	public PBVO(){
		// 기본 생성자
	}
	
	public PBVO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	PBVO(Long id, String name, String hp, String tel){
		this(id, name);
		this.hp = hp;
		this.tel = tel;
		
		this.name = name == null ? " " : name;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? " " : name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "PBVO [id=" + id + ", name=" + name + ", hp=" + hp + ", tel=" + tel + "]";
	}
	
}
