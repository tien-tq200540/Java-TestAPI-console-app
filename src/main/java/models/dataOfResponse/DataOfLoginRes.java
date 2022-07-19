package models.dataOfResponse;

import models.User;

public class DataOfLoginRes {
	public static final String token_type = "bearer";
	private String access_token;
	public User user;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

}
