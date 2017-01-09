package net.tatans.iapetus.android.manager;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.AndroidChannel;
import net.tatans.iapetus.android.entity.AndroidChannelSec;
import net.tatans.iapetus.android.entity.Comment;
import net.tatans.iapetus.android.entity.User;
import net.tatans.iapetus.android.entity.Version;


public interface AndroidAppSecMng{

	public AndroidAppSec findById(Integer id);
	
	public AndroidChannelSec findByChannelId(Integer AndroidChannelId); 
	
	public Pagination findBychannelId(Integer channelId, Integer pageNo, Integer pageSize);
	
	public List<AndroidAppSec> deleteByIds(Integer[] ids);
	
	public AndroidAppSec update(AndroidAppSec bean);

	public boolean save(AndroidAppSec bean);
	
	public int updateSumDownloadApp(String packageName);
	
	public List<AndroidAppSec> findNewsApps(String mobileModel);

	public List<AndroidAppSec> findNewAppByPackageName(String packagename);
	
	public List<AndroidAppSec> findNewAppByPackageName(String packagename, String mobileModel);

	public Pagination findclassifyAppsByChannelName(String channelName, int pageNo, String mobileModel);

	public Pagination searchAppByAppName(String appName,int pageNo, String mobileModel);

	public Pagination findSpecifyApps(String appName, String mobileModel);

	public Pagination findAppsByTag(String tag, Integer pageNo, String mobileModel);
	
	public Pagination findAppsBySumCount(String tag, Integer pageNo, String mobileModel);

	public String validaApp(String imei, String sign);
	
	public boolean saveCommentApp(String userName,int packageId,String versionName,String comment,int score);
	
	public String uploadApk(MultipartFile apkFile) throws Exception;
	
	public List<Comment> getUserCommentApp(int packageId,String versionName);
	
	public User getUserByUserName(String userName);
	
	public void updateVersion(Version version);
	
	public void saveOrUpdate(AndroidAppSec bean);
	
	public String saveDifferentVersion(Version version);
	
}
