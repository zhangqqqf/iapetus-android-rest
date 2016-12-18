package net.tatans.iapetus.android.dao;

import net.tatans.iapetus.android.entity.User;
import net.tatans.iapetus.android.entity.Version;

public interface CommentDao {

	public boolean saveCommentApp(User user,Version version,String comment);
}
