package com.fshl.xy.logo.service.impl;

import java.io.File;

import com.ujigu.secure.common.utils.BaseConfig;
import com.ujigu.secure.common.utils.CmdExecUtil;
import com.ujigu.secure.common.utils.DateUtil;



public class BackupService {
	
	private String host;
	private int port;
	private String database;
	private String userName;
	private String password;
	
	public void backupData(){
		
		String homePath = BaseConfig.getValue("mysql.home.path");
		String backupPath = BaseConfig.getValue("data.backup.path");
		File dir = new File(backupPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		CmdExecUtil.execCmd(homePath + "/bin/mysqldump -h" + host + " -P" + port + " -u" + userName + " -p" + password + " " + database + " --result-file=" + backupPath + "/logo_" + DateUtil.getCurrentDateStr() + ".sql");
		
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
