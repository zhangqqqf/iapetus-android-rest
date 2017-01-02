package net.tatans.iapetus.android.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.Finder;
import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.iapetus.android.dao.CommentDao;
import net.tatans.iapetus.android.entity.Comment;
import net.tatans.iapetus.android.entity.User;
import net.tatans.iapetus.android.entity.Version;

@Repository
public class CommentDaoImpl extends HibernateBaseDao<Comment, Integer> implements CommentDao {

	@Override
	protected Class<Comment> getEntityClass() {
		return Comment.class;
	}

	@Override
	public boolean saveCommentApp(User user, Version version,String comment,int score) {
		if(version==null||user==null){
			return false;
		}
		Comment comments = new Comment();
		comments.setVersion(version);
		comments.setUser(user);
		comments.setContent(comment);
		comments.setScore(score);
		getSession().save(comments);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getUserCommentApp(Version version) {
		Finder finder=Finder.create("from Comment bean where bean.version.id=:versionId order by contentTime desc");
		finder.setParam("versionId", version.getId());
		return find(finder);
	}

	@Override
	public List<Comment> getCommentByPackageNameAndVersionName(int packageId, String versionName) {
		Finder finder=Finder.create("from Comment bean where bean.version.id=:versionId order by contentTime desc");
//		finder.setParam("versionId", version.getId());
		return find(finder);
	}

	@Override
	public double getAvgScoreByVersionId(Version version) {
		double a =(double)findUnique("select avg(bean.score) from Comment bean where versionId=?", version.getId());
		return a;
	}

	
}
