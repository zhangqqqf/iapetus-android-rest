package net.tatans.iapetus.android.dao;

import java.util.List;

import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.Version;

public interface VersionDao {
	public Version findById(Integer id);

	public Pagination findBychannelId(Integer channelId, Integer pageNo, Integer pageSize);
	
	public Version delete(AndroidAppSec bean);
	
	public Version updateByUpdater(Updater<Version> updater);

	public boolean save(Version bean);
	
	public int updateSumDownloadApp(String packageName);
	
	public List<Version> findNewsApps(String mobileModel);
	
	
	public List<Version> findNewAppByPackageName(String packagename, String mobileModel);

	
	public boolean saveCommentApp(int userId,int packageId,String versionName);
}
