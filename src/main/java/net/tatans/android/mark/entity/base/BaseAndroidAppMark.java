package net.tatans.android.mark.entity.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * 应用标签表 bean类
 * @author windows7
 *
 */
@JsonFilter("BaseAndroidAppMark")
public abstract class BaseAndroidAppMark implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String packageName;
	private String packageSignature;//签名
	private String viewName;
	private String text;//标签名
	private String locale;
	private int packageVersion;
	private String screenshotPath;
	private String timestamp;
	private String markVersion;
	
	//有参构造器
	public BaseAndroidAppMark(Integer id, String packageName, String packageSignature, String viewName, String text,
			String locale, int packageVersion, String screenshotPath, String timestamp, String markVersion) {
		super();
		this.id = id;
		this.packageName = packageName;
		this.packageSignature = packageSignature;
		this.viewName = viewName;
		this.text = text;
		this.locale = locale;
		this.packageVersion = packageVersion;
		this.screenshotPath = screenshotPath;
		this.timestamp = timestamp;
		this.markVersion = markVersion;
	}
	
	public BaseAndroidAppMark(){
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageSignature() {
		return packageSignature;
	}

	public void setPackageSignature(String packageSignature) {
		this.packageSignature = packageSignature;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public int getPackageVersion() {
		return packageVersion;
	}

	public void setPackageVersion(int packageVersion) {
		this.packageVersion = packageVersion;
	}

	public String getScreenshotPath() {
		return screenshotPath;
	}

	public void setScreenshotPath(String screenshotPath) {
		this.screenshotPath = screenshotPath;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((markVersion == null) ? 0 : markVersion.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((packageSignature == null) ? 0 : packageSignature.hashCode());
		result = prime * result + packageVersion;
		result = prime * result + ((screenshotPath == null) ? 0 : screenshotPath.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((viewName == null) ? 0 : viewName.hashCode());
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
		BaseAndroidAppMark other = (BaseAndroidAppMark) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
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
		if (packageSignature == null) {
			if (other.packageSignature != null)
				return false;
		} else if (!packageSignature.equals(other.packageSignature))
			return false;
		if (packageVersion != other.packageVersion)
			return false;
		if (screenshotPath == null) {
			if (other.screenshotPath != null)
				return false;
		} else if (!screenshotPath.equals(other.screenshotPath))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (viewName == null) {
			if (other.viewName != null)
				return false;
		} else if (!viewName.equals(other.viewName))
			return false;
		return true;
	}
	
}
