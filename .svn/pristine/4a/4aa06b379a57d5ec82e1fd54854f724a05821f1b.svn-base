package net.tatans.iapetus.android.manager.impl;

import java.util.List;

import net.tatans.iapetus.android.dao.AndroidChannelDao;
import net.tatans.iapetus.android.entity.AndroidChannel;
import net.tatans.iapetus.android.manager.AndroidChannelMng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AndroidChannelMngImpl implements AndroidChannelMng{



	@Override
	public AndroidChannel[] deleteByIds(Integer[] ids) {
		return androidChannelDao.deleteByIds(ids);
	}

	@Override
	public AndroidChannel update(AndroidChannel bean) {
		// TODO Auto-generated method stub
		return androidChannelDao.update(bean);
	}

	

	@Override
	public AndroidChannel findById(Integer AndroidChannelId) {
		// TODO Auto-generated method stub
		return androidChannelDao.findById(AndroidChannelId);
	}

	@Override
	public AndroidChannel save(AndroidChannel bean) {
		// TODO Auto-generated method stub
		return androidChannelDao.save(bean);
	}

	

	@Override
	public AndroidChannel delete(Integer id) {
		// TODO Auto-generated method stub
		return androidChannelDao.delete(id);
	}

	@Override
	public List<AndroidChannel> getChildList(int id) {
		// TODO Auto-generated method stub
		return androidChannelDao.getChildList(id);
	}

	@Override
	public List<AndroidChannel> getTopList() {
		// TODO Auto-generated method stub
		return androidChannelDao.getTopList();
	}
	@Autowired
	private AndroidChannelDao androidChannelDao;
}
