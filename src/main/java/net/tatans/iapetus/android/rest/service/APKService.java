package net.tatans.iapetus.android.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.tatans.iapetus.android.entity.AndroidApp;
import net.tatans.iapetus.android.manager.AndroidAppMng;
import net.tatans.iapetus.android.manager.AndroidAppSecMng;
import net.tatans.iapetus.android.rest.util.JsonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/android")
public class APKService {
	@ResponseBody
	@RequestMapping(value = "/validaVersion.do")
	public String ValidaVersion(String packagename,
			HttpServletRequest request) {
		List<AndroidApp> list=new ArrayList<>();
		if(packagename==null||packagename.equals("")){
			list=mng.findNewsApps();
		}else{
			list.add(mng.findNewAppByPackageName(packagename));
		}
		String json=null;
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","decription","iconUrl","url","packageName","size"});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 通过imei  app签名验证app是否能在该手机运行
	 * @param imei 
	 * @param sign app签名
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validaApp.do")
	public String validaApp(@RequestParam String imei,@RequestParam String sign,
			HttpServletRequest request) {
		if("".equals(imei)||"".equals(sign)){
			return "false";
		}
		String retr=mng2.validaApp(imei,sign);
		return retr;
	}
	
	@Autowired
	private AndroidAppMng mng;
	@Autowired
	private AndroidAppSecMng mng2;
	@Autowired
	private JsonMapper jsonMapper;
}
