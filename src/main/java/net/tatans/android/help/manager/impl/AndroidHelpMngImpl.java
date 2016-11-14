package net.tatans.android.help.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tatans.android.help.dao.AndroidHelpDao;
import net.tatans.android.help.entity.AndroidHelp;
import net.tatans.android.help.manager.AndroidHelpMng;

@Service
@Transactional
public class AndroidHelpMngImpl implements AndroidHelpMng {
	
	@Autowired
	private AndroidHelpDao dao;

	@Override
	public List<AndroidHelp> findHelpCourse(String fileType) {
		return dao.findHelpCourse(fileType);
	}
	
}
