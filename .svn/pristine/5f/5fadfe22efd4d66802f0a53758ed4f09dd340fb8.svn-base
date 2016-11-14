package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;

public abstract class BaseImei implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imei;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imei == null) ? 0 : imei.hashCode());
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
		BaseImei other = (BaseImei) obj;
		if (imei == null) {
			if (other.imei != null)
				return false;
		} else if (!imei.equals(other.imei))
			return false;
		return true;
	}
	public BaseImei(String imei) {
		super();
		this.imei = imei;
	}
	public BaseImei() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
}
