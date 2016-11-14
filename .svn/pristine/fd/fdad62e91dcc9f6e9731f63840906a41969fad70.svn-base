package net.tatans.android.mark.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tatans.android.mark.dao.AndroidMobileDao;
import net.tatans.android.mark.manager.AndroidMobileIDMng;

@Service
@Transactional
public class AndroidMobileIDMngImpl implements AndroidMobileIDMng{

	@Autowired
	private AndroidMobileDao dao;
	
	@Override
	public boolean findMobileId(String mobileId) {
		return dao.findMobileId(mobileId);
	}

}
