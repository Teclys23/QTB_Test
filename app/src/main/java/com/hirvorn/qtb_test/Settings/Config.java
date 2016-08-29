package com.hirvorn.qtb_test.Settings;

public class Config {

	private String lastCode;
	private String usersPath;
	private String resourcesPath;
	private String lastsessionFile;
	private String userExtension;
	
	
	public Config(String lastCode, String usersPath, String resourcesPath, String lastsessionFile, String userExtension){
		
		this.setLastCode(lastCode);
		this.setUsersPath(usersPath);
		this.setResourcesPath(resourcesPath);
		this.setLastsessionFile(lastsessionFile);
		this.setUserExtension(userExtension);
	}

	public String getLastCode() {
		return lastCode;
	}

	private void setLastCode(String lastCode) {
		this.lastCode = lastCode;
	}

	public String getUsersPath() {
		return usersPath;
	}

	public void setUsersPath(String usersPath) {
		this.usersPath = usersPath;
	}

	public String getResourcesPath() {
		return resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	public String getLastsessionFile() {
		return lastsessionFile;
	}

	public void setLastsessionFile(String lastsessionFile) {
		this.lastsessionFile = lastsessionFile;
	}

	public String getUserExtension(){
		return this.userExtension;
	}

	public void setUserExtension(String userExtension){
		this.userExtension = userExtension;
	}
}
