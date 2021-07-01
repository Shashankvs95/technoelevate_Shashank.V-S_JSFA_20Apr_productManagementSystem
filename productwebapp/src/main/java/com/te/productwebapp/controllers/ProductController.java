package com.te.productwebapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.productwebapp.beans.Admin;
import com.te.productwebapp.beans.ProductResponse;
import com.te.productwebapp.beans.Products;
import com.te.productwebapp.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping(path = "/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ProductResponse authenticate(@RequestBody Admin admin, HttpServletRequest request) {
		ProductResponse response = new ProductResponse();

		if (service.authenticate(admin)) {
			response.setStatusCode(200);
			response.setMsg("Login Successful");
			response.setDescription("Admin found");
			HttpSession session = request.getSession();
			session.setAttribute("loggedIn", admin);

		} else {
			response.setStatusCode(404);
			response.setMsg("Login Failure");
			response.setDescription("Retry to login");
		}

		return response;
	}

	@GetMapping(path = "/getProduct", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ProductResponse getProductData(int pid, @SessionAttribute(name = "loggedIn", required = false) Admin admin) {
		Products product = service.getProductData(pid);
		ProductResponse response = new ProductResponse();
		if (admin != null) {
			if (product != null) {
				response.setStatusCode(200);
				response.setMsg("Success");
				response.setDescription("Product found for id: " + pid);
				response.setProduct(product);
			} else {
				response.setStatusCode(404);
				response.setMsg("Failure");
				response.setDescription("Product not found for id: " + pid);
				response.setProduct(product);
			}
		} else {
			response.setStatusCode(404);
			response.setMsg("Login Failure");
			response.setDescription("Please login first");
		}
		return response;
	}

	@PostMapping(path = "/addProduct", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ProductResponse addProduct(@RequestBody Products product,
			@SessionAttribute(name = "loggedIn", required = false) Admin admin) {
		ProductResponse response = new ProductResponse();
		if (admin != null) {
			if (service.addProduct(product)) {
				response.setStatusCode(200);
				response.setMsg("Success");
				response.setDescription("Product added Successfuly");
				response.setProduct(product);
			} else {
				response.setStatusCode(500);
				response.setMsg("Failure");
				response.setDescription("Product could not be added");
				response.setProduct(product);
			}
		} else {
			response.setStatusCode(404);
			response.setMsg("Login Failure");
			response.setDescription("Please login first");
		}
		return response;
	}

	@PutMapping(path = "/updateProduct", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ProductResponse updateProduct(@RequestBody Products product,
			@SessionAttribute(name = "loggedIn", required = false) Admin admin) {
		ProductResponse response = new ProductResponse();
		if (admin != null) {
			if (service.updateRecord(product)) {
				response.setStatusCode(200);
				response.setMsg("Success");
				response.setDescription("Product updated Successfuly");
			} else {
				response.setStatusCode(500);
				response.setMsg("Failure");
				response.setDescription("Product could not be updated");
			}
		} else {
			response.setStatusCode(404);
			response.setMsg("Login Failure");
			response.setDescription("Please login first");
		}
		return response;
	}

	@DeleteMapping(path = "/deleteProduct/{pid}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ProductResponse deleteProduct(@PathVariable(name = "pid") int pid,
			@SessionAttribute(name = "loggedIn", required = false) Admin admin) {
		ProductResponse response = new ProductResponse();
		if (admin != null) {
			if (service.deleteProductData(pid)) {
				response.setStatusCode(200);
				response.setMsg("Success");
				response.setDescription("Product deleted Successfuly");
			} else {
				response.setStatusCode(500);
				response.setMsg("Failure");
				response.setDescription("Product not exist");
			}
		} else {
			response.setStatusCode(404);
			response.setMsg("Login Failure");
			response.setDescription("Please login first");
		}
		return response;
	}

	@GetMapping(path = "/getAllProducts")
	public ProductResponse getAllProducts(@SessionAttribute(name = "loggedIn", required = false) Admin admin) {
		ProductResponse response = new ProductResponse();
		if (admin != null) {
			List<Products> product = service.getAllProduct();
			if (product != null) {
				response.setStatusCode(200);
				response.setMsg("Success");
				response.setDescription("Product found Successfuly");
				response.setProducts(product);
			} else {
				response.setStatusCode(500);
				response.setMsg("Failure");
				response.setDescription("Product not exist");
			}
		} else {
			response.setStatusCode(404);
			response.setMsg("Login Failure");
			response.setDescription("Please login first");
		}
		return response;
	}
	
	@GetMapping(path = "/logout")
	public ProductResponse logout(HttpSession session)
	{
		session.invalidate();
		ProductResponse response = new ProductResponse();
		response.setStatusCode(200);
		response.setMsg("Logged out");
		response.setDescription("Admin logout successful");
		return response;
	}

}
