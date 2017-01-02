package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.iapetus.android.dao.VersionDao;
import net.tatans.iapetus.android.entity.Version;

@Repository
public class VersionDaoImpl extends HibernateBaseDao<Version, Integer> implements VersionDao {

	@Override
	protected Class<Version> getEntityClass() {
		return Version.class;
	}

	@Override
	public Version getVersionByPackageNameAndVersionName(int packageId, String versionName) {
		// TODO Auto-generated method stub
		Finder finder=Finder.create("from Version bean where bean.packageId=:packageId and bean.versionName=:versionName");
		finder.setParam("versionName",versionName);
		finder.setParam("packageId",packageId);
		List<Version> list=find(finder);
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

	
	
}
