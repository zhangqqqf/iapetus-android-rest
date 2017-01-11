package net.tatans.iapetus.android.rest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.tatans.android.common.page.Pagination;
import net.tatans.android.common.web.RequestUtils;
import net.tatans.iapetus.android.entity.AndroidAppSec;
import net.tatans.iapetus.android.entity.AndroidChannelSec;
import net.tatans.iapetus.android.entity.AndroidOssUtil;
import net.tatans.iapetus.android.entity.Comment;
import net.tatans.iapetus.android.entity.Version;
import net.tatans.iapetus.android.manager.AndroidAppSecMng;
import net.tatans.iapetus.android.rest.util.Constans;
import net.tatans.iapetus.android.rest.util.JsonMapper;
import net.tatans.iapetus.android.rest.util.StringUtil;

/**
 * 针对新版本天坦商店app
 * @author Yuriy
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
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","decription","packageName","size"});
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
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","decription","packageName","size"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return StringUtil.toResponseStr(true, "\"pageNo\":"+pageNo+",\"pageCount\":"+page.getTotalPage(), json);
	}
	/**
	 * 统计下载数量
	 */
	@ResponseBody
	@RequestMapping(value = "/sumDownloadCount.do")
	public String sumDownloadCount(String packagename,
			HttpServletRequest request) {
		int intCount=mng.updateSumDownloadApp(packagename);
		if(intCount==1){
			return true+"";
		}else{
			return false+"";
		}
	}
	
	/**
	 * 下载应用
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/downLoadApp.do")
	public String downloadApp(String packageName,String versionName,HttpServletResponse response) throws IOException {
		boolean flag=AndroidOssUtil.verifyKey(Constans.apkPath(packageName, versionName, ".apk"));
		if(flag==true){
			response.sendRedirect(AndroidOssUtil.getFileAddress(Constans.apkPath(packageName, versionName, ".apk")));
		}else{
			response.sendRedirect("http://other.tatans.net/apksource/all/"+packageName+"/"+versionName+".apk");
		}
		
		int intCount=mng.updateSumDownloadApp(packageName);
		System.out.println("intcount:"+intCount);
		if(intCount==1){
			return true+"";
		}else{
			return false+"";
		}
	}
	/**
	 * 下载排行版
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/topChartsApp.do")
	public String topChartsApp(String mobileModel,@RequestParam(defaultValue="1",required=false)Integer pageNo,
			HttpServletRequest request)  {
		String  tag=RequestUtils.getQueryParam(request, "tag");
		Pagination page=mng.findAppsBySumCount(tag, pageNo, mobileModel);
		List<AndroidAppSec> list=(List<AndroidAppSec>) page.getList();
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, "\"pageNo\":"+pageNo+",\"pageCount\":"+null, null);
		}
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","decription","packageName","size","down"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return StringUtil.toResponseStr(true, "\"pageNo\":"+pageNo+",\"pageCount\":"+page.getTotalPage(), json);
	}
	/**
	 * 用户评论
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/setUserCommentApp.do")
	public boolean setUserCommentApp(String userName,@RequestParam(defaultValue="50",required=false)
	int packageId,String versionName,String comment,@RequestParam(defaultValue="5",required=false)int score){
		boolean flag=mng.saveCommentApp(userName, packageId, versionName,comment,score);
		return flag;
	}
	/**
	 * 获取用户评论列表
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserCommentApp.do")
	public String getUserCommentApp(int packageId,String versionName){
		List<Comment> list= mng.getUserCommentApp(packageId,versionName);
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, null, null);
		}
		try {
			json=jsonMapper.toJsonStr(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return json;
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
		@SuppressWarnings("unchecked")
		List<AndroidAppSec> list=(List<AndroidAppSec>) page.getList();
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, "\"pageNo\":"+pageNo+",\"pageCount\":"+null, null);
		}
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","decription","packageName","size"});
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
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@ResponseBody
	@RequestMapping(value = "/searchapp.do")
	public String findSearchInputApp(String appName,String mobileModel,@RequestParam(defaultValue="1",required=false)Integer pageNo,
			HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
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
		@SuppressWarnings("unchecked")
		List<AndroidAppSec> list=(List<AndroidAppSec>) page.getList();
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, "\"pageNo\":"+pageNo+",\"pageCount\":"+null, null);
		}
		json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","decription","packageName","size"});
	
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
		@SuppressWarnings("unchecked")
		List<AndroidAppSec> list=(List<AndroidAppSec>) page.getList();
		String json=null;
		if(list.size()==0){
			return StringUtil.toResponseStr(false, "\"pageCount\":"+null, null);
		}
		try {
			json=jsonMapper.toJsonStr(list,new String[] {"id","appName","versionCode","versionName","decription","packageName","size"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return StringUtil.toResponseStr(true, "\"pageCount\":"+page.getTotalPage(), json);
	}
	/**
	 * 跳转到指定应用界面
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@ResponseBody
	@RequestMapping(value = "/findAllVersion.do")
	public String findAllVersion(String packageName,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		List<Version> list = mng.findAllVersion(packageName);
		if(list.size()==0){
			return "false";
		}
		String json=null;
		json=jsonMapper.toJsonStr(list,new String[] {"versionName","versionCode","gradle","sizes"});
	 	return json;
	}
	@ResponseBody
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String uploadApps(String decription,String userName,String sizes,String appName,String packageName,String versionName,@RequestParam(defaultValue="1",required=false)int versionCode,String sign,Integer cid,
			HttpServletRequest request,@RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
		List<AndroidAppSec> list = mng.findNewAppByPackageName(packageName);
		if(null==decription||"".equals(decription)){
			decription=appName;
		}
		if(!list.isEmpty()){
			System.out.println("signs:"+list.get(0).getSigns());
			if(null==list.get(0).getSigns()||"".equals(list.get(0).getSigns())){
				//签名为空，插入签名
				list.get(0).setSigns(sign);
				
				mng.saveOrUpdate(list.get(0));
				Version version = new Version();
				version.setVersionName(versionName);
				version.setVersionCode(versionCode);
				version.setSizes(sizes);
				version.setAndroidAppSec(list.get(0));
				version.setUsers(mng.getUserByUserName(userName));
				version.setGradle(5);
				if(!mng.saveDifferentVersion(version).equals(Constans.TRUE)){
					//该应用此版本已经存在
					return Constans.APP_VERSION_EXISTS;
				}
				mng.uploadApk(file,packageName,versionName);
				return Constans.TRUE;
			}
			if (sign.equals(list.get(0).getSigns())) {

				Version version = new Version();
				version.setVersionName(versionName);
				version.setVersionCode(versionCode);
				version.setSizes(sizes);
				version.setAndroidAppSec(list.get(0));
				version.setUsers(mng.getUserByUserName(userName));
				version.setGradle(5);
				
				if(!mng.saveDifferentVersion(version).equals(Constans.TRUE)){
					//该应用此版本已经存在
					return Constans.APP_VERSION_EXISTS;
				}
				mng.uploadApk(file,packageName,versionName);
				return Constans.TRUE;
			} else {
				//签名不同把他return掉。
				return "3";
			}
		}else{
			//该应用不存在保存应用,以及对应的版本
			AndroidChannelSec androidChannel=mng.findByChannelId(cid);		
			AndroidAppSec androidAppSec = new AndroidAppSec();
			androidAppSec.setAppName(appName);
			androidAppSec.setPackageName(packageName);
			androidAppSec.setChannel(androidChannel);
			androidAppSec.setVersionName(versionName);
			androidAppSec.setDecription(decription);
			androidAppSec.setVersionCode(versionCode);
			androidAppSec.setSigns(sign);
			androidAppSec.setDown(0);
			androidAppSec.setSize(sizes);
			
			Version version = new Version();
			version.setVersionName(versionName);
			version.setVersionCode(versionCode);
			version.setSizes(sizes);
			version.setAndroidAppSec(androidAppSec);
			version.setUsers(mng.getUserByUserName(userName));
			version.setGradle(5);
			mng.saveDifferentVersion(version);
			mng.uploadApk(file,packageName,versionName);
			return Constans.TRUE;
		}
		
	}
	@Autowired
	private AndroidAppSecMng mng;
	@Autowired
	private JsonMapper jsonMapper;
	
}
