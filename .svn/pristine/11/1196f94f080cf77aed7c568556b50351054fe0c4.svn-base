package net.tatans.iapetus.android.rest.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tatans.android.common.page.Pagination;
import net.tatans.android.common.web.RequestUtils;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.manager.AndroidAppSecMng;
import net.tatans.iapetus.android.rest.util.JsonMapper;
import net.tatans.iapetus.android.rest.util.StringUtil;

/**
 * 针对新版本天坦商店app
 * @author windows7
 *
 */
@Controller
@RequestMapping("/findappsec")
public class FindAppSec {
	/**
	 * 获取所有app信息(用于自动更新)
	 * @param packagename
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validaVersion.do")
	public String ValidaVersion(String packagename,String mobileModel,
			HttpServletRequest request) {
		List<AndroidAppSec> list=new ArrayList<>();
		if(packagename==null||packagename.equals("")){
			list=mng.findNewsApps(mobileModel);
		}else{
			list=mng.findNewAppByPackageName(packagename,mobileModel);
		}
		String json=null;
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","decription","iconUrl","url","packageName","size"});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/appclassifyitem.do")
	public String showClassifyApps(String channelName,String mobileModel,@RequestParam(defaultValue="1",required=false)Integer pageNo,
			HttpServletRequest request) {
		Pagination page=mng.findclassifyAppsByChannelName(channelName, pageNo,mobileModel);
		List<AndroidAppSec> list=(List<AndroidAppSec>) page.getList();
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, "\"pageNo\":"+pageNo+",\"pageCount\":"+null, null);
		}
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","iconUrl","decription","url","packageName","size"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return StringUtil.toResponseStr(true, "\"pageNo\":"+pageNo+",\"pageCount\":"+page.getTotalPage(), json);
	}
	/**
	 * 根据标签名查询APP
	 * @param tag
	 * @param pageNo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/appclassifytag.do")
	public String showTagApps(String mobileModel,@RequestParam(defaultValue="1",required=false)Integer pageNo,
			HttpServletRequest request) {
		String  tag=RequestUtils.getQueryParam(request, "tag");
		Pagination page=mng.findAppsByTag(tag, pageNo,mobileModel);
		List<AndroidAppSec> list=(List<AndroidAppSec>) page.getList();
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, "\"pageNo\":"+pageNo+",\"pageCount\":"+null, null);
		}
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","iconUrl","decription","url","packageName","size"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return StringUtil.toResponseStr(true, "\"pageNo\":"+pageNo+",\"pageCount\":"+page.getTotalPage(), json);
	}
	/**
	 * 应用商店用户APP搜索界面查询
	 * @param appName 搜索框中用户输入的appName
	 * @param pageNo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/searchapp.do")
	public String findSearchInputApp(String appName,String mobileModel,@RequestParam(defaultValue="1",required=false)Integer pageNo,
			HttpServletRequest request) {
		Pagination page=null;
		try {
			page=mng.searchAppByAppName(appName,pageNo,mobileModel);
		} catch (Exception e) {
			return StringUtil.toResponseStr(false, "\"pageNo\":"+pageNo+",\"pageCount\":"+null, null);
		}finally {
			if(page==null){
				return StringUtil.toResponseStr(false, "\"pageNo\":"+pageNo+",\"pageCount\":"+null, null);
			}
		}
		List<AndroidAppSec> list=(List<AndroidAppSec>) page.getList();
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, "\"pageNo\":"+pageNo+",\"pageCount\":"+null, null);
		}
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","iconUrl","decription","url","packageName","size"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return StringUtil.toResponseStr(true, "\"pageNo\":"+pageNo+",\"pageCount\":"+page.getTotalPage(), json);
	}
	/**
	 * 跳转到指定应用界面
	 */
	@ResponseBody
	@RequestMapping(value = "/findspecifyapp.do")
	public String findSpecialApps(String mobileModel,HttpServletRequest request) {
		String  appName=RequestUtils.getQueryParam(request, "appName");
		Pagination page=mng.findSpecifyApps(appName,mobileModel);
		List<AndroidAppSec> list=(List<AndroidAppSec>) page.getList();
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, "\"pageCount\":"+null, null);
		}
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","iconUrl","decription","url","packageName","size"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return StringUtil.toResponseStr(true, "\"pageCount\":"+page.getTotalPage(), json);
	}
	
	@Autowired
	private AndroidAppSecMng mng;
	@Autowired
	private JsonMapper jsonMapper;
	
}
