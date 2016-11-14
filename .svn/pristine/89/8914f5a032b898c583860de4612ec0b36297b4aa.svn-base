package net.tatans.android.help.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tatans.android.common.hibernate3.HibernateBaseDao;
import net.tatans.android.help.dao.AndroidHelpDao;
import net.tatans.android.help.entity.AndroidHelp;

@Repository
public class AndroidHelpDaoImpl extends HibernateBaseDao<AndroidHelp, Integer> implements AndroidHelpDao{

	@Override
	protected Class<AndroidHelp> getEntityClass() {
		return AndroidHelp.class;
	}

	@Override
	public List<AndroidHelp> findHelpCourse(String fileType) {
		List<AndroidHelp> list = find("from AndroidHelp bean where bean.detailUrl like ? ","%."+fileType);
		return list;
	}

}
