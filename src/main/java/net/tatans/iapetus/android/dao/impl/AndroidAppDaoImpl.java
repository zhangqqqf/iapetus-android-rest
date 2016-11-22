package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.dao.AndroidAppDao;
import net.tatans.iapetus.android.entity.AndroidApp;
import net.tatans.iapetus.android.entity.SumDownLoadApp;
import net.tatans.iapetus.android.rest.util.Constans;

public class AndroidAppDaoImpl extends HibernateBaseDao<AndroidApp, Integer> implements AndroidAppDao {


	@Override
	protected Class<AndroidApp> getEntityClass() {
		return AndroidApp.class;
	}

	@Override
	public AndroidApp findById(Integer id) {
		return get(id);
	}

	@Override
	public Pagination findBychannelId( Integer channelId,
			Integer pageNo, Integer pageSize) {
		Finder finder;
		if(channelId==null){
			finder= Finder.create("from AndroidApp bean order by weight desc");
		}else{
			finder= Finder.create("from AndroidApp bean where bean.channel.id=:channelId order by weight desc");
			finder.setParam("channelId", channelId);
		}
		return find(finder, pageNo, pageSize);
	}

	@Override
	public AndroidApp delete(AndroidApp bean) {
		getSession().delete(bean);
		return bean;
	}

	@Override
	public boolean save(AndroidApp bean) {
		Finder finder=Finder.create("from AndroidApp bean where bean.appName=:appName ");
		finder.setParam("appName",bean.getAppName());
		List<AndroidApp> list=find(finder);
		if(list.size()==0){
			getSession().save(bean);
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<AndroidApp> findNewsApps() {
		Finder finder=Finder.create("from AndroidApp bean group by bean.packageName order by weight desc ");
//		finder.setMaxResults(20);
		return find(finder);
	}

	@Override
	public AndroidApp findNewAppByPackageName(String packagename) {
		return findUniqueByProperty("packageName", packagename);
	}

	@Override
	public Pagination findclassifyAppsByChannelName(String channelName, int pageNo) {
		Finder f=Finder.create("from AndroidApp bean where bean.channel.channelName=:channelName order by weight desc ");
		f.setParam("channelName", channelName);
		return find(f, pageNo, countQueryResult(f)+1);
	}

	@Override
	public Pagination searchAppByAppName(String appName,int pageNo) {
		    Finder finder=Finder.create("from AndroidApp bean where lower(bean.appName) like :appName order by weight desc ");
	    	finder.setParam("appName","%"+appName.toLowerCase()+"%");
	    	return find(finder, pageNo,countQueryResult(finder)+1);
	}
	
	@Override
	public Pagination findSpecifyApps(String appName) {
		    Finder finder=Finder.create("from AndroidApp bean where bean.appName=:appName order by weight desc ");
		    finder.setParam("appName",appName);
	    	return find(finder, 1, Constans.pageSize);
	}
	
	public Pagination findAppsByTag(String tag, int pageNo){
		Finder f=Finder.create("from AndroidApp bean where bean.tag=:tag order by weight desc ");
		f.setParam("tag", tag);
		return find(f, pageNo, countQueryResult(f)+1);
	}
}
