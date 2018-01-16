package com.hqhx.dao;

import java.util.List;

import com.hqhx.model.User;

public interface UserDao {

	/**
	 * ����û���Ϣ
	 * @param user���û�����
	 * @return���������� >0��ʾ��ӳɹ��������ʾ���ʧ��
	 */
	public int addUser(User user);

	/**
	 * ɾ���û�
	 * @param id��Ҫɾ����û����
	 * @return���������� >0��ʾɾ��ɹ��������ʾɾ��ʧ��
	 */
	public int deleteUserById(Integer id);
	/**
	 * �޸��û���Ϣ
	 * @param user���µ��û���Ϣ
	 * @return���������� >0��ʾ�޸ĳɹ��������ʾ�޸�ʧ��
	 */
	public int updateUser(User user);
	
	
	/**
	 * ��ѯ�����û���Ϣ
	 * @return�����������û���Ϣ
	 */
	public List<User> listUser();
	
	/**
	 * ��ݱ�Ų�ѯ�û�����
	 * @param id��Ҫ��ѯ���û��ı��
	 * @return:���ز�ѯ�����û�����
	 */
	public User findUserById(Integer id);

	/**
	 * ����û����ѯ�û�����
	 * @param username��Ҫ��ѯ���û��ı��
	 * @return:���ز�ѯ�����û�����
	 */
	public User findUserByName(String username);
	

}
