package net.tatans.iapetus.android.entity;

import net.tatans.iapetus.android.entity.base.BaseAndroidAppSec;
import net.tatans.iapetus.android.entity.AndroidChannelSec;


public class AndroidAppSec extends BaseAndroidAppSec{
	public AndroidAppSec() {
		super();
	}

	public AndroidAppSec(Integer id,String appName, String decription,
			String packageName, Integer versionCode, String versionName,
			AndroidChannelSec channel,String tag, Integer weight, String mobileModel,Integer dowm) {
		super(id, appName, decription, packageName, versionCode, versionName, channel, tag, weight, mobileModel,dowm);
	}

	
}
