package net.tatans.android.mark.dao;

import java.util.List;

import net.tatans.android.mark.entity.AndroidAppMark;

public interface AndroidAppMarkDao {

	public void saveOrUpdate(List<AndroidAppMark> list);

	public List<AndroidAppMark> findMarkByPackageName(String packageName);

	public String findMarkVersionByPNAndMV(String packageName, String appVersion);

	public void saveOrUpdate(List<AndroidAppMark> list, String markVersionDb);

	public int checkVersionCount(String packageName);

	public void deleteOlderMarkVersion(String packageName);

	public List<AndroidAppMark> findMarkByPNAndAV(String packageName, String markVersion);
	
	public String selectMarkVersion(String packageName, boolean type);
	
}
