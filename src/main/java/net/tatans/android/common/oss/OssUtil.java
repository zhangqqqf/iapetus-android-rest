package net.tatans.android.common.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.tatans.iapetus.android.rest.util.Constans;

import org.apache.commons.io.FileUtils;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.openservices.oss.model.InitiateMultipartUploadResult;
import com.aliyun.openservices.oss.model.ListObjectsRequest;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PartETag;
import com.aliyun.openservices.oss.model.UploadPartRequest;
import com.aliyun.openservices.oss.model.UploadPartResult;

public class OssUtil {

	private final static OSSClient client=new OSSClient(Constans.accessKeyId, Constans.accessKeySecret);
	private final static String bucketName=Constans.bucketName;
	
	/**Purpose:上传流
	 * @author 周焕
	
	 * Create Time: 2014年10月10日 上午10:49:58
	
	 * @param file
	 * @param objectMetadata
	 * @param key
	 * @throws OSSException
	 * @throws ClientException
	 * @throws IOException
	
	 * Version: 1.0
	 */
	public static void uploadInputStream(InputStream ins, ObjectMetadata objectMetadata,
			String key) throws OSSException, ClientException, IOException {
		client.putObject(bucketName, fileFormat(key), ins, objectMetadata);
	}

	/**Purpose:上传文件
	 * @author 周焕
	
	 * Create Time: 2014年10月10日 上午9:21:03
	
	 * @param file
	 * @param objectMetadata
	 * @param key
	 * @throws OSSException
	 * @throws ClientException
	 * @throws IOException
	
	 * Version: 1.0
	 */
	public static void uploadFile(File file, ObjectMetadata objectMetadata,
			String key) throws OSSException, ClientException, IOException {
		client.putObject(bucketName, fileFormat(key), FileUtils.openInputStream(file), objectMetadata);
	}
//	上传
	
	/**Purpose:分段上传文件
	 * @author 周焕
	
	 * Create Time: 2014年10月10日 上午9:21:23
	
	 * @param file
	 * @param partSize：分为多少部分
	 * @param initiateMultipartUploadRequest
	 * @param key
	 * @throws IOException
	
	 * Version: 1.0
	 */
	public static void multipartFile(File file, int partSize,
			InitiateMultipartUploadRequest initiateMultipartUploadRequest,
			String key) throws IOException {
		
		InitiateMultipartUploadResult initiateMultipartUploadResult =
                client.initiateMultipartUpload(initiateMultipartUploadRequest);
		// 计算分块数目
		int partCount = (int) (file.length() / partSize);
		if (file.length() % partSize != 0){
		    partCount++;
		}

		// 新建一个List保存每个分块上传后的ETag和PartNumber
		List<PartETag> partETags = new ArrayList<PartETag>();

		for(int i = 0; i < partCount; i++){
		    // 获取文件流
		    FileInputStream fis = new FileInputStream(file);

		    // 跳到每个分块的开头
		    long skipBytes = partSize * i;
		    fis.skip(skipBytes);

		    // 计算每个分块的大小
		    long size = partSize < file.length() - skipBytes ?
		            partSize : file.length() - skipBytes;

		    // 创建UploadPartRequest，上传分块
		    UploadPartRequest uploadPartRequest = new UploadPartRequest();
		    uploadPartRequest.setBucketName(bucketName);
		    uploadPartRequest.setKey(fileFormat(key));
		    uploadPartRequest.setUploadId(initiateMultipartUploadResult.getUploadId());
		    uploadPartRequest.setInputStream(fis);
		    uploadPartRequest.setPartSize(size);
		    uploadPartRequest.setPartNumber(i + 1);
		    UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);

		    // 将返回的PartETag保存到List中。
		    partETags.add(uploadPartResult.getPartETag());

		    // 关闭文件
		    fis.close();
		}
	}

	
