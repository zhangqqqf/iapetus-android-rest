package net.tatans.iapetus.android.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.tatans.android.common.hibernate3.HibernateTree;
import net.tatans.android.common.hibernate3.PriorityInterface;
import net.tatans.iapetus.android.entity.base.BaseAndroidChannel;

public class AndroidChannel extends BaseAndroidChannel implements HibernateTree<Integer> ,PriorityInterface {
	
	public AndroidChannel(Integer channelId, String channelName, Integer lft,
			Integer rgt, Integer priority) {
		super(channelId, channelName, lft, rgt, priority);
	}

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public AndroidChannel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AndroidChannel(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLftName() {
		return DEF_LEFT_NAME;
	}

	@Override
	public String getRgtName() {
		return DEF_RIGHT_NAME;
	}

	@Override
	public String getParentName() {
		return DEF_PARENT_NAME;
	}

	@Override
	public Integer getParentId() {
		AndroidChannel parent = getParent();
		if (parent != null) {
			return parent.getId();
		} else {
			return null;
		}
	}

	@Override
	public String getTreeCondition() {
		return null;
	}
	
	/**
	 * 获得深度
	 * 
	 * @return 第一层为0，第二层为1，以此类推。
	 */
	public int getDeep() {
		int deep = 0;
		AndroidChannel parent = getParent();
		while (parent != null) {
			deep++;
			parent = parent.getParent();
		}
		return deep;
	}
	
	public boolean getHasChild() {
		if (getChild().size() > 0) {
			return true;
		}
		return false;
	}

	public static List<AndroidChannel> getListForSelect(List<AndroidChannel> topList,
			Set<AndroidChannel> rights, boolean hasContentOnly) {
		return getListForSelect(topList, rights, null, hasContentOnly);
	}

	public static List<AndroidChannel> getListForSelect(List<AndroidChannel> topList,
			Set<AndroidChannel> rights, AndroidChannel exclude, boolean hasContentOnly) {
		List<AndroidChannel> list = new ArrayList<AndroidChannel>();
		for (AndroidChannel c : topList) {
			addChildToList(list, c, rights, exclude, hasContentOnly);
		}
		return list;
	}
	
	/**
	 * 递归将子栏目加入列表。条件：有内容的栏目。
	 * 
	 * @param list
	 *            栏目容器
	 * @param channel
	 *            待添加的栏目，且递归添加子栏目
	 * @param rights
	 *            有权限的栏目，为null不控制权限。
	 */
	private static void addChildToList(List<AndroidChannel> list, AndroidChannel channel,
			Set<AndroidChannel> rights, AndroidChannel exclude, boolean hasContentOnly) {
		if ((rights != null && !rights.contains(channel))
				|| (exclude != null && exclude.equals(channel))) {
			return;
		}
		list.add(channel);
		Set<AndroidChannel> child = channel.getChild();
		for (AndroidChannel c : child) {
			if (hasContentOnly) {
				if (c.getHasContent()) {
					addChildToList(list, c, rights, exclude, hasContentOnly);
				}
			} else {
				addChildToList(list, c, rights, exclude, hasContentOnly);
			}
		}
	}
}
