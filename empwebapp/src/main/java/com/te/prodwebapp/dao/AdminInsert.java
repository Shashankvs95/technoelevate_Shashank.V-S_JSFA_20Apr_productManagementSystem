package com.te.prodwebapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.prodwebapp.beans.AdminInfo;


public class AdminInsert {

	public static void main(String[] args) {

		AdminInfo admin = new AdminInfo();
		admin.setId(100);
		admin.setName("Shashank");
		admin.setPassword("admin");

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction();
			transaction = entityManager.getTransaction();

			transaction.begin();

			entityManager.persist(admin);
			System.out.println("admin data into table");

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

}
