package net.tatans.iapetus.android.manager.impl;

import java.util.ArrayList;
import java.util.List;

import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.dao.AndroidAppDao;
import net.tatans.iapetus.android.entity.AndroidApp;
import net.tatans.iapetus.android.manager.AndroidAppMng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AndroidAppMngImpl implements AndroidAppMng {

	
	@Autowired
	private AndroidAppDao dao;

	@Override
	public AndroidApp findById(Integer id) {
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
	public List<AndroidApp> deleteByIds(Integer[] ids) {
		List<AndroidApp> apps=new ArrayList<AndroidApp>();
		for(Integer id:ids){
			AndroidApp app=dao.findById(id);
			delete(app);
			apps.add(app);
		}
		return apps;
	}
	
	private AndroidApp delete(AndroidApp bean){
		return dao.delete(bean);
	}
	
	@Override
	public AndroidApp update(AndroidApp bean) {
		Updater<AndroidApp> update=new Updater<AndroidApp>(bean);
		return dao.updateByUpdater(update);
	}

	@Override
	public boolean save(AndroidApp bean) {
		return dao.save(bean);
	}

	@Override
	public List<AndroidApp> findNewsApps() {
		return dao.findNewsApps();
	}

	@Override
	public AndroidApp findNewAppByPackageName(String packagename) {
		return dao.findNewAppByPackageName(packagename);
	}
	
	@Override
	public Pagination findclassifyAppsByChannelName(String channelName, int pageNo) {
		return dao.findclassifyAppsByChannelName(channelName, pageNo);
	}
	@Override
	public Pagination searchAppByAppName(String appName,int pageNo) {
		return dao.searchAppByAppName(appName,pageNo);
	}

	@Override
	public Pagination findSpecifyApps(String appName) {
		return dao.findSpecifyApps(appName);
	}
	
	@Override
	public Pagination findAppsByTag(String tag, Integer pageNo){
		return dao.findAppsByTag(tag,pageNo);
	}

}
