package net.tatans.android.mark.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.tatans.android.mark.entity.AndroidAppMark;
import net.tatans.android.mark.entity.base.AndroidMarkUpdateMsg;
import net.tatans.android.mark.manager.AndroidAppMarkMng;
import net.tatans.android.mark.manager.AndroidMobileIDMng;
import net.tatans.iapetus.android.rest.util.JsonMapper;
import net.tatans.iapetus.android.rest.util.JsonUtil;

@Controller
@RequestMapping("/appMark")
public class AppMarkService {
	@Autowired
	private AndroidAppMarkMng appMarkMng;
	@Autowired
	private JsonMapper jsonMapper;
	@Autowired
	private AndroidMobileIDMng mobileMng;
	
	/**
	 * 增加标签
	 * 获取前端app按钮标签的json数据，解析，保存到数据库
	 * @param packagename
	 * @param request
	 * @return @RequestParam(value = "markFile", required = true) MultipartFile markFile
	 */
	@ResponseBody
	@RequestMapping(value = "/addAppMark.do")
	public boolean addAppMark(String mobileId, String markStr, HttpServletRequest request) {
		boolean existMobileID = mobileMng.findMobileId(mobileId);
		boolean canSave = false;
		if (existMobileID && markStr != null && !"".equals(markStr)) {
			List<AndroidAppMark> list = JsonUtil.getDTOList(markStr, AndroidAppMark.class);
			//从markStr中提取出markVersion(appVersionName)
			if(list.size()>0){
				//提取出标签版本号(此处就是应用的版本号)
				String appVersion = list.get(0).getMarkVersion();//581
				//根据packageName、markVersion找到对应版本的应用的标签版本
				String markVersionDb = appMarkMng.findMarkVersionByPNAndMV(list.get(0).getPackageName(),appVersion);//581.2
				//新的标签版本名
				String newMarkVersionDb = null;
				if(markVersionDb !=null && !"".equals(markVersionDb)){
					int intMarkVersion =Integer.valueOf(markVersionDb.substring(markVersionDb.indexOf(".")+1))+1;
					newMarkVersionDb = appVersion+"."+intMarkVersion;
				}else{
					newMarkVersionDb = appVersion+".1";
				}
				//替换掉所有list中markVersion的值
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setMarkVersion(newMarkVersionDb);
				}
				//进行保存或者更新
				appMarkMng.saveOrUpdate(list,markVersionDb);
				canSave = true;
			}
			
		}
		return canSave;
	}
	
	/**
	 * 查询供下载标签
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAppMark.do")
	public String queryAppMark(String packageName, String appVersion,HttpServletRequest request) {
		if (packageName != null && !"".equals(packageName)
				&& appVersion != null && !"".equals(appVersion)) {
			List<AndroidAppMark> list = appMarkMng.findMarkByPNAndAV(packageName, appVersion);
			String json = null;
			if(list.size()>0){
				try {
					json = jsonMapper.toJsonStr(list,
							new String[] {"packageName","packageSignature","viewName","text","locale","packageVersion","screenshotPath","timestamp","markVersion"});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return json;
		}
		return null;
	}
	
	/**
	 * listPN  返回的包名集合
	 * updateMsg  包含packageName、appVersion、markVersion
	 * 返回标签更新的数据
	 */
	@ResponseBody
	@RequestMapping(value = "/queryMarkForUpdate.do")
	public String queryAppMarkUpdate(String updateMsg, HttpServletRequest request) {
		String packageName = "";
		String appVersion = "";
		List<String> listPN = new ArrayList<String>();
		List<AndroidMarkUpdateMsg> list = JsonUtil.getDTOList(updateMsg, AndroidMarkUpdateMsg.class);
		for (int i = 0; i < list.size(); i++) {
			packageName = list.get(i).getPackageName();
			List<AndroidAppMark> listByPN = appMarkMng.findMarkByPackageName(packageName);
			if (listByPN.size() > 0) {
				appVersion = list.get(i).getAppVersion();
				List<AndroidAppMark> listDb = appMarkMng.findMarkByPNAndAV(packageName, appVersion);
				//前端的标签版本号
				long appMarkVersionCode = (long) (Double.valueOf(list.get(i).getMarkVersion())*1000);
				//后端对应应用的标签版本号
				long dbMarkVersionCode = (long) (Double.valueOf(listDb.get(0).getMarkVersion())*1000);
				if (dbMarkVersionCode > appMarkVersionCode) {
					listPN.add(packageName);
				}
			}
		}
		//把获取的可更新的包名的集合转换成json数据
		String jsonBack = null;
		if(listPN.size()>0){
			try {
				JSONArray jsonArray = new JSONArray();
				for (int i = 0; i < listPN.size(); i++) {
					JSONObject jo = new JSONObject();
					jo.put("packageName", listPN.get(i));
					jsonArray.add(jo);
				}
				jsonBack = jsonArray.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return jsonBack;
	}

}
