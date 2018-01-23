package com.hqhx.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	
	private static SessionFactory sf=null;
	private static Configuration cfg=null;
	
	static{
		cfg=new Configuration().configure();
		sf=cfg.buildSessionFactory();
		System.out.println(sf);
	}
	
	public static SessionFactory getSessionFactory(){
		if(sf==null){
			sf=cfg.buildSessionFactory();
		}
		return sf;
	}

}
