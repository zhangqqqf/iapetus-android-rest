package net.tatans.android.mark.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tatans.android.mark.entity.AndroidAppMark;
import net.tatans.android.mark.manager.AndroidAppMarkMng;
import net.tatans.android.mark.manager.AndroidMobileIDMng;
import net.tatans.iapetus.android.rest.util.CryptOperation;
import net.tatans.iapetus.android.rest.util.JsonMapper;
import net.tatans.iapetus.android.rest.util.JsonUtil;

@Controller
@RequestMapping("/apkMark")
public class MarkServiceCrypt {
	@Autowired
	private AndroidAppMarkMng appMarkMng;
	@Autowired
	private JsonMapper jsonMapper;
	@Autowired
	private AndroidMobileIDMng mobileMng;
	
	private final static String secret = "19890807";
	
	/**
	 * 增加标签
	 * 获取前端app按钮标签的json数据，解析，保存到数据库
	 * @param packagename
	 * @param request
	 * @return @RequestParam(value = "markFile", required = true) MultipartFile markFile
	 */
	@ResponseBody
	@RequestMapping(value = "/addAppMark.do")
	public boolean addAppMark(String mobileId,String markStr,
			HttpServletRequest request) {
		boolean existMobileID = mobileMng.findMobileId(mobileId);
		boolean canSave = false;
		if(existMobileID && markStr != null && !"".equals(markStr)){
			System.out.println("加密加码："+markStr);
			String deMarkStr=null;
			try {
				deMarkStr = CryptOperation.aesDecrypt(markStr,secret);
				System.out.println("deMarkStr:"+deMarkStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(deMarkStr !=null && !"".equals(deMarkStr)){
				List<AndroidAppMark> list = JsonUtil.getDTOList(deMarkStr, AndroidAppMark.class);
				if(list.size()>0){
					//根据数据保存或更新
					appMarkMng.saveOrUpdate(list);
					canSave = true;
				}
			}
		}
		return canSave;
	}
	
	/**
	 * 查询标签
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAppMark.do")
	public String queryAppMark(String packageName,
			HttpServletRequest request) {
		if(packageName != null && !"".equals(packageName)){
			String json = null;
			String enJson = null;
			//获取该应用下的所有标签  转换成json
			List<AndroidAppMark> appList= appMarkMng.findMarkByPackageName(packageName);
			if(appList.size()>0){
				try {
					json = jsonMapper.toJsonStr(appList,
							new String[] {"packageName","packageSignature","viewName","text","locale","packageVersion","screenshotPath","timestamp"});
					enJson = CryptOperation.aesEncrypt(json,secret);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return enJson;
			}
		}
		return null;
	}

//	public static void main(String[] args) throws Exception {
//		System.out.println("xxx");
//		 HttpClient httpclient = new DefaultHttpClient();
//		 HttpPost httppost = new HttpPost("http://localhost:8080/android/rest/v1.0/apkMark/addAppMark.do");
//         
////		 String s="[{'packageName':'com.tieyou.train.ark','packageSignature':'403bd8307451d1d990e561336bae21f3d51705c5','viewName':'btnGrabTicketTest','text':'抢票喽','locale':'zh_CN','packageVersion':119,'screenshotPath':'','timestamp':'1447395136959'}]";
//		 List<AndroidAppMark> list = new ArrayList<>();
//		 AndroidAppMark map = new AndroidAppMark();
//		 map.setPackageName("com.tieyou.train.ark");
//		 map.setPackageSignature("403bd8307451d1d990e561336bae21f3d51705c5");
//		 map.setViewName("btnGrabTicketTest");
//		 map.setText("抢票喽");
//		 map.setLocale("zh_CN");
//		 map.setPackageVersion(119);
//		 map.setScreenshotPath("");
//		 map.setTimestamp("1447395136959");
//		 
//		 list.add(map);
//		 JsonMapper jsonMapper = new JsonMapper();
//		 String s=jsonMapper.toJsonStr(list, new String[] {"packageName","packageSignature","viewName","text","locale","packageVersion","screenshotPath","timestamp"});
//		 System.out.println("json:"+s);
//		 String ss =null;
//		try {
//			ss = CryptOperation.aesEncrypt(s,secret);
//			System.out.println("加密后的解密："+CryptOperation.aesDecrypt(ss,secret));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		 System.out.println("加密加码后："+ss);
//		 StringBody bin = new StringBody(ss);
//         StringBody comment = new StringBody("111");
//
//         MultipartEntity reqEntity = new MultipartEntity();
//         reqEntity.addPart("markStr", bin);//file1为请求后台的数据;属性
//         reqEntity.addPart("mobileId", comment);//filename1为请求后台的普通参数;属性
//         httppost.setEntity(reqEntity);
//           
//         HttpResponse response = httpclient.execute(httppost);
//         System.out.println("xxx");
//	}
	
}
