package net.tatans.iapetus.android.dao;

import net.tatans.iapetus.android.entity.User;

public interface UserDao {
	
	public User getUserByUserName(String name);

}
