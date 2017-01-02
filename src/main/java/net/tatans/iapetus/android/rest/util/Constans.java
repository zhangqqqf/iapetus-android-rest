package net.tatans.iapetus.android.rest.util;

import net.tatans.android.common.oss.OssUtil;

public class Constans {
	public static final Integer pageSize=7;//androidApp列表每页7条
	
	public static String accessKeyId="74rBtwkmNsAIedXF";
	public static String accessKeySecret="Nkwju9KO61ahJAOK9NDirCJT3Gf5Bx";
	public static String bucketName="tatans-bucket-other";//tatans-bucket-test tatans-bucket-other
	public final static String ANDROID_MARK_PREFIX="appmark";
	
	public final static String ANDROID_bucketName="androd-app-store";//测试时候改成tatans-bucket-test tatans-bucket-other
	public final static String ANDROID_APP_PREFIX="apksource";
	public final static String ANDROID_IMGE_PREFIX="img";
	
	public static String apkPath(String packageName,String versionName,String suffix){
		return ANDROID_APP_PREFIX+"/"+packageName+"/"+versionName+suffix;
	}
	public static String apkIconPath(String packageName,String versionName,String suffix){
		return ANDROID_IMGE_PREFIX+"/"+packageName+"/"+versionName+suffix;
	}
	
	public static String apkNewPath(String mobileModel,String packageName,String versionName,String suffix){
		return ANDROID_APP_PREFIX+"/"+mobileModel+"/"+packageName+"/"+versionName+suffix;
	}
	public static String apkNewIconPath(String mobileModel,String packageName,String versionName,String suffix){
		return ANDROID_IMGE_PREFIX+"/"+mobileModel+"/"+packageName+"/"+versionName+suffix;
	}
	/**
	 * base模块到user模块获取用户相关信息
	 */
	/**Purpose:apk文件保存路径
	 * @author 周焕
	
	 * Create Time: 2014年7月17日 下午3:54:03
	
	 * @param packageName
	 * @param version
	 * @return
	
	 * Version: 1.0
	 */
	public static String androidFilePath(String packageName ,String version){
		String filePath=OssUtil.fileFormat("apksource"+"/"+packageName+
				"/"+version+"/"+packageName+version+".apk");
		return filePath;
	}
	
	public static String androidSecFilePath(String mobileModel,String packageName ,String version){
		String filePath=OssUtil.fileFormat("apksource"+"/"+mobileModel+"/"+packageName+
				"/"+version+"/"+packageName+version+".apk");
		return filePath;
	}
	
}
