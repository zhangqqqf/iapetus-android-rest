package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

import net.tatans.iapetus.android.entity.User;

@JsonFilter("BaseAnroidVersion")
public abstract class BaseVersion implements Serializable {

	public BaseVersion() {
		super();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer versionCode;
	private String versionName ;
	private Integer gradle;
	private  BaseAndroidAppSec baseAndroidAppSec;
	private  User baseUsers;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	public Integer getGradle() {
		return gradle;
	}
	public void setGradle(Integer gradle) {
		this.gradle = gradle;
	}
	public BaseAndroidAppSec getBaseAndroidAppSec() {
		return baseAndroidAppSec;
	}
	public void setBaseAndroidAppSec(BaseAndroidAppSec baseAndroidAppSec) {
		this.baseAndroidAppSec = baseAndroidAppSec;
	}
	public User getBaseUsers() {
		return baseUsers;
	}
	public void setBaseUsers(User baseUsers) {
		this.baseUsers = baseUsers;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseAndroidAppSec == null) ? 0 : baseAndroidAppSec.hashCode());
		result = prime * result + ((baseUsers == null) ? 0 : baseUsers.hashCode());
		result = prime * result + ((gradle == null) ? 0 : gradle.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((versionCode == null) ? 0 : versionCode.hashCode());
		result = prime * result + ((versionName == null) ? 0 : versionName.hashCode());
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
		BaseVersion other = (BaseVersion) obj;
		if (baseAndroidAppSec == null) {
			if (other.baseAndroidAppSec != null)
				return false;
		} else if (!baseAndroidAppSec.equals(other.baseAndroidAppSec))
			return false;
		if (baseUsers == null) {
			if (other.baseUsers != null)
				return false;
		} else if (!baseUsers.equals(other.baseUsers))
			return false;
		if (gradle == null) {
			if (other.gradle != null)
				return false;
		} else if (!gradle.equals(other.gradle))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (versionCode == null) {
			if (other.versionCode != null)
				return false;
		} else if (!versionCode.equals(other.versionCode))
			return false;
		if (versionName == null) {
			if (other.versionName != null)
				return false;
		} else if (!versionName.equals(other.versionName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BaseAnroidVersion [id=" + id + ", versionCode=" + versionCode + ", versionName=" + versionName
				+ ", userId="  + ", gradle=" + gradle + ", baseAndroidAppSec=" + baseAndroidAppSec
				+ ", baseUser=" + baseUsers + "]";
	}
	public BaseVersion(Integer id, Integer versionCode, String versionName, Integer gradle) {
		super();
		this.id = id;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.gradle = gradle;
	}
}
