package com.te.prodwebapp.dao;

import com.te.prodwebapp.beans.AdminInfo;

public interface AdminDAO 
{
	public AdminInfo authenticate(int id, String pwd);
}
