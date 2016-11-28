package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

import net.tatans.iapetus.android.entity.AndroidChannel;

@JsonFilter("BaseAndroidApp")
public abstract class BaseAndroidApp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String appName;
	private String decription;
	private String packageName ;
	private Integer versionCode=1;
	private String versionName;
	private Integer down;
	private String iconUrl;
	private String url;
	private String size;
	//0 mean is hotAPP ;1 mean is not hotAPP
	private String tag;
	private Integer weight;

	//	many_to_one
	private AndroidChannel channel;

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the appName.
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName The appName to set.
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return Returns the decription.
	 */
	public String getDecription() {
		return decription;
	}

	/**
	 * @param decription The decription to set.
	 */
	public void setDecription(String decription) {
		this.decription = decription;
	}

	/**
	 * @return Returns the packageName.
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName The packageName to set.
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return Returns the versionCode.
	 */
	public Integer getVersionCode() {
		return versionCode;
	}

	/**
	 * @param versionCode The versionCode to set.
	 */
	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * @return Returns the versionName.
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName The versionName to set.
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	

	/**
	 * @return Returns the down.
	 */
	public Integer getDown() {
		return down;
	}

	/**
	 * @param down The down to set.
	 */
	public void setDown(Integer down) {
		this.down = down;
	}

	/**
	 * @return Returns the iconUrl.
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl The iconUrl to set.
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the channel.
	 */
	public AndroidChannel getChannel() {
		return channel;
	}

	/**
	 * @param channel The channel to set.
	 */
	public void setChannel(AndroidChannel channel) {
		this.channel = channel;
	}
	
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public BaseAndroidApp(Integer id, String appName, String decription,
			String packageName, Integer versionCode, String versionName,
			AndroidChannel channel, String tag, Integer weight,Integer down) {
		super();
		this.id = id;
		this.appName = appName;
		this.decription = decription;
		this.packageName = packageName;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.channel = channel;
		this.tag = tag;
		this.weight = weight;
		this.down=down;
	}

	public BaseAndroidApp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the size.
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size The size to set.
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((decription == null) ? 0 : decription.hashCode());
		result = prime * result + ((down == null) ? 0 : down.hashCode());
		result = prime * result + ((iconUrl == null) ? 0 : iconUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((versionCode == null) ? 0 : versionCode.hashCode());
		result = prime * result + ((versionName == null) ? 0 : versionName.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		BaseAndroidApp other = (BaseAndroidApp) obj;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (decription == null) {
			if (other.decription != null)
				return false;
		} else if (!decription.equals(other.decription))
			return false;
		if (down == null) {
			if (other.down != null)
				return false;
		} else if (!down.equals(other.down))
			return false;
		if (iconUrl == null) {
			if (other.iconUrl != null)
				return false;
		} else if (!iconUrl.equals(other.iconUrl))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
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
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

}
