package net.tatans.iapetus.android.manager.impl;

import java.util.List;

import net.tatans.iapetus.android.dao.AndroidChannelSecDao;
import net.tatans.iapetus.android.entity.AndroidChannelSec;
import net.tatans.iapetus.android.manager.AndroidChannelSecMng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AndroidChannelSecMngImpl implements AndroidChannelSecMng{



	@Override
	public AndroidChannelSec[] deleteByIds(Integer[] ids) {
		return androidChannelSecDao.deleteByIds(ids);
	}

	@Override
	public AndroidChannelSec update(AndroidChannelSec bean) {
		// TODO Auto-generated method stub
		return androidChannelSecDao.update(bean);
	}

	

	@Override
	public AndroidChannelSec findById(Integer AndroidChannelId) {
		// TODO Auto-generated method stub
		return androidChannelSecDao.findById(AndroidChannelId);
	}

	@Override
	public AndroidChannelSec save(AndroidChannelSec bean) {
		// TODO Auto-generated method stub
		return androidChannelSecDao.save(bean);
	}

	

	@Override
	public AndroidChannelSec delete(Integer id) {
		// TODO Auto-generated method stub
		return androidChannelSecDao.delete(id);
	}

	@Override
	public List<AndroidChannelSec> getChildList(int id) {
		// TODO Auto-generated method stub
		return androidChannelSecDao.getChildList(id);
	}

	@Override
	public List<AndroidChannelSec> getTopList() {
		// TODO Auto-generated method stub
		return androidChannelSecDao.getTopList();
	}
	@Autowired
	private AndroidChannelSecDao androidChannelSecDao;
}
