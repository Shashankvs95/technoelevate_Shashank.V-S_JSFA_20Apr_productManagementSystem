package com.te.productwebapp.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@Entity
@XmlRootElement(name = "Admin-info")
@JsonRootName("Admin-info")
public class Admin implements Serializable{
	
	@Id
	@Column
	@XmlElement
	@JsonProperty
	private int aid;
	
	@Column
	@XmlElement
	@JsonProperty
	private String aname;
	
	@Column
	@XmlElement
	@JsonProperty
	private String password;

}
