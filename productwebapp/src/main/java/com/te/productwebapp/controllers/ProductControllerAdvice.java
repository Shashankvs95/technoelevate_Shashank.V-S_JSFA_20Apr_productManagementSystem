package com.te.productwebapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.te.productwebapp.customexp.ProductException;

@ControllerAdvice
public class ProductControllerAdvice {

	@ExceptionHandler(ProductException.class)
	public String handleExp(ProductException exception,HttpServletRequest req ) {
		req.setAttribute("errMsg", exception.getMessage());
		return "empLogin";
	}// 

}
