package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import net.tatans.iapetus.android.entity.User;
import net.tatans.iapetus.android.entity.Version;

@JsonFilter("BaseComment")
public abstract class BaseComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String content;
	private Timestamp contentTime;
	private User user;
	private int thumbsUp;
	private Version version;	
	
	public BaseComment(Integer id, String content, int thumbsUp) {
		super();
		this.id = id;
		this.content = content;
		this.thumbsUp = thumbsUp;
	}
	public BaseComment() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((contentTime == null) ? 0 : contentTime.hashCode());
		result = prime * result + id;
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
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
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
		if (id != other.id)
			return false;
		if (thumbsUp != other.thumbsUp)
			return false;
		return true;
	}
	public int getId() {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Timestamp getContentTime() {
		return contentTime;
	}
	public void setContentTime(Timestamp contentTime) {
		this.contentTime = contentTime;
	}
	public BaseComment(int id, String content, Timestamp contentTime, User user, int thumbsUp,
			Version version) {
		super();
		this.id = id;
		this.content = content;
		this.contentTime = contentTime;
		this.user = user;
		this.thumbsUp = thumbsUp;
		this.version = version;
	}
	
	public Version getVersion() {
		return version;
	}
	
	@JsonIgnore
	public void setVersion(Version version) {
		this.version = version;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
