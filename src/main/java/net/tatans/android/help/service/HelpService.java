package net.tatans.android.help.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tatans.android.help.entity.AndroidHelp;
import net.tatans.android.help.manager.AndroidHelpMng;
import net.tatans.iapetus.android.rest.util.JsonMapper;

@Controller
@RequestMapping("/help")
public class HelpService {

	@Autowired
	private AndroidHelpMng mng;
	@Autowired
	private JsonMapper jsonMapper;
	
	@ResponseBody
	@RequestMapping(value = "/findHelpCourse.do")
	public String ValidaVersion(String fileType,
			HttpServletRequest request) {
		List<AndroidHelp> list=new ArrayList<>();
		if(fileType!=null && !"".equals(fileType)){
			list=mng.findHelpCourse(fileType);
		}
		
		String json=null;
		if(list.size()>0){
			try {
				json=jsonMapper.toJsonStr(list,new String[] {"id","courseName","detailCourseName","detailUrl"});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	
}
