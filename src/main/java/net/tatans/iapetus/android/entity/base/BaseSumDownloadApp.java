package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("BaseSumDownloadApp")
public abstract class BaseSumDownloadApp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer count;
	private String packageName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public BaseSumDownloadApp() {
		super();
	}
	public BaseSumDownloadApp(Integer id, Integer count, String packageName) {
		super();
		this.id = id;
		this.count = count;
		this.packageName = packageName;
	}
	
}
