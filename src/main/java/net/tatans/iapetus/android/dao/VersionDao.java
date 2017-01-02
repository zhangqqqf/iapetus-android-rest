package net.tatans.iapetus.android.dao;

import net.tatans.iapetus.android.entity.Version;

public interface VersionDao {
	
	public Version getVersionByPackageNameAndVersionName(int packageId,String versionName);
	
	public void updateVersion(Version version);
}
