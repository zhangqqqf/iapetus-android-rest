package net.tatans.android.mark.manager;

import java.util.List;

import net.tatans.android.mark.entity.AndroidAppMark;

public interface AndroidAppMarkMng {
	
	public void saveOrUpdate(List<AndroidAppMark> list);
	
	public void saveOrUpdate(List<AndroidAppMark> list, String markVersionDb);
	
	public List<AndroidAppMark> findMarkByPackageName(String packageName);

	public String findMarkVersionByPNAndMV(String packageName, String appVersion);

	public List<AndroidAppMark> findMarkByPNAndAV(String packageName, String appVersion);
}
