package net.tatans.android.help.manager;

import java.util.List;

import net.tatans.android.help.entity.AndroidHelp;

public interface AndroidHelpMng {

	public List<AndroidHelp> findHelpCourse(String fileType);
	
}
