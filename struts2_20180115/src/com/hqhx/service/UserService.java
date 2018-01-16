package com.hqhx.service;

import java.util.List;

import com.hqhx.model.User;

public interface UserService {

	/**
	 * ��¼
	 * @param username���û���
	 * @return����ѯ�����û�����
	 */
	public User login(String username);
	
	/**
	 * ע��
	 * @param user:�û�����
	 * @return��>0��ע��ɹ�������ע��ʧ��
	 */
	public int reg(User user);
	
	
	
	/**
	 * ɾ���û�
	 * @param id��Ҫɾ�����û����
	 * @return���������� >0��ʾɾ���ɹ��������ʾɾ��ʧ��
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
	 * ���ݱ�Ų�ѯ�û�����
	 * @param id��Ҫ��ѯ���û��ı��
	 * @return:���ز�ѯ�����û�����
	 */
	public User findUserById(Integer id);
}
