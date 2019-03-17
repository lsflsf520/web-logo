package com.fshl.xy.logo.util;

import java.util.HashMap;
import java.util.Map;

import com.xyz.tools.common.bean.Pair;
import com.xyz.tools.common.exception.BaseRuntimeException;

public class PassportUtil {
	
//	public final static String TK_VALUE = "xxyyuueee";
	
	/**
	 * 销售人员的用户名、密码及token
	 * 
	 */
	private final static Map<String /*username*/, Pair<String/*password*/, String/*uid_priv_token*/>> saleInfoMap = new HashMap<>();
	
	private final static Map<String/*token*/, Pair<Integer/*uid*/, String/*priv*/>> token2UidPrivMap = new HashMap<>();
	
	static {
		saleInfoMap.put("siyufank", new Pair<String, String>("335869", "xxyyuueee"));
		saleInfoMap.put("genghui2", new Pair<String, String>("gh2018", "yy377osuoEywyoioihhqq23"));
		saleInfoMap.put("lsflsf520", new Pair<String, String>("335869", "yiuwe7783ljoyyoHlljwuyy477"));
		
		saleInfoMap.put("genghui", new Pair<String, String>("gh2018", "reduwwwyyoouuu323ee"));
		
		token2UidPrivMap.put("xxyyuueee", new Pair<Integer, String>(1000, "rw"));
		token2UidPrivMap.put("yy377osuoEywyoioihhqq23", new Pair<Integer, String>(1001, "rw"));
		token2UidPrivMap.put("yiuwe7783ljoyyoHlljwuyy477", new Pair<Integer, String>(1002, "rw"));
		
		token2UidPrivMap.put("reduwwwyyoouuu323ee", new Pair<Integer, String>(1990, "r"));
	}
	
	/**
	 * 如果用户名和密码校验通过，则返回对应的token
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String canLogin(String userName, String password) {
		Pair<String, String> passInfo = saleInfoMap.get(userName);
		if(passInfo == null || !passInfo.first.equals(password)) {
			throw new BaseRuntimeException("ILLEGAL_USER", "用户名或密码不正确");
		}
		
		return passInfo.second;
	}
	
	/**
	 * 获取当前登录用户的uid
	 * @param token
	 * @return
	 */
	public static int getUidByToken(String token) {
		Pair<Integer, String> userPriv = token2UidPrivMap.get(token);
		if(userPriv == null) {
			throw new BaseRuntimeException("NOT_LOGON", "请先登录");
		}
		
		return userPriv.first;
	}
	
	/**
	 * 是否可读写
	 * @param token
	 * @return
	 */
	public static boolean canReadWrite(String token) {
		Pair<Integer, String> userPriv = token2UidPrivMap.get(token);
		if(userPriv == null) {
			throw new BaseRuntimeException("NOT_LOGON", "请先登录");
		}
		
		return "rw".equals(userPriv.second);
	}
	
	/**
	 * 是否只读
	 * @param token
	 * @return
	 */
	public static boolean isReadOnly(String token) {
		Pair<Integer, String> userPriv = token2UidPrivMap.get(token);
		if(userPriv == null) {
			throw new BaseRuntimeException("NOT_LOGON", "请先登录");
		}
		
		return "r".equals(userPriv.second);
	}

}
