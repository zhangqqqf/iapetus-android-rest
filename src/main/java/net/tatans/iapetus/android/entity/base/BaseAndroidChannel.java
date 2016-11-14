package net.tatans.iapetus.android.entity.base;

import java.io.Serializable;
import java.util.Set;

import net.tatans.iapetus.android.entity.AndroidApp;
import net.tatans.iapetus.android.entity.AndroidChannel;

public abstract class BaseAndroidChannel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String REF = "AndroidChannel";
	public static String PROP_RGT = "rgt";
	public static String PROP_LFT = "lft";
	public static String PROP_PARENT = "parent";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_ID = "id";
	public static String PROP_HAS_CONTENT = "hasContent";
	public static String PROP_DISPLAY = "display";

	private java.lang.Integer id;
	private java.lang.String channelName;
	private java.lang.Integer lft;
	private java.lang.Integer rgt;
	private java.lang.Integer priority;
	public java.lang.Boolean getHasContent() {
		return hasContent;
	}



	public BaseAndroidChannel(Integer id, String channelName, Integer lft,
			Integer rgt, Integer priority, Boolean hasContent, Boolean display,
			int hashCode, AndroidChannel parent, Set<AndroidChannel> child,
			Set<AndroidApp> apps) {
		super();
		this.id = id;
		this.channelName = channelName;
		this.lft = lft;
		this.rgt = rgt;
		this.priority = priority;
		this.hasContent = hasContent;
		this.display = display;
		this.hashCode = hashCode;
		this.parent = parent;
		this.child = child;
		this.apps = apps;
	}



	public void setHasContent(java.lang.Boolean hasContent) {
		this.hasContent = hasContent;
	}



	public java.lang.Boolean getDisplay() {
		return display;
	}



	public void setDisplay(java.lang.Boolean display) {
		this.display = display;
	}




	private java.lang.Boolean hasContent;
	private java.lang.Boolean display;

	protected void initialize() {
	}
	
	

	public BaseAndroidChannel() {
		initialize();
	}
	
	


	public BaseAndroidChannel(Integer id) {
		super();
		this.id = id;
		initialize();
	}




	private int hashCode = Integer.MIN_VALUE;

	// many-to-one
	private AndroidChannel parent;

	// collections
	private java.util.Set<AndroidChannel> child;

	private java.util.Set<AndroidApp> apps;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public java.lang.String getChannelName() {
		return channelName;
	}

	public void setChannelName(java.lang.String channelName) {
		this.channelName = channelName;
	}

	public java.lang.Integer getLft() {
		return lft;
	}

	public void setLft(java.lang.Integer lft) {
		this.lft = lft;
	}

	public java.lang.Integer getRgt() {
		return rgt;
	}

	public void setRgt(java.lang.Integer rgt) {
		this.rgt = rgt;
	}

	public java.lang.Integer getPriority() {
		return priority;
	}

	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}



	/**
	 * @return Returns the parent.
	 */
	public AndroidChannel getParent() {
		return parent;
	}



	/**
	 * @param parent The parent to set.
	 */
	public void setParent(AndroidChannel parent) {
		this.parent = parent;
	}



	/**
	 * @return Returns the child.
	 */
	public java.util.Set<AndroidChannel> getChild() {
		return child;
	}



	/**
	 * @param child The child to set.
	 */
	public void setChild(java.util.Set<AndroidChannel> child) {
		this.child = child;
	}



	/**
	 * @return Returns the apps.
	 */
	public java.util.Set<AndroidApp> getApps() {
		return apps;
	}



	/**
	 * @param apps The apps to set.
	 */
	public void setApps(java.util.Set<AndroidApp> apps) {
		this.apps = apps;
	}



	public BaseAndroidChannel(Integer id, String channelName, Integer lft,
			Integer rgt, Integer priority) {
		super();
		this.id = id;
		this.channelName = channelName;
		this.lft = lft;
		this.rgt = rgt;
		this.priority = priority;
		initialize();
	}


	
}
