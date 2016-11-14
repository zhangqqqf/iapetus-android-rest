package net.tatans.iapetus.android.entity;

import net.tatans.iapetus.android.entity.base.BaseAndroidApp;

public class SumDownLoadApp extends BaseAndroidApp{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SumDownLoadApp() {
		super();
	}

	public SumDownLoadApp(Integer id,String appName, String decription,
			String packageName, Integer versionCode, String versionName,
			AndroidChannel channel,String tag, Integer weight) {
		super(id, appName, decription, packageName, versionCode, versionName, channel,tag, weight);
	}

	
}
