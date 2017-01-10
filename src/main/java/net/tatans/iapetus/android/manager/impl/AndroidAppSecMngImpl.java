package net.tatans.iapetus.android.manager.impl;

import java.util.ArrayList;
import java.util.List;
import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.dao.AndroidAppSecDao;
import net.tatans.iapetus.android.dao.AndroidChannelSecDao;
import net.tatans.iapetus.android.dao.CommentDao;
import net.tatans.iapetus.android.dao.UserDao;
import net.tatans.iapetus.android.dao.VersionDao;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.AndroidChannelSec;
import net.tatans.iapetus.android.entity.AndroidOssUtil;
import net.tatans.iapetus.android.entity.Comment;
import net.tatans.iapetus.android.entity.User;
import net.tatans.iapetus.android.entity.Version;
import net.tatans.iapetus.android.manager.AndroidAppSecMng;
import net.tatans.iapetus.android.rest.util.Constans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.openservices.oss.model.ObjectMetadata;

@Service
@Transactional
public class AndroidAppSecMngImpl implements AndroidAppSecMng {

	@Autowired
	private AndroidAppSecDao dao;
	
	@Autowired
	private AndroidChannelSecDao androidChannelDao;
	
	@Autowired
	private VersionDao versionDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public AndroidAppSec findById(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	public AndroidChannelSec findByChannelId(Integer AndroidChannelId) {
		// TODO Auto-generated method stub
		return androidChannelDao.findById(AndroidChannelId);
	}
	@Override
	public Pagination findBychannelId(Integer channelId,
			Integer pageNo, Integer pageSize) {
		if(pageNo==null||pageNo<=0){
			pageNo=1;
		}
		return dao.findBychannelId(channelId, pageNo, pageSize);
	}

	@Override
	public List<AndroidAppSec> deleteByIds(Integer[] ids) {
		List<AndroidAppSec> apps=new ArrayList<AndroidAppSec>();
		for(Integer id:ids){
			AndroidAppSec app=dao.findById(id);
			delete(app);
			apps.add(app);
		}
		return apps;
	}
	
	private AndroidAppSec delete(AndroidAppSec bean){
		return dao.delete(bean);
	}
	
	@Override
	public AndroidAppSec update(AndroidAppSec bean) {
		Updater<AndroidAppSec> update=new Updater<AndroidAppSec>(bean);
		return dao.updateByUpdater(update);
	}

	@Override
	public boolean save(AndroidAppSec bean) {
		return dao.save(bean);
	}

	@Override
	public List<AndroidAppSec> findNewsApps(String mobileModel) {
		return dao.findNewsApps(mobileModel);
	}
	@Override
	public int updateSumDownloadApp(String packageName) {
		// TODO Auto-generated method stub
		return dao.updateSumDownloadApp(packageName);
	}
	@Override
	public List<AndroidAppSec> findNewAppByPackageName(String packagename, String mobileModel) {
		return dao.findNewAppByPackageName(packagename,mobileModel);
	}
	
	@Override
	public Pagination findclassifyAppsByChannelName(String channelName, int pageNo, String mobileModel) {
		return dao.findclassifyAppsByChannelName(channelName,pageNo,mobileModel);
	}
	@Override
	public Pagination searchAppByAppName(String appName,int pageNo, String mobileModel) {
		return dao.searchAppByAppName(appName,pageNo,mobileModel);
	}

	@Override
	public Pagination findSpecifyApps(String appName, String mobileModel) {
		return dao.findSpecifyApps(appName,mobileModel);
	}
	
	@Override
	public Pagination findAppsByTag(String tag, Integer pageNo, String mobileModel){
		return dao.findAppsByTag(tag,pageNo,mobileModel);
	}

	@Override
	public String validaApp(String imei, String sign) {
		return dao.validaApp(imei,sign);
	}

	@Override
	public Pagination findAppsBySumCount(String tag, Integer pageNo, String mobileModel) {
		// TODO Auto-generated method stub
		return dao.findAppsBySumCount(tag,pageNo,mobileModel);
	}

	@Override
	public boolean saveCommentApp(String userName,int packageId,String versionName,String comment,int score) {
		// TODO Auto-generated method stub
		 Version version=versionDao.getVersionByPackageNameAndVersionName(packageId, versionName);
		 User user =userDao.getUserByUserName(userName);
		 return commentDao.saveCommentApp(user, version, comment,score);
	}

	@Override
	public List<Comment> getUserCommentApp(int packageId,String versionName) {
		Version version=versionDao.getVersionByPackageNameAndVersionName(packageId, versionName);
		double gradle= commentDao.getAvgScoreByVersionId(version);
		version.setGradle(gradle);
		versionDao.updateVersion(version);
		return commentDao.getUserCommentApp(version);
	}

	@Override
	public String uploadApk(MultipartFile apkFile,String packageName, String versionName) throws Exception {
		ObjectMetadata objectMetadata =new ObjectMetadata();
		objectMetadata.setContentLength(apkFile.getSize());
		
		boolean flag=AndroidOssUtil.verifyKey(Constans.apkPath(packageName, versionName, ".apk"));
		System.out.println("uploadApk_flag："+flag);
		if(flag==true)
			return "该文件已经存在，请勿重复上传。";
		//AndroidOssUtil.deleteFile(Constans.apkPath(packageName, versionName, ".apk"));
		AndroidOssUtil.uploadInputStream(apkFile.getInputStream(), objectMetadata, Constans.apkPath(packageName, versionName, ".apk"));
		String url=AndroidOssUtil.getFileAddress(Constans.apkPath(packageName, versionName, ".apk"));
		System.out.println("url:"+url);
		return "true";
	}

	@Override
	public List<AndroidAppSec> findNewAppByPackageName(String packagename) {
		// TODO Auto-generated method stub
		return dao.findNewAppByPackageName(packagename);
	}

	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(userName);
	}

	@Override
	public void updateVersion(Version version) {
		// TODO Auto-generated method stub
		versionDao.updateVersion(version);
	}

	@Override
	public void saveOrUpdate(AndroidAppSec bean) {
		// TODO Auto-generated method stub
		dao.save(bean);
	}

	@Override
	public String saveDifferentVersion(Version version) {
		// TODO Auto-generated method stub
		return versionDao.saveDifferentVersion(version);
	}

}
