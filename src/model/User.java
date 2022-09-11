package model;

public class User {
	private int iduser;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	public User(int iduser, String firstname, String lastname) {
		super();
		this.iduser = iduser;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	public User(int iduser, String firstname, String lastname, String email, String phone) {
		super();
		this.iduser = iduser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
	
	

}
