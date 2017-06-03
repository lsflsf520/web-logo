package com.fshl.xy.logo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.ujigu.secure.common.utils.BaseConfig;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class DocUtil {

	private static Configuration configure = null;
	static{
		configure= new Configuration(); 
		configure.setDefaultEncoding("UTF-8");
	}
	
	public static void writeHttpResponse(HttpServletResponse response, String tmplFileName, Map<String, Object> dataMap, String fileName) throws TemplateException, IOException {
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".doc");
			response.setContentType("application/msword;charset=UTF-8");
			writeDoc(response.getOutputStream(), tmplFileName, dataMap);
	}

	/**        
	 * 根据Doc模板生成word文件         
	* @param dataMap Map 需要填入模板的数据        
	* @param fileName 文件名称         
	* @param savePath 保存路径         
	 * @throws IOException 
	 * @throws TemplateException 
	*/
	public static void createDoc(String savePath,  String tmplFileName, Map<String, Object> dataMap) throws IOException, TemplateException{
		File outfile = new File(savePath);
		
		OutputStream out = new FileOutputStream(outfile);
		
		writeDoc(out, tmplFileName, dataMap);
	}
	
	public static void writeDoc(OutputStream out, String tmplFileName, Map<String, Object> dataMap) throws IOException, TemplateException{
//		configure.setClassForTemplateLoading(DocUtil.class, "doctmpl");
		String basePath = BaseConfig.getPath("application.properties");
		if(basePath.startsWith("file:/")){
			basePath = basePath.replace("file:", "");
		}
		basePath = basePath.replace("application.properties", "doctmpl");
		
		Writer writer = null;
		try{
			configure.setDirectoryForTemplateLoading(new File(basePath));
			configure.setObjectWrapper(new DefaultObjectWrapper());
			configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			
			Template templ = configure.getTemplate(tmplFileName + ".xml");
			
			writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
			templ.process(dataMap, writer);
			
			out.flush();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零
     * 要用到正则表达式
     */
    public static String digitUppercase(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
 
        String head = n < 0? "负": "";
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
    
    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
          in = new FileInputStream(imgFile);
          data = new byte[in.available()];
          in.read(data);
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return Base64.encodeBase64String(data);
//        return encoder.encode(data);
    }
	
//	public static void main(String[] args) throws IOException, TemplateException {
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("company", "网吧公司");
//		dataMap.put("logoName", "好运来");
//		dataMap.put("customerAddr", "梅溪湖街道办事处");
//		dataMap.put("date", "2016 年 7 月 3 日");
//		
//		createDoc("E:/software/test.doc", "delegate", dataMap);
		
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("company", "网吧公司");
//		dataMap.put("logoName", "好运来");
//		dataMap.put("customerAddr", "梅溪湖街道办事处");
//		dataMap.put("phone", "15296481397");
//		dataMap.put("userName", "王傻帽");
//		dataMap.put("num", 1);
//		dataMap.put("totalPrice", 1400);
//		dataMap.put("bigTotalPrice", "壹千肆佰元");
//		dataMap.put("logoTypes", "31");
//		
//		createDoc("E:/software/代理协议书.doc", "agent", dataMap);
		
//		System.out.println(getImageStr());
//	}
	
}
