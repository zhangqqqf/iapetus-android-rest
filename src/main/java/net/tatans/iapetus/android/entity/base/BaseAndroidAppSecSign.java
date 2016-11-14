package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;

public abstract class BaseAndroidAppSecSign implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String sign;
	private String mobileModel;
	
	public BaseAndroidAppSecSign() {
	}

	public BaseAndroidAppSecSign(Integer id, String sign, String mobileModel) {
		super();
		this.id = id;
		this.sign = sign;
		this.mobileModel = mobileModel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMobileModel() {
		return mobileModel;
	}

	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobileModel == null) ? 0 : mobileModel.hashCode());
		result = prime * result + ((sign == null) ? 0 : sign.hashCode());
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
		BaseAndroidAppSecSign other = (BaseAndroidAppSecSign) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobileModel == null) {
			if (other.mobileModel != null)
				return false;
		} else if (!mobileModel.equals(other.mobileModel))
			return false;
		if (sign == null) {
			if (other.sign != null)
				return false;
		} else if (!sign.equals(other.sign))
			return false;
		return true;
	}
	
}
