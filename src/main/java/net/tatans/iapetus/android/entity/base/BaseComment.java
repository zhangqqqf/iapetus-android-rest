package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

import net.tatans.iapetus.android.entity.Version;

@JsonFilter("BaseComment")
public abstract class BaseComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private BaseUser baseUser;
	private String contentTime ;
	private int thumbsUp;
	private Version baseAnroidVersion;
	
	public Version getBaseAnroidVersion() {
		return baseAnroidVersion;
	}
	public void setBaseAnroidVersion(Version baseAnroidVersion) {
		this.baseAnroidVersion = baseAnroidVersion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BaseUser getBaseUser() {
		return baseUser;
	}
	public void setBaseUser(BaseUser baseUser) {
		this.baseUser = baseUser;
	}
	public String getContentTime() {
		return contentTime;
	}
	public void setContentTime(String contentTime) {
		this.contentTime = contentTime;
	}
	public int getThumbsUp() {
		return thumbsUp;
	}
	public void setThumbsUp(int thumbsUp) {
		this.thumbsUp = thumbsUp;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BaseComment(Integer id, String content, String contentTime, int thumbsUp) {
		super();
		this.id = id;
		this.content = content;
		this.contentTime = contentTime;
		this.thumbsUp = thumbsUp;
	}
	public BaseComment() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseAnroidVersion == null) ? 0 : baseAnroidVersion.hashCode());
		result = prime * result + ((baseUser == null) ? 0 : baseUser.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((contentTime == null) ? 0 : contentTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + thumbsUp;
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
		BaseComment other = (BaseComment) obj;
		if (baseAnroidVersion == null) {
			if (other.baseAnroidVersion != null)
				return false;
		} else if (!baseAnroidVersion.equals(other.baseAnroidVersion))
			return false;
		if (baseUser == null) {
			if (other.baseUser != null)
				return false;
		} else if (!baseUser.equals(other.baseUser))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (contentTime == null) {
			if (other.contentTime != null)
				return false;
		} else if (!contentTime.equals(other.contentTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (thumbsUp != other.thumbsUp)
			return false;
		return true;
	}
	
	
}
