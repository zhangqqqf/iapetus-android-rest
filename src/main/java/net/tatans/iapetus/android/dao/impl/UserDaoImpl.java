package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
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
		return (Integer)getSession().save(user);
	}


	@Override
	public String getUserNameByLogin(String phoneNumber, String password) {
		Finder finder=Finder.create("from User bean where bean.phoneNumber=:phoneNumber and bean.password=:password");
		finder.setParam("phoneNumber",phoneNumber);
		finder.setParam("password",password);
		List<User> list=find(finder);
		if(list.size()==0){
			return null;
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
		Finder finder=Finder.create("from User bean where bean.userName=:userName");
		finder.setParam("userName",userName);
		List<User> list=find(finder);
		if(list.size()==0){
			return null;
		}else{
			return list.get(0).getUserName();
		}
	}
	
}
