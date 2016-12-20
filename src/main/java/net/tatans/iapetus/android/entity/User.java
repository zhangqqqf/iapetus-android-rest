package net.tatans.iapetus.android.entity;

import net.tatans.iapetus.android.entity.base.BaseUser;
public class User extends BaseUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User() {
		// TODO Auto-generated constructor stub
		super();
	}

	public User(Integer id, String userName, String password, String phoneNumber, String enabled) {
		super(id, userName, password, phoneNumber, enabled);
		// TODO Auto-generated constructor stub
	}
	
}
