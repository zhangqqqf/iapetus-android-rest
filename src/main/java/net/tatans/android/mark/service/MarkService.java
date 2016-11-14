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
import net.tatans.iapetus.android.rest.util.JsonMapper;
import net.tatans.iapetus.android.rest.util.JsonUtil;

@Controller
@RequestMapping("/mark")
public class MarkService {
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
	public boolean addAppMark(String mobileId,String markStr,
			HttpServletRequest request) {
		boolean existMobileID = mobileMng.findMobileId(mobileId);
		boolean canSave = false;
		if(existMobileID && markStr != null && !"".equals(markStr)){
				List<AndroidAppMark> list = JsonUtil.getDTOList(markStr, AndroidAppMark.class);
				if(list.size()>0){
					//根据数据保存或更新
					appMarkMng.saveOrUpdate(list);
					canSave = true;
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
			//获取该应用下的所有标签  转换成json
			List<AndroidAppMark> appList= appMarkMng.findMarkByPackageName(packageName);
			if(appList.size()>0){
				try {
					json = jsonMapper.toJsonStr(appList,
							new String[] {"packageName","packageSignature","viewName","text","locale","packageVersion","screenshotPath","timestamp"});
				} catch (Exception e) {
					e.printStackTrace();
				}
				return json;
			}
		}
		return null;
	}
	
//	public static void main(String[] args) throws ClientProtocolException, IOException {
//		System.out.println("xxx");
//		 HttpClient httpclient = new DefaultHttpClient();
//		 HttpPost httppost = new HttpPost("http://localhost:8080/android/rest/v1.0/mark/addAppMark.do");
//         
////		 FileBody bin = new FileBody(new File("C:"+File.separator+"Users"+File.separator+"windows7"+File.separator+"Desktop"+File.separator+"c.txt"));
//		 String s ="[{'packageName':'com.tieyou.train.ark','packageSignature':'403bd8307451d1d990e561336bae21f3d51705c5','viewName':'btnGrabTicketTest','text':'抢票','locale':'zh_CN','packageVersion':119,'screenshotPath':'','timestamp':'1447395136959'}]";
//		 StringBody bin = new StringBody(s,Charset.forName("UTF-8"));
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
