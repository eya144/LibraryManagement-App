package model;

import java.util.Date;

public class Issue {
	private int idissue;
	private String firstname;
	private String lastname;
	private String title;
	private Date date;

	public Issue(int idissue, String firstname, String lastname, String title, Date date) {
		super();
		this.idissue = idissue;
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.date = date;
	}

	

	public int getIdissue() {
		return idissue;
	}



	public void setIdissue(int idissue) {
		this.idissue = idissue;
	}



	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Issue [idissue=" + idissue + ", firstname=" + firstname + ", lastname=" + lastname + ", title=" + title
				+ ", date=" + date + "]";
	}

}
