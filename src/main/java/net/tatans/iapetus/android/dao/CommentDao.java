package net.tatans.iapetus.android.dao;

import java.util.List;

import net.tatans.iapetus.android.entity.Comment;
import net.tatans.iapetus.android.entity.User;
import net.tatans.iapetus.android.entity.Version;

public interface CommentDao {

	public boolean saveCommentApp(User user,Version version,String comment,int score);
	
	public List<Comment> getUserCommentApp(Version version);
	
	public List<Comment> getCommentByPackageNameAndVersionName(int packageId,String versionName);
	
	public double getAvgScoreByVersionId(Version version);
}