//	删除文件
	/**Purpose:周焕
	 * @author 删除指定文件
	
	 * Create Time: 2014年10月10日 上午9:23:43
	
	 * @param key
	
	 * Version: 1.0
	 */
	public static void deleteFile(String key){
		client.deleteObject(bucketName, fileFormat(key));
	}
	
	/**Purpose:周焕
	 * @author 批量删除文件
	
	 * Create Time: 2014年10月10日 上午9:23:43
	
	 * @param prefix,文件开头
	
	 * Version: 1.0
	 */
	public static void deleteMultipartFile(String prefix,boolean isDirestory){
//		格式啊目录地址
		if(isDirestory){
			prefix=directoryFormat(prefix);
		}
//		获取key
		List<String> keyLi=getKeys(prefix);
//		循环删除key		
		for(String key:keyLi){
			client.deleteObject(bucketName, key);
		}
	}
//	拷贝目录
	public static void copyDirectory(String oldPath,String newPath){
		oldPath=directoryFormat(oldPath);
		newPath=directoryFormat(newPath);
		List<String> keyLi=getKeys(oldPath);
		for(String key:keyLi){
			client.copyObject(bucketName, key, bucketName, key.replace(oldPath, newPath));
			client.deleteObject(bucketName, key);
		}
	}
	
// 获取文件key
	/**Purpose:根据key开头获取keys  
	 * @author 周焕
	
	 * Create Time: 2014年10月15日 下午3:26:18
	
	 * @param prefix
	 * @return
	
	 * Version: 1.0
	 */
	private static List<String> getKeys(String prefix){
		List<String> keyLi=new ArrayList<>();
		ListObjectsRequest objectsRequest=new ListObjectsRequest(bucketName);
		objectsRequest.setPrefix(prefix);
		objectsRequest.setMaxKeys(1000);
		ObjectListing listing=client.listObjects(objectsRequest);
		for(OSSObjectSummary objectSummary :listing.getObjectSummaries()){
			keyLi.add(objectSummary.getKey());
		}
		String marker=listing.getNextMarker();
		while(listing.getObjectSummaries().size()!=0&&marker!=null){
			objectsRequest.setPrefix(prefix);
			objectsRequest.setMaxKeys(1000);
			objectsRequest.setMarker(marker);
			listing=client.listObjects(objectsRequest);
			for(OSSObjectSummary objectSummary :listing.getObjectSummaries()){
				keyLi.add(objectSummary.getKey());
			}
			marker=listing.getNextMarker();
		}
		return keyLi;
	}
//	/**Purpose:获取文件
//	 * @author 周焕
//	
//	 * Create Time: 2014年10月11日 上午9:54:51
//	
//	 * @param key 
//	 * @return String
//	
//	 * Version: 1.0
//	 * @throws UnsupportedEncodingException 
//	 */
//	public static InputStream getFile(String key) throws UnsupportedEncodingException{
//		key=fileFormat(key);
//		OSSObject object=client.getObject(bucketName, key);
//		InputStream ins=object.getObjectContent();
//		return ins;
//	}
	/**Purpose:获取文件
	 * @author 周焕
	
	 * Create Time: 2014年10月11日 上午9:54:51
	
	 * @param key 
	 * @return String
	
	 * Version: 1.0
	 * @throws UnsupportedEncodingException 
	 */
	public static String getFileAddress(String key) {
		
		return "http://other.tatans.net/"+fileFormat(key);
	}
	/**Purpose:验证key是否存在
	 * @author 周焕
	
	 * Create Time: 2014年10月11日 上午9:53:03
	
	 * @param key
	 * @return
	
	 * Version: 1.0
	 */
	public static boolean verifyKey(String key){
		try {
			client.getObject(bucketName, key);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
//	file文件地址格式化
	public static String fileFormat(String key){
		key=key.replaceAll("^/+", "");
		key=key.replaceAll("/+$", "");
		return key;
	}
//	direstory目录地址格式化
	public static String directoryFormat(String key){
		key=key.replaceAll("^/+", "");
		key=key.replaceAll("/+$", "")+"/";
		return key;
	}
}
