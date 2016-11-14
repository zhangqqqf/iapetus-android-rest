package net.tatans.android.mark.entity;

import net.tatans.android.mark.entity.base.BaseAndroidAppMark;

public class AndroidAppMark extends BaseAndroidAppMark{

	public AndroidAppMark() {
		super();
	}
	
	public AndroidAppMark(Integer id, String packageName, String packageSignature, String viewName, String text,
			String locale, int packageVersion, String screenshotPath, String timestamp, String markVersion) {
		super(id, packageName, packageSignature, viewName, text, locale, packageVersion, screenshotPath, timestamp, markVersion);
	}
}
