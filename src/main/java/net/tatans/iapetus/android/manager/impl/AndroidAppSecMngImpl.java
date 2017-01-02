package net.tatans.iapetus.android.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.dao.AndroidAppSecDao;
import net.tatans.iapetus.android.dao.CommentDao;
import net.tatans.iapetus.android.dao.UserDao;
import net.tatans.iapetus.android.dao.VersionDao;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.AndroidOssUtil;
import net.tatans.iapetus.android.entity.ApkInfo;
import net.tatans.iapetus.android.entity.ApkUtil;
import net.tatans.iapetus.android.entity.Comment;
import net.tatans.iapetus.android.entity.User;
import net.tatans.iapetus.android.entity.Version;
import net.tatans.iapetus.android.manager.AndroidAppSecMng;
import net.tatans.iapetus.android.rest.util.Constans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.openservices.oss.model.ObjectMetadata;

@Service
@Transactional
public class AndroidAppSecMngImpl implements AndroidAppSecMng {

	@Autowired
	private AndroidAppSecDao dao;
	
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
	public Map uploadApk(File apkFile) throws Exception {
//		解析apk信息
		Map map=new HashMap<>();
		ApkInfo apkInfo=ApkUtil.apk(apkFile);
		System.out.println(apkInfo);
		String packageName=apkInfo.getPackageName();
		String versionName=apkInfo.getVersionName();
		ObjectMetadata objectMetadata =new ObjectMetadata();
		objectMetadata.setContentLength(apkFile.length());
		AndroidOssUtil.deleteFile(Constans.apkPath(packageName, versionName, ".apk"));
		AndroidOssUtil.uploadInputStream(new FileInputStream(apkFile), objectMetadata, Constans.apkPath(packageName, versionName, ".apk"));
		String url=AndroidOssUtil.getFileAddress(Constans.apkPath(packageName, versionName, ".apk"));
		System.out.println("url:"+url);
		map.put("versionName", versionName);
		map.put("packageName", packageName);
		map.put("size", apkInfo.getSize());
		map.put("versionCode", apkInfo.getVersionCode());
		map.put("url",url);
		return map;
	}

}
