package net.tatans.iapetus.android.dao.impl;

import org.springframework.stereotype.Repository;

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
	public boolean saveCommentApp(User user, Version version,String comment) {
		if(version==null||user==null){
			return false;
		}
		Comment comments = new Comment();
		comments.setBaseAnroidVersion(version);
		comments.setBaseUser(user);
		comments.setContent(comment);
		getSession().save(comments);
		return true;
	}

	
}
