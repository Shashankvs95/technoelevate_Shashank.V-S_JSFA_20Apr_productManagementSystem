package com.te.prodwebapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.te.prodwebapp.beans.AdminInfo;
import com.te.prodwebapp.customexp.AdminException;



public class AdminDAOHibernateImpl implements AdminDAO 
{

	@Override
	public AdminInfo authenticate(int id, String pwd) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		AdminInfo admininfo = manager.find(AdminInfo.class, id);

		if (admininfo != null) {
			if (admininfo.getPassword().equals(pwd)) {
				return admininfo;
			} else {
				throw new AdminException("Password is wrong");
			}
		} else {
			throw new AdminException("Invalid ID");
		}
	}

}
