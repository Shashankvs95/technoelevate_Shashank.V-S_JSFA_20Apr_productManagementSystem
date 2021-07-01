package com.te.productwebapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.productwebapp.beans.Admin;

public class AdminDAO {
	
	public static void main(String[] args) {
		
		Admin admin = new Admin();
		admin.setAid(10);
		admin.setAname("admin");
		admin.setPassword("admin");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		
		manager.persist(admin);
		
		transaction.commit();
	}

}
