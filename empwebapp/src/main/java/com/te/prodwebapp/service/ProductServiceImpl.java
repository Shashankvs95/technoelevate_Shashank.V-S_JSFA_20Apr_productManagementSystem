package com.te.prodwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.prodwebapp.beans.AdminInfo;
import com.te.prodwebapp.beans.Products;
import com.te.prodwebapp.dao.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	@Override
	public AdminInfo authenticate(int id, String pwd) {
		if (id <= 0) {
			return null;
		} else {
			return dao.authenticate(id, pwd);
		}

	}

	@Override
	public Products getProductData(int id) {
		if (id <= 0) {
			return null;
		}
		return dao.getProductData(id);
	}

	@Override
	public boolean deleteProdData(int id) {

		return dao.deleteProdData(id);
	}

	@Override
	public boolean addProduct(Products product) {
		return dao.addProduct(product);
	}

	@Override
	public boolean updateRecord(Products product) {
		// TODO Auto-generated method stub
		return dao.updateRecord(product);
	}

	@Override
	public List<Products> getAllProd() {

		return dao.getAllProd();
	}

	

}
