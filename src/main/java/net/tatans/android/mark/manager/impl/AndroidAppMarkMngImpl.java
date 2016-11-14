package net.tatans.android.mark.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tatans.android.mark.dao.AndroidAppMarkDao;
import net.tatans.android.mark.entity.AndroidAppMark;
import net.tatans.android.mark.manager.AndroidAppMarkMng;

@Service
@Transactional
public class AndroidAppMarkMngImpl  implements AndroidAppMarkMng{
	
	@Autowired
	private AndroidAppMarkDao dao;

	@Override
	public void saveOrUpdate(List<AndroidAppMark> list) {
		dao.saveOrUpdate(list);
	}

	@Override
	public List<AndroidAppMark> findMarkByPackageName(String packageName) {
		return dao.findMarkByPackageName(packageName);
	}

	@Override
	public String findMarkVersionByPNAndMV(String packageName, String appVersion) {
		return dao.findMarkVersionByPNAndMV(packageName,appVersion);
	}

	@Override
	public void saveOrUpdate(List<AndroidAppMark> list, String markVersionDb) {
		dao.saveOrUpdate(list,markVersionDb);
//		//检查同一应用是否有三个或以上的版本保存了标签，删除最低版本的所有标签
		int versionCount = dao.checkVersionCount(list.get(0).getPackageName());
		if(versionCount>2){
			dao.deleteOlderMarkVersion(list.get(0).getPackageName());
		}
		
	}

	@Override
	public List<AndroidAppMark> findMarkByPNAndAV(String packageName, String appVersion) {
		String markVersionDb = dao.findMarkVersionByPNAndMV(packageName, appVersion);
		List<AndroidAppMark> listByPN = dao.findMarkByPackageName(packageName);
		if("".equals(markVersionDb) && listByPN.size()>0){
			markVersionDb = dao.selectMarkVersion(packageName, true);
			int appVersionCode = Integer.valueOf(appVersion);
			int DbVersionCode = Integer.valueOf(markVersionDb.substring(0,markVersionDb.indexOf(".")));
			//如前端给的应用版本号小于数据库中该应用的最大版本号  则给他最小的版本
			if(DbVersionCode>appVersionCode){
				markVersionDb = dao.selectMarkVersion(packageName, false);
			}
		}
		return dao.findMarkByPNAndAV(packageName,markVersionDb);
	}
	
}
