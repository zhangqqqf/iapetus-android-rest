package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.common.hibernate3.Updater;
import net.tatans.iapetus.android.dao.UserDao;
import net.tatans.iapetus.android.entity.User;

@Repository
public class UserDaoImpl extends HibernateBaseDao<User, Integer> implements UserDao {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}


	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
				Finder finder=Finder.create("from User bean where bean.userName=:userName");
				finder.setParam("userName",userName);
				List<User> list=find(finder);
				if(list.size()==0){
					return null;
				}else{
					return list.get(0);
				}
	}


	@Override
	public int saveUser(User user) {
		Integer integer =(Integer)getSession().save(user);
		if(integer==1){
			return user.getId();
		}else{
			return 0;
		}
	}


	@Override
	public String getUserNameByLogin(String phoneNumber, String password) {
		Finder finder=Finder.create("from User bean where bean.phoneNumber=:phoneNumber and bean.password=:password");
		finder.setParam("phoneNumber",phoneNumber);
		finder.setParam("password",password);
		List<User> list=find(finder);
		if(list.size()==0){
			return "false";
		}else{
			return list.get(0).getUserName();
		}
	}


	@Override
	public String getUserNameByPhoneName(String phoneNumber) {
		Finder finder=Finder.create("from User bean where bean.phoneNumber=:phoneNumber");
		finder.setParam("phoneNumber",phoneNumber);
		List<User> list=find(finder);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0).getUserName();
		}
	}


	@Override
	public String updateUserName(String modifyUserName,String userName) {
		User updateUser= findUniqueByProperty("userName",modifyUserName);
		if(null!=updateUser){
			//用户名已经存在了。
			return "2";
		}
		User user=findUniqueByProperty("userName",userName);
		if(null==user){
			//用户名不存在。
			return "3";
		}
		user.setUserName(modifyUserName);
		Updater<User> up = new Updater<User>(user);
		updateByUpdater(up);
		return "true";
	}


	@Override
	public String registerUser(User user) {
		User updateUser= findUniqueByProperty("phoneNumber",user.getPhoneNumber());
		if(null!=updateUser){
			//用手机号已经存在了。
			return "2";
		}
		String userName;
		Integer integer =(Integer)getSession().save(user);
		userName="嗨"+user.getId();
		user.setUserName(userName);
		getSession().saveOrUpdate(user);
		return userName;
	}


	@Override
	public String updatePassword(String modifyPassword, String phoneNumber) {
		User user=findUniqueByProperty("phoneNumber",phoneNumber);
		if(null==user){
			//手机号。
			return "3";
		}
		user.setPassword(modifyPassword);;
		Updater<User> up = new Updater<User>(user);
		updateByUpdater(up);
		return "true";
	}
	
}
