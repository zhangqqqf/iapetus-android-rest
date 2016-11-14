package net.tatans.android.mark.dao.impl;

import java.util.List;

import org.hibernate.sql.Delete;
import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.mark.dao.AndroidAppMarkDao;
import net.tatans.android.mark.entity.AndroidAppMark;

@Repository
public class AndroidAppMarkDaoImpl extends HibernateBaseDao<AndroidAppMark, Integer> implements AndroidAppMarkDao {

	@Override
	protected Class<AndroidAppMark> getEntityClass() {
		return AndroidAppMark.class;
	}

	@Override
	public void saveOrUpdate(List<AndroidAppMark> list) {
		Finder finder = null;
		String packageName = list.get(0).getPackageName();
		List <AndroidAppMark> listUpdate = null;
		for (int i = 0; i < list.size(); i++) {
			finder = Finder.create("from AndroidAppMark bean where bean.viewName=:viewName and bean.packageName=:packageName ");
			finder.setParam("viewName", list.get(i).getViewName());
			finder.setParam("packageName",packageName);
			listUpdate = find(finder);
			if(listUpdate.size()>0){
				getSession().delete(listUpdate.get(0));
				getSession().save(list.get(i));
			}else{
				getSession().save(list.get(i));
			}
		}
	}

	@Override
	public List<AndroidAppMark> findMarkByPackageName(String packageName) {
		Finder finder = Finder.create("from AndroidAppMark bean where bean.packageName=:packageName ");
		finder.setParam("packageName",packageName);
		List <AndroidAppMark> list = find(finder);
		return list;
	}

	@Override
	public String findMarkVersionByPNAndMV(String packageName, String appVersion) {
		String markVersionDb="";
		List list=find(
				"select DISTINCT markVersion from AndroidAppMark bean where bean.packageName=? and bean.markVersion like ? ",
				packageName,appVersion+".%");
		if(list.size()!=0){
			markVersionDb=list.get(0).toString();
		}
		return markVersionDb;
	}

	@Override
	public void saveOrUpdate(List<AndroidAppMark> list, String markVersionDb) {
		/*
		 * 如果存在该版本 对该版本进行更新保存,并更新该应用版本下的所有标签的标签版本
		 * 不存在该版本 则直接保存
		 */
		if("".equals(markVersionDb)){
			for (int i = 0; i < list.size(); i++) {
				getSession().save(list.get(i));
			}
		}else{
			String packageName = list.get(0).getPackageName();
			String newMarkVersionDb = list.get(0).getMarkVersion();
			List <AndroidAppMark> listUpdate = null;
			for(AndroidAppMark mark:list){
				listUpdate = find("from AndroidAppMark bean where bean.viewName=? and bean.markVersion=?  and bean.packageName=? ",
						mark.getViewName(), markVersionDb, packageName);
				if(listUpdate.size()>0){
					AndroidAppMark markBack=listUpdate.get(0);
					markBack.setMarkVersion(newMarkVersionDb);
					markBack.setText(mark.getText());
					markBack.setTimestamp(mark.getTimestamp());
					markBack.setPackageVersion(mark.getPackageVersion());
					Updater<AndroidAppMark> updater=new Updater<AndroidAppMark>(markBack);
					updateByUpdater(updater);
				}else{
					getSession().save(mark);
				}
			}
			//更新应用版本下所有标签的标签版本
			updateAllAppMark(packageName, markVersionDb,newMarkVersionDb);
		}
	}
	/**
	 * 对原有的版本 更新所有的标签版本号
	 * @param packageName  包名
	 * @param MarkVersionDb 原来的标签版本号
	 * @param newMarkVersionDb 新的标签版本号
	 */
	public void updateAllAppMark(String packageName, String markVersionDb, String newMarkVersionDb){
		Finder finder = null;
		finder = Finder.create("from AndroidAppMark bean where bean.markVersion=:markVersion and bean.packageName=:packageName ");
		finder.setParam("markVersion", markVersionDb);
		finder.setParam("packageName",packageName);
		List <AndroidAppMark> list = find(finder);
		if(list.size()>0){
			for (AndroidAppMark mark : list) {
				mark.setMarkVersion(newMarkVersionDb);
				Updater<AndroidAppMark> updater=new Updater<AndroidAppMark>(mark);
				updateByUpdater(updater);
			}
		}
	}

	@Override
	public int checkVersionCount(String packageName) {
		List VersionCount = find("select distinct markVersion from AndroidAppMark bean where packageName=? ", packageName);
		return VersionCount.size();
	}

	@Override
	public void deleteOlderMarkVersion(String packageName) {
		//获取最小标签版本
		String markVerison = selectMarkVersion(packageName,false);
		List<AndroidAppMark> markList = find("from AndroidAppMark bean where bean.markVersion=? and bean.packageName=? ",
				markVerison, packageName);
		for (AndroidAppMark mark : markList) {
			getSession().delete(mark);
		}
	}

	/**
	 * 根据包名和应用的版本号查询返回对应版本的标签版本
	 */
	@Override
	public List<AndroidAppMark> findMarkByPNAndAV(String packageName, String markVersion) {
		List<AndroidAppMark> list = find("from AndroidAppMark bean where bean.markVersion=? and bean.packageName=? ",markVersion,packageName);
		return list;
	}
	
	/**
	 * 
	 * @param packageName 包名
	 * @param type true时获取最大版本  false获取最小版本 标签版本号
	 * @return
	 */
	@Override
	public String selectMarkVersion(String packageName,boolean type){
		String size = "max";
		if(!type){
			size = "min";
		}
		String sql = "select "+size+"(markVersion) from AndroidAppMark bean where bean.packageName=? ";
		List<String> list=find(sql,packageName);
		
		return list.get(0);
	}
	
}
