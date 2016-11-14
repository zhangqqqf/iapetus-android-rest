package net.tatans.android.mark.entity.base;

public class AndroidMarkUpdateMsg {
	
	private String packageName;
	private String appVersion;
	private String markVersion;
	
	public AndroidMarkUpdateMsg(){
	}
	
	public AndroidMarkUpdateMsg(String packageName, String appVersion, String markVersion) {
		super();
		this.packageName = packageName;
		this.appVersion = appVersion;
		this.markVersion = markVersion;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getMarkVersion() {
		return markVersion;
	}
	public void setMarkVersion(String markVersion) {
		this.markVersion = markVersion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appVersion == null) ? 0 : appVersion.hashCode());
		result = prime * result + ((markVersion == null) ? 0 : markVersion.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AndroidMarkUpdateMsg other = (AndroidMarkUpdateMsg) obj;
		if (appVersion == null) {
			if (other.appVersion != null)
				return false;
		} else if (!appVersion.equals(other.appVersion))
			return false;
		if (markVersion == null) {
			if (other.markVersion != null)
				return false;
		} else if (!markVersion.equals(other.markVersion))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		return true;
	}
	
}
