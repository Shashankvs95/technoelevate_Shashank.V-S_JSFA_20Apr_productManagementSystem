package com.te.productwebapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.te.productwebapp.beans.Admin;
import com.te.productwebapp.beans.Products;
import com.te.productwebapp.customexp.ProductException;

@Repository
public class ProductDAOHibernateImpl implements ProductDAO {

	@Override
	public boolean authenticate(Admin admindata) {

		boolean found = false;

		try {

			EntityManagerFactory emf = null;
			EntityManager entityManager = null;

			emf = Persistence.createEntityManagerFactory("emsPeristenceUnit");
			entityManager = emf.createEntityManager();

			String s = "from Admin where aid=:aid and password=:password";
			Query query = entityManager.createQuery(s);
			query.setParameter("aid", admindata.getAid());
			query.setParameter("password", admindata.getPassword());

			Admin admin = (Admin) query.getSingleResult();

			if (admin != null) {
				found = true;
			} else {
				found = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return found;
	}

	@Override
	public Products getProductData(int id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		Products infoBean = manager.find(Products.class, id);
		manager.close();
		factory.close();
		return infoBean;
	}

	@Override
	public boolean deleteProductData(int id) {
		boolean isDeleted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			Products product = manager.find(Products.class, id);
			manager.remove(product);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return isDeleted;
	}

	@Override
	public boolean addProduct(Products product) {
		boolean isInserted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.persist(product);
			transaction.commit();
			isInserted = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isInserted;
	}

	@Override
	public boolean updateRecord(Products product) {
		boolean isUpdated = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			Products actualInfo = manager.find(Products.class, product.getPid());

			if (product.getPname() != null && product.getPname() != "") {
				actualInfo.setPname(product.getPname());
			}

			if (product.getMgDate() != null) {
				actualInfo.setMgDate(product.getMgDate());
			}

			if (product.getExDate() != null) {
				actualInfo.setExDate(product.getExDate());
			}

			if (product.getPrice() != null) {
				actualInfo.setPrice(product.getPrice());
			}

			if (product.getQuantity() != null) {
				actualInfo.setPrice(product.getQuantity());
			}

			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isUpdated;
	}

	@Override
	public List<Products> getAllProduct() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from Products");
		ArrayList<Products> product = new ArrayList<Products>();
		try {
			product = (ArrayList<Products>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			product = null;
		}

		return product;
	}

}
