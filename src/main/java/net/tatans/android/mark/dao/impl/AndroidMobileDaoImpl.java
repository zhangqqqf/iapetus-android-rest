package net.tatans.android.mark.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.mark.dao.AndroidMobileDao;
import net.tatans.android.mark.entity.AndroidMobileID;

@Repository
public class AndroidMobileDaoImpl extends HibernateBaseDao<AndroidMobileID, Integer> implements AndroidMobileDao{

	@Override
	protected Class<AndroidMobileID> getEntityClass() {
		// TODO Auto-generated method stub
		return AndroidMobileID.class;
	}

	@Override
	public boolean findMobileId(String mobileId) {
		Finder finder=Finder.create("from AndroidMobileID bean where bean.mobileId=:mobile_id ");
		finder.setParam("mobile_id", mobileId);
		List<AndroidMobileID> list=find(finder);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

}
