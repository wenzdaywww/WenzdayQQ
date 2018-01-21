package www.model;

public class User {
	private String userId;
	private String userPwd;
	private String userAge;
	private String userSex;
	private String userNickname;
	private String userBirthday;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userNickname;
	}
	public void setUserName(String userName) {
		this.userNickname = userName;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	@Override
	public String toString() {
		return userId + " " +userNickname + " " + userSex + " " +userAge + " " + userBirthday;
	}
	
}
