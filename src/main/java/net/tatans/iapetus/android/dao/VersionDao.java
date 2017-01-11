package net.tatans.iapetus.android.dao;

import java.util.List;

import net.tatans.iapetus.android.entity.Version;

public interface VersionDao {
	
	public Version getVersionByPackageNameAndVersionName(int packageId,String versionName);
	
	public void updateVersion(Version version);
	
	public String saveDifferentVersion(Version version);
	
	public boolean save(Version version);
	
	public List<Version> findAllVersion(String packageName);
}
