package net.tatans.iapetus.android.entity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xmlpull.v1.XmlPullParser;

import android.content.res.AXmlResourceParser;
import net.tatans.iapetus.android.entity.ApkInfo;

public class ApkUtil {
	private static final String namespace = "http://schemas.android.com/apk/res/android";

	// private static String
	// apkUrl="C:\\Users\\Administrator\\Desktop\\launcher.apk";
	public static ApkInfo apk(File apkFile) {
	    ZipFile zipFile = null;
	    ZipEntry zipEntry=null;
	    AXmlResourceParser parser = null;
	    int versionCode=0;
        String versionName="";
        String packageName="";
        String icon="";
        double size=apkFile.length()/1024.0/1024.0;
	    try {
	        zipFile = new ZipFile(apkFile);
	        zipEntry = zipFile.getEntry("AndroidManifest.xml");
            parser = new AXmlResourceParser();
            parser.open(zipFile.getInputStream(zipEntry));
            int eventType = parser.getEventType();  
            while (eventType != XmlPullParser.END_DOCUMENT) {
                
                if (eventType == XmlPullParser.START_TAG) {
                	
                    int count = parser.getAttributeCount();
                    for (int i = 0; i < count; i++) {
                        String name = parser.getAttributeName(i);
                         if(name.equals("versionCode")){
                        	versionCode=parser.getAttributeIntValue(i, 0);
                        }
                        if(name.equals("versionName")){
                        	versionName=parser.getAttributeValue(i);
                        }
                        if(name.equals("package")){
                        	packageName=parser.getAttributeValue(i);
                        }
                        if(name.equals("icon")){
                        	icon=parser.getAttributeValue(i);
                        	System.out.println(123);
                        }
                    }//end for
                }
                eventType = parser.next();
            }// end while  
            System.out.println("versionCode:"+versionCode+"  versionName:"+versionName + " packageName:"+packageName+" icon:"+icon);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }finally {
			try {
				parser.close();
				zipFile.close();

			} catch (IOException e) {
			}
		}
	    return new ApkInfo(packageName, versionCode, versionName, size,icon,"");
	}
	
	/** 
     * 从APK中读取签名 
     * @param file 
     * @return 
     * @throws IOException 
     */  
	public static String getSignaturesFromApk(File file) throws IOException {  
        List<String> signatures=new ArrayList<String>();  
        JarFile jarFile=new JarFile(file);  
        try {  
        	StringBuilder sb = new StringBuilder();
            JarEntry je=jarFile.getJarEntry("AndroidManifest.xml");  
            byte[] readBuffer=new byte[8192];  
            Certificate[] certs=loadCertificates(jarFile, je, readBuffer);  
            if(certs != null) {  
                for(Certificate c: certs) {  
                    String sig=toCharsString(c.getEncoded());  
                    signatures.add(sig);  
                }  
            }
            
            for (int i = 0; i <signatures.size() ; i++) {
            	System.out.println("wo:"+signatures.get(i));
                sb.append(signatures.get(i));
            }
            if(sb!=null){
            	return sb.toString();
            }
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
        return "";
    }  

	/** 
     * 加载签名 
     * @param jarFile 
     * @param je 
     * @param readBuffer 
     * @return 
     */  
    public static Certificate[] loadCertificates(JarFile jarFile, JarEntry je, byte[] readBuffer) {  
        try {  
            InputStream is=jarFile.getInputStream(je);  
            while(is.read(readBuffer, 0, readBuffer.length) != -1) {  
            }  
            is.close();  
            return je != null ? je.getCertificates() : null;  
        } catch(IOException e) {  
        }  
        return null;  
    } 
    /** 
     * 将签名转成转成可见字符串 
     * @param sigBytes 
     * @return 
     */  
    public static String toCharsString(byte[] sigBytes) {  
        byte[] sig=sigBytes;  
        final int N=sig.length;  
        final int N2=N * 2;  
        char[] text=new char[N2];  
        for(int j=0; j < N; j++) {  
            byte v=sig[j];  
            int d=(v >> 4) & 0xf;  
            text[j * 2]=(char)(d >= 10 ? ('a' + d - 10) : ('0' + d));  
            d=v & 0xf;  
            text[j * 2 + 1]=(char)(d >= 10 ? ('a' + d - 10) : ('0' + d));  
        }  
        return new String(text);
    }
//	public static File apkIco(File apkFile) {
//		ZipFile zipFile = null;
//	    ZipEntry zipEntry=null;
//	    AXmlResourceParser parser = null;
//		try{
//			zipFile=new  ZipFile(apkFile);
//			zipEntry=zipEntry.getName("");
//			
//		}catch(Exception e){
//			
//		}
//		return null;
//	}
	private static String apkUrl="C:\\Users\\Administrator\\Desktop\\launcher.apk";
	public static void main(String[] args) {
		apk(new File(apkUrl));
	}
}
