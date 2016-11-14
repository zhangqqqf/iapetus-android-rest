package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.common.hibernate3.Updater;
import net.tatans.iapetus.android.dao.AndroidChannelSecDao;
import net.tatans.iapetus.android.entity.AndroidChannelSec;

import org.springframework.stereotype.Repository;

@Repository
public class AndroidChannelSecDaoImpl extends HibernateBaseDao<AndroidChannelSec, Integer> implements AndroidChannelSecDao{

	@Override
	public AndroidChannelSec save(AndroidChannelSec bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public AndroidChannelSec findById(Integer id) {
		return get(id);
	}

	@Override
	public AndroidChannelSec delete(Integer id) {
		AndroidChannelSec entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public AndroidChannelSec[] deleteByIds(Integer[] ids) {
		AndroidChannelSec[] beans = new AndroidChannelSec[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = delete(ids[i]);
		}
		return beans;
	}

	@Override
	public AndroidChannelSec update(AndroidChannelSec bean) {
		Updater<AndroidChannelSec> updater=new Updater<AndroidChannelSec>(bean);
		updateByUpdater(updater);
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AndroidChannelSec> getTopList() {
		Finder f = Finder.create("from AndroidChannelSec bean");
		f.append(" where  bean.parent.id is null");
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AndroidChannelSec> getChildList(int id) {
		Finder f = Finder.create("from AndroidChannelSec bean");
		f.append(" where bean.parent.id=:parentId");
		f.setParam("parentId", id);
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
		
	}

	@Override
	protected Class<AndroidChannelSec> getEntityClass() {
		return AndroidChannelSec.class;
	}
}
