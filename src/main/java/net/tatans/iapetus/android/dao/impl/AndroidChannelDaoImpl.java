package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.common.hibernate3.Updater;
import net.tatans.iapetus.android.dao.AndroidChannelDao;
import net.tatans.iapetus.android.entity.AndroidChannel;

import org.springframework.stereotype.Repository;

@Repository
public class AndroidChannelDaoImpl extends HibernateBaseDao<AndroidChannel, Integer> implements AndroidChannelDao{

	@Override
	public AndroidChannel save(AndroidChannel bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public AndroidChannel findById(Integer id) {
		return get(id);
	}

	@Override
	public AndroidChannel delete(Integer id) {
		AndroidChannel entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public AndroidChannel[] deleteByIds(Integer[] ids) {
		AndroidChannel[] beans = new AndroidChannel[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = delete(ids[i]);
		}
		return beans;
	}

	@Override
	public AndroidChannel update(AndroidChannel bean) {
		Updater<AndroidChannel> updater=new Updater<AndroidChannel>(bean);
		updateByUpdater(updater);
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AndroidChannel> getTopList() {
		Finder f = Finder.create("from AndroidChannel bean");
		f.append(" where  bean.parent.id is null");
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AndroidChannel> getChildList(int id) {
		Finder f = Finder.create("from AndroidChannel bean");
		f.append(" where bean.parent.id=:parentId");
		f.setParam("parentId", id);
		f.append(" order by bean.priority asc,bean.id asc");
		return find(f);
		
	}

	@Override
	protected Class<AndroidChannel> getEntityClass() {
		return AndroidChannel.class;
	}
}
