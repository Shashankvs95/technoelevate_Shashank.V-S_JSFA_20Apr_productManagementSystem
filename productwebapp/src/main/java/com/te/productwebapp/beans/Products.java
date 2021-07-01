package com.te.productwebapp.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Columns;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
@XmlRootElement(name = "product-info")
@JsonRootName("product-info")
public class Products implements Serializable {
	@Id
	@Column
	@XmlElement
	@JsonProperty
	private Integer pid;
	
	@Column
	@XmlElement
	@JsonProperty
	private String pname;
	
	@Column
	@XmlElement
	@JsonProperty
	private Date mgDate;
	
	@Column
	@XmlElement
	@JsonProperty
	private Date exDate;
	
	@Column
	@XmlElement
	@JsonProperty
	private Integer price;
	
	@Column
	@XmlElement
	@JsonProperty
	private Integer quantity;
}
