package org.annotatorjs.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Store {
	
	private String name;
	private String version;
	
	public Store(){};
	
	public Store(String name, String version){
		
		this.name=name;
		this.version=version;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	

}
