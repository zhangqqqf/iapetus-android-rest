package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.Comment;
import net.tatans.iapetus.android.entity.User;

@JsonFilter("BaseVersion")
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
	private double gradle;
	
	private Integer packageId;
	
	private Set<Comment> comment = new HashSet<Comment>();
	
	private  AndroidAppSec androidAppSec;
	
	private  User users;
	@JsonIgnore
	public Set<Comment> getComment() {
		return comment;
	}
	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

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
	
	public double getGradle() {
		return gradle;
	}
	public void setGradle(double gradle) {
		this.gradle = gradle;
	}
	@JsonIgnore
	public AndroidAppSec getandroidAppSec() {
		return androidAppSec;
	}
	public void setAndroidAppSec(AndroidAppSec androidAppSec) {
		this.androidAppSec = androidAppSec;
	}
	@JsonIgnore
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((androidAppSec == null) ? 0 : androidAppSec.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gradle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((packageId == null) ? 0 : packageId.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		if (androidAppSec == null) {
			if (other.androidAppSec != null)
				return false;
		} else if (!androidAppSec.equals(other.androidAppSec))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (Double.doubleToLongBits(gradle) != Double.doubleToLongBits(other.gradle))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (packageId == null) {
			if (other.packageId != null)
				return false;
		} else if (!packageId.equals(other.packageId))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
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
		return "BaseVersion [id=" + id + ", versionCode=" + versionCode + ", versionName=" + versionName
				+ ", userId="  + ", gradle=" + gradle + ", androidAppSec=" + androidAppSec
				+ ", user=" + users + "]";
	}
	public BaseVersion(Integer id, Integer versionCode, String versionName, Integer gradle) {
		super();
		this.id = id;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.gradle = gradle;
	}
}
