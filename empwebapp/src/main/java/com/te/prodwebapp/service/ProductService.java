package com.te.prodwebapp.service;

import java.util.List;

import com.te.prodwebapp.beans.AdminInfo;
import com.te.prodwebapp.beans.Products;

public interface ProductService {

	public AdminInfo authenticate(int id, String pwd);

//	public EmployeeInfoBean getEmployeeData(int id);
	public Products getProductData(int id);

	public boolean deleteProdData(int id);
	
//	public boolean addEmployee(EmployeeInfoBean employeeInfoBean);
	public boolean addProduct(Products product);
	
	public boolean updateRecord(Products product);
	
	public List<Products> getAllProd();
}
