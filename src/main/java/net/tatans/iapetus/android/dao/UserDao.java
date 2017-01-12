package net.tatans.iapetus.android.dao;

import net.tatans.iapetus.android.entity.User;

public interface UserDao {
	
	public User getUserByUserName(String UserName);
	
	public int save(User user);
	
	public String getUserNameByLogin(String phoneNumber,String password);
	
	public String getUserNameByPhoneName(String phoneNumber);
	
	public String modifyUserName(String UserName);
	
}
