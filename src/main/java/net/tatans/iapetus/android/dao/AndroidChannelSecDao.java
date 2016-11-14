package net.tatans.iapetus.android.dao;

import java.util.List;

import net.tatans.iapetus.android.entity.AndroidChannelSec;

public interface AndroidChannelSecDao {
	public AndroidChannelSec save(AndroidChannelSec bean);

	public AndroidChannelSec findById(Integer id);

	public AndroidChannelSec delete(Integer id);

	public AndroidChannelSec[] deleteByIds(Integer[] ids);

	public AndroidChannelSec update(AndroidChannelSec bean);

	public List<AndroidChannelSec> getTopList();

	public List<AndroidChannelSec> getChildList(int id);
	
}
