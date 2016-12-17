package net.tatans.iapetus.android.entity;

import net.tatans.iapetus.android.entity.base.BaseAnroidVersion;

public class Version extends BaseAnroidVersion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Version() {
		super();
	}

	public Version(Integer id, String versionCode, String versionName, Integer gradle) {
		super(id, versionCode, versionName, gradle);
	}

}
