package com.te.productwebapp.dao;

import java.util.List;

import com.te.productwebapp.beans.Admin;
import com.te.productwebapp.beans.Products;

public interface ProductDAO {

	public boolean authenticate(Admin admin);

	public Products getProductData(int id);

	public boolean deleteProductData(int id);

	public boolean addProduct(Products product);

	public boolean updateRecord(Products product);

	public List<Products> getAllProduct();
}
