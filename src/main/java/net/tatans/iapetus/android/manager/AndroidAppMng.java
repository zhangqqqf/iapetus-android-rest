package net.tatans.iapetus.android.manager;

import java.util.List;

import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.entity.AndroidApp;


public interface AndroidAppMng{

	public AndroidApp findById(Integer id);

	public Pagination findBychannelId(Integer channelId, Integer pageNo, Integer pageSize);
	
	public List<AndroidApp> deleteByIds(Integer[] ids);
	
	public AndroidApp update(AndroidApp bean);

	public boolean save(AndroidApp bean);
	
	public List<AndroidApp> findNewsApps();

	public AndroidApp findNewAppByPackageName(String packagename);

	public Pagination findclassifyAppsByChannelName(String channelName, int pageNo);

	public Pagination searchAppByAppName(String appName,int pageNo);

	public Pagination findSpecifyApps(String appName);

	public Pagination findAppsByTag(String tag, Integer pageNo);
}
