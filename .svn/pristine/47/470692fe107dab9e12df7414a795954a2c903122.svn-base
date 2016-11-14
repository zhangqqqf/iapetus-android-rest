package net.tatans.android.help.entity.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("BaseAndroidHelp")
public class BaseAndroidHelp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String courseName;
	private String detailCourseName;
	private String detailUrl;
	
	public BaseAndroidHelp(){
	}
	
	public BaseAndroidHelp(Integer id, String courseName, String detailCourseName, String detailUrl) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.detailCourseName = detailCourseName;
		this.detailUrl = detailUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDetailCourseName() {
		return detailCourseName;
	}

	public void setDetailCourseName(String detailCourseName) {
		this.detailCourseName = detailCourseName;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detailCourseName == null) ? 0 : detailCourseName.hashCode());
		result = prime * result + ((detailUrl == null) ? 0 : detailUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
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
		BaseAndroidHelp other = (BaseAndroidHelp) obj;
		if (detailCourseName == null) {
			if (other.detailCourseName != null)
				return false;
		} else if (!detailCourseName.equals(other.detailCourseName))
			return false;
		if (detailUrl == null) {
			if (other.detailUrl != null)
				return false;
		} else if (!detailUrl.equals(other.detailUrl))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		return true;
	}
	
}
