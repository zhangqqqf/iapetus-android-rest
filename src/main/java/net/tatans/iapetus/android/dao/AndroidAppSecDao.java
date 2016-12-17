package net.tatans.iapetus.android.dao;

import java.util.List;

import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.entity.AndroidAppSec;

public interface AndroidAppSecDao {
	public AndroidAppSec findById(Integer id);

	public Pagination findBychannelId(Integer channelId, Integer pageNo, Integer pageSize);
	
	public AndroidAppSec delete(AndroidAppSec bean);
	
	public AndroidAppSec updateByUpdater(Updater<AndroidAppSec> updater);

	public boolean save(AndroidAppSec bean);
	
	public int updateSumDownloadApp(String packageName);
	
	public List<AndroidAppSec> findNewsApps(String mobileModel);
	
	public Pagination findAppsBySumCount(String tag,int pageNo, String mobileModel);
	
	public List<AndroidAppSec> findNewAppByPackageName(String packagename, String mobileModel);

	public Pagination findclassifyAppsByChannelName(String channelName, int pageNo, String mobileModel);

	public Pagination searchAppByAppName(String appName,int pageNo, String mobileModel);

	public Pagination findSpecifyApps(String appName, String mobileModel);

	public Pagination findAppsByTag(String tag,int pageNo, String mobileModel);

	public String validaApp(String imei, String sign);
	
}
