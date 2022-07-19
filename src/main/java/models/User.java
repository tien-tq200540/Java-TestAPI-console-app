package models;

public class User {
	private String name;
	private String role;
	private String avatar;
	private String email;
	private String password;
	private String address;
	private String phone;
	private String user_id;
	
	public User() {		
	}
	public User(String name, String role, String avatar, String email, String address, String phone) {
	    this.name = name;
	    this.role = role;
	    this.avatar = avatar;
	    this.email = email;
	    this.address = address;
	    this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void display() {
		System.out.println(getName());
		System.out.println(getRole());
		System.out.println(getAvatar());
		System.out.println(getEmail());
		System.out.println(getAddress());
		System.out.println(getPhone());
		System.out.println(getUser_id());
	}
}
