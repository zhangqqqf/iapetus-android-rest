package net.tatans.iapetus.android.entity;

import java.io.Serializable;

public class ApkInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String packageName;
	private int versionCode;
	private String versionName;
	private double size;//M
	private String iconName;
	private String iconUrl;
	
	public ApkInfo() {
		super();
	}
	
	public ApkInfo(String packageName, int versionCode, String versionName, double size, String iconName,
			String iconUrl) {
		super();
		this.packageName = packageName;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.size = size;
		this.iconName = iconName;
		this.iconUrl = iconUrl;
	}

	/**
	 * @return Returns the packageName.
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName The packageName to set.
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return Returns the versionCode.
	 */
	public int getVersionCode() {
		return versionCode;
	}

	/**
	 * @param versionCode The versionCode to set.
	 */
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * @return Returns the versionName.
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName The versionName to set.
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return Returns the size.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @param size The size to set.
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * @return Returns the iconName.
	 */
	public String getIconName() {
		return iconName;
	}

	/**
	 * @param iconName The iconName to set.
	 */
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	/**
	 * @return Returns the iconUrl.
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl The iconUrl to set.
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApkInfo [packageName=" + packageName + ", versionCode=" + versionCode + ", versionName=" + versionName
				+ ", size=" + size + ", iconName=" + iconName + ", iconUrl=" + iconUrl + "]";
	}

	
}
