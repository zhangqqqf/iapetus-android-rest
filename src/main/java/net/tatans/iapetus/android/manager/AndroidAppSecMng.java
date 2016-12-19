package net.tatans.iapetus.android.manager;

import java.util.List;

import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.Comment;
import net.tatans.iapetus.android.entity.SumDownLoadApp;


public interface AndroidAppSecMng{

	public AndroidAppSec findById(Integer id);

	public Pagination findBychannelId(Integer channelId, Integer pageNo, Integer pageSize);
	
	public List<AndroidAppSec> deleteByIds(Integer[] ids);
	
	public AndroidAppSec update(AndroidAppSec bean);

	public boolean save(AndroidAppSec bean);
	
	public int updateSumDownloadApp(String packageName);
	
	public List<AndroidAppSec> findNewsApps(String mobileModel);

	public List<AndroidAppSec> findNewAppByPackageName(String packagename, String mobileModel);

	public Pagination findclassifyAppsByChannelName(String channelName, int pageNo, String mobileModel);

	public Pagination searchAppByAppName(String appName,int pageNo, String mobileModel);

	public Pagination findSpecifyApps(String appName, String mobileModel);

	public Pagination findAppsByTag(String tag, Integer pageNo, String mobileModel);
	
	public Pagination findAppsBySumCount(String tag, Integer pageNo, String mobileModel);

	public String validaApp(String imei, String sign);
	
	public boolean saveCommentApp(String userName,int packageId,String versionName,String comment);
	
	public List<Comment> getUserCommentApp(int packageId,String versionName);
}
