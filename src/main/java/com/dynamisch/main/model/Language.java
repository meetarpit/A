package com.dynamisch.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Language {
	
	@Id
	@GeneratedValue
	private int id;
	private String booking;
	private String dashboard;
	private String createAdmin;
	private String admin_List;
	private String mr_mS;
	private String surname;
	private String gender;
	private String email;
	private String password;
	private String datecreated;
	private String dateUpdated;
	private String phone;
	private String address;
	private String settings;
	private String lang1;
	private String lang2;
	
	
	
    
	
	public String getLang1() {
		return lang1;
	}
	public void setLang1(String lang1) {
		this.lang1 = lang1;
	}
	public String getLang2() {
		return lang2;
	}
	public void setLang2(String lang2) {
		this.lang2 = lang2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDashboard() {
		return dashboard;
	}
	public void setDashboard(String dashboard) {
		this.dashboard = dashboard;
	}
	public String getCreateAdmin() {
		return createAdmin;
	}
	public void setCreateAdmin(String createAdmin) {
		this.createAdmin = createAdmin;
	}
	public String getMr_mS() {
		return mr_mS;
	}
	public void setMr_mS(String mr_mS) {
		this.mr_mS = mr_mS;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSettings() {
		return settings;
	}
	public void setSettings(String settings) {
		this.settings = settings;
	}
	
	public String getBooking() {
		return booking;
	}
	public void setBooking(String booking) {
		this.booking = booking;
	}
	public String getAdmin_List() {
		return admin_List;
	}
	public void setAdmin_List(String admin_List) {
		this.admin_List = admin_List;
	}
	@Override
	public String toString() {
		return "Language [id=" + id + ", dashboard=" + dashboard + ", createAdmin=" + createAdmin + ", mr_mS=" + mr_mS
				+ ", surname=" + surname + ", gender=" + gender + ", email=" + email + ", password=" + password
				+ ", datecreated=" + datecreated + ", dateUpdated=" + dateUpdated + ", phone=" + phone + ", address="
				+ address + ", settings=" + settings + "]";
	}
	
	
	
	

}
