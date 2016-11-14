package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.common.page.Pagination;
import net.tatans.iapetus.android.dao.AndroidAppSecDao;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.rest.util.Constans;

public class AndroidAppSecDaoImpl extends HibernateBaseDao<AndroidAppSec, Integer> implements AndroidAppSecDao {


	@Override
	protected Class<AndroidAppSec> getEntityClass() {
		return AndroidAppSec.class;
	}

	@Override
	public AndroidAppSec findById(Integer id) {
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
	public AndroidAppSec delete(AndroidAppSec bean) {
		getSession().delete(bean);
		return bean;
	}

	@Override
	public boolean save(AndroidAppSec bean) {
		Finder finder=Finder.create("from AndroidAppSec bean where bean.appName=:appName ");
		finder.setParam("appName",bean.getAppName());
		List<AndroidAppSec> list=find(finder);
		if(list.size()==0){
			getSession().save(bean);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<AndroidAppSec> findNewsApps(String mobileModel) {
		Finder finder=Finder.create("from AndroidAppSec bean where ( bean.mobileModel='all' or bean.mobileModel=:mobileModel ) group by bean.packageName order by weight desc ");
		finder.setParam("mobileModel",mobileModel);
//		finder.setMaxResults(20);
		return find(finder);
	}

	@Override
	public List<AndroidAppSec> findNewAppByPackageName(String packagename, String mobileModel) {
		Finder finder=Finder.create("from AndroidAppSec bean where bean.packageName in (:packageName) and ( bean.mobileModel='all' or bean.mobileModel=:mobileModel ) ");
		finder.setParamList("packageName", packagename.split(","));
		finder.setParam("mobileModel",mobileModel);
		List<AndroidAppSec> list = find(finder);
		return list;
	}

	@Override
	public Pagination findclassifyAppsByChannelName(String channelName, int pageNo, String mobileModel) {
		Finder f=Finder.create("from AndroidAppSec bean where bean.channel.channelName=:channelName and ( bean.mobileModel='all' or bean.mobileModel=:mobileModel ) order by weight desc ");
		f.setParam("channelName", channelName);
		f.setParam("mobileModel",mobileModel);
		return find(f, pageNo, countQueryResult(f)+1);
	}

	@Override
	public Pagination searchAppByAppName(String appName,int pageNo, String mobileModel) {
		    Finder finder=Finder.create("from AndroidAppSec bean where lower(bean.appName) like :appName and ( bean.mobileModel='all' or bean.mobileModel=:mobileModel ) order by weight desc ");
	    	finder.setParam("appName","%"+appName.toLowerCase()+"%");
	    	finder.setParam("mobileModel",mobileModel);
	    	return find(finder, pageNo,countQueryResult(finder)+1);
	}
	
	@Override
	public Pagination findSpecifyApps(String appName, String mobileModel) {
		    Finder finder=Finder.create("from AndroidAppSec bean where bean.appName=:appName and ( bean.mobileModel='all' or bean.mobileModel=:mobileModel ) order by weight desc ");
		    finder.setParam("appName",appName);
		    finder.setParam("mobileModel",mobileModel);
	    	return find(finder, 1, Constans.pageSize);
	}
	
	public Pagination findAppsByTag(String tag, int pageNo,String mobileModel){
		Finder f=Finder.create("from AndroidAppSec bean where bean.tag=:tag and ( bean.mobileModel='all' or bean.mobileModel=:mobileModel ) order by weight desc ");
		f.setParam("tag", tag);
		f.setParam("mobileModel",mobileModel);
		return find(f, pageNo, countQueryResult(f)+1);
	}

	@Override
	public String validaApp(String imei, String sign) {
//		Finder f=Finder.create("from net.tatans.iapetus.android.entity.Imei  where imei=123");
//		f.setParam("sign",sign);
//		f.setParam("imei",imei);
		System.out.println(imei+"::"+sign);
//		find("from net.tatans.iapetus.android.entity.Imei  where imei=123 and (select sign from AndroidAppSecSign  c where c.sign=?) is  null");
		if(find("select imei from net.tatans.iapetus.android.entity.Imei  where imei=? and (select sign from AndroidAppSecSign  c where c.sign=?) is not null",imei,sign).size()>0){
			System.out.println(2);
			return "true";
		}
		System.out.println(1);
		return "false";
	}
}
