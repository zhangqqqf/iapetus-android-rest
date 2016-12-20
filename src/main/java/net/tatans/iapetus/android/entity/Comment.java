package net.tatans.iapetus.android.entity;

import java.sql.Timestamp;

import net.tatans.iapetus.android.entity.base.BaseComment;

public class Comment extends BaseComment{

	public Comment() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Comment(Integer id, String content, int thumbsUp) {
		super(id, content, thumbsUp);
		// TODO Auto-generated constructor stub
	}

	public Comment(int id, String content, Timestamp contentTime, User baseUser, int thumbsUp,
			Version baseAnroidVersion) {
		super(id, content, contentTime, baseUser, thumbsUp, baseAnroidVersion);
		// TODO Auto-generated constructor stub
	}
}
