package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.iapetus.android.dao.VersionDao;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.Version;
import net.tatans.iapetus.android.rest.util.Constans;

@Repository
public class VersionDaoImpl extends HibernateBaseDao<Version, Integer> implements VersionDao {

	@Override
	protected Class<Version> getEntityClass() {
		return Version.class;
	}

	@Override
	public Version getVersionByPackageNameAndVersionName(int packageId, String versionName) {
		// TODO Auto-generated method stub
		Finder finder=Finder.create("from Version bean where bean.androidAppSec.id=:packageId and bean.versionName=:versionName");
		finder.setParam("packageId",packageId);
		finder.setParam("versionName",versionName);
		List<Version> list=find(finder);
		System.out.println("list.size()"+list.size());
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public void updateVersion(Version version) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(version);
	}

	@Override
	public boolean save(Version version) {
		Finder finder=Finder.create("from Version bean where bean.appName=:appName ");
		//finder.setParam("appName",version.getAppName());
		List<AndroidAppSec> list=find(finder);
		if(list.size()==0){
			Integer i=(Integer)getSession().save(version);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String saveDifferentVersion(Version version) {
		Finder finder=Finder.create("from Version bean where bean.androidAppSec.id=:packageId and bean.versionName=:versionName");
		finder.setParam("versionName",version.getVersionName());
		finder.setParam("packageId",version.getAndroidAppSec().getId());
		List<Version> list=find(finder);
		//System.out.println(list.size()+"-"+version.getVersionName()+"-"+version.getAndroidAppSec().getId());
		if(list.size()==0){
			getSession().save(version);
			return Constans.TRUE;
		}else{
			//该应用此版本已经存在。
			return Constans.APP_VERSION_EXISTS;
		}
	}

	@Override
	public List<Version> findAllVersion(String packageName) {
		// TODO Auto-generated method stub'
		Finder finder=Finder.create("from Version bean where bean.androidAppSec.packageName=:packageName ");
		finder.setParam("packageName",packageName);
		List<Version> list=find(finder);
		return list;
	}

	
	
}
