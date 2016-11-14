package net.tatans.android.mark.entity.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

public abstract class BaseAndroidMobileID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public BaseAndroidMobileID(){
		super();
	}
	
	private Integer id;
	private String mobileId;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobileId() {
		return mobileId;
	}
	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((mobileId == null) ? 0 : mobileId.hashCode());
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
		BaseAndroidMobileID other = (BaseAndroidMobileID) obj;
		if (id != other.id)
			return false;
		if (mobileId == null) {
			if (other.mobileId != null)
				return false;
		} else if (!mobileId.equals(other.mobileId))
			return false;
		return true;
	}
	public BaseAndroidMobileID(int id, String mobileId) {
		super();
		this.id = id;
		this.mobileId = mobileId;
	}
	
}
