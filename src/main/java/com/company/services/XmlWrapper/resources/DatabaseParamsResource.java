package com.company.services.XmlWrapper.resources;

@SuppressWarnings("UnusedDeclaration")
public class DatabaseParamsResource {
	private final String host;
	private final String password;
	private final String user;
	
	public DatabaseParamsResource() {
		this.host = "";
		this.password = "";
		this.user = "";
	}
	
	public DatabaseParamsResource(String host, String password, String user) {
		this.host = host;
		this.password = password;
		this.user = user;
	}

	public String getHost() {
		return host;
	}

	public String getPassword() {
		return password;
	}

	public String getUser() {
		return user;
	}
	
	@Override
	public String toString() {
		return String.format("DatabaseParamsResource { host = %s; user = %s; password = %s }",
				this.host, this.user, this.password);
	}
}
