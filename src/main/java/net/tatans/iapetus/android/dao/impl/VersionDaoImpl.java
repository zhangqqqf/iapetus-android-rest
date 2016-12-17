package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.dao.VersionDao;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.Version;

@Repository
public class VersionDaoImpl extends HibernateBaseDao<Version, Integer> implements VersionDao {


	@Override
	protected Class<Version> getEntityClass() {
		return Version.class;
	}

	@Override
	public Version findById(Integer id) {
		return get(id);
	}

	@Override
	public Pagination findBychannelId( Integer channelId,
			Integer pageNo, Integer pageSize) {
		Finder finder;
		if(channelId==null){
			finder= Finder.create("from AndroidAppSec bean order by weight desc");
		}else{
			finder= Finder.create("from AndroidAppSec bean where bean.channel.id=:channelId order by weight desc");
			finder.setParam("channelId", channelId);
		}
		return find(finder, pageNo, pageSize);
	}

	@Override
	public boolean saveCommentApp(int userId,int packageId,String versionName) {
		Finder finder=Finder.create("from Version");
//		Finder finder=Finder.create("from Version bean where bean.versionName=:versionName and bean.packageId=:packageId");
//		finder.setParam("versionName",versionName);
//		finder.setParam("packageId",packageId);
		System.out.println("---------------");
		List<Version> list=find(finder);
		System.out.println("-----"+list.get(0).getId());
//		if(list.size()==0){
//			//getSession().save(bean);
//			return true;
//		}else{
//			return false;
//		}
		return true;
	}

	@Override
	public Version delete(AndroidAppSec bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Version bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int updateSumDownloadApp(String packageName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Version> findNewsApps(String mobileModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Version> findNewAppByPackageName(String packagename, String mobileModel) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
