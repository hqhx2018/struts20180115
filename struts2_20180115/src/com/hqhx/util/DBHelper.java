package com.hqhx.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBHelper {

	private static String className;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		try {
			//�ȼ��������ļ�����ȡ������ݿ����Ϣ
			Properties p=new Properties();
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
			//ͨ��key��ȡvalue
			className=p.getProperty("jdbc.className");
			url=p.getProperty("jdbc.url");
			username=p.getProperty("jdbc.username");
			password=p.getProperty("jdbc.password");
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��ȡ���ӵķ���
	public Connection getConn(){
		// 3.����ݿ⽨�����ӣ�ͨ�����������ȡ����ݿ�����Ӷ���
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("���ӳɹ�.....");
			} catch (SQLException e) {
				System.out.println("����ʧ��");
			e.printStackTrace();
		}
		return conn;
	}
	
	//insert into dept values (?,?,?)
	//delete from dept where deptno=?
	//
	public int CUD(String sql,Object...params) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		// 1.������
		// 2.��������
		try {
			// 4.ͨ��Connection���Ӷ����ȡһ��Ԥ����������
			ps = conn.prepareStatement(sql);
			//5.��̬��Ԥ�������������ò���
			for(int i=0;i<params.length;i++){
			ps.setObject(i+1, params[i]);
			}
			// 5.ͨ��Ԥ����������ִ��sql���
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 7.�ͷ���Դ
			close(ps,conn);
		}
		return 0;
	}



	//Select * from dept;Dept.Class
	public List query(String sql,Class c,Object...params) {
		for (Object object : params) {
			System.out.println("-----------------");
			System.out.println(object);
		}
		//����һ��List<Dept> depts;
		List entitys=new ArrayList();
		Connection conn = getConn();
		PreparedStatement ps= null;
		ResultSet rs = null;
		// 1.������
		// 2.��������
		try {
			// 4.ͨ��Connection���Ӷ����ȡһ��������
			ps = conn.prepareStatement(sql);
			//5.���ò���
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			// 5.ͨ������������ݿⷢ�;�̬sql���
			rs = ps.executeQuery();
			//��ȡResultSetMetaData���󣬸ö���洢�˲�ѯ�Ľ���е���Ϣ�����������������ֶ���
			ResultSetMetaData rsmd=rs.getMetaData();
			int colCount=rsmd.getColumnCount();
			
			//ȡ�����
			while(rs.next()){
				//��̬����һ������
				Object object=null;
				try {
					object = c.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int i=1;i<=colCount;i++){
					//��ȡ��i�е�ֵ
					Object value=rs.getObject(i);
					//��ȡ��i�е��ֶ���
					String colName=rsmd.getColumnName(i);
					//����������
					String methodName="set"+colName.substring(0,1).toUpperCase()+colName.substring(1);
					System.out.println(methodName);
					//��̬��ȡ����
					if(value!=null){
					Method m=c.getMethod(methodName, value.getClass());
					//ʹ�÷����ȡ������ݶ�̬���õ�object������
					m.invoke(object, value);
					}
				}
				
				//��ӵ�������
				entitys.add(object);
			}
			return entitys;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 7.�ͷ���Դ
			close(rs,ps,conn);
		}
		return null;
	}

	public void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(stmt, conn);
	}


	public void close(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
}
