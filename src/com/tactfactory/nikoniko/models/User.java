package com.tactfactory.nikoniko.models;

import java.util.ArrayList;

import com.tactfactory.nikoniko.models.security.SecurityUser;

public class User extends SecurityUser {
	private String lastname;
	private String firstname;
	private String registration_cgi;
	private ArrayList<NikoNiko> nikoNikos;
	private ArrayList<Team> teams;

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the registration_cgi
	 */
	public String getRegistration_cgi() {
		return registration_cgi;
	}
	/**
	 * @param registration_cgi the registration_cgi to set
	 */
	public void setRegistration_cgi(String registration_cgi) {
		this.registration_cgi = registration_cgi;
	}
	/**
	 * @return the nikoNikos
	 */
	public ArrayList<NikoNiko> getNikoNikos() {
		return nikoNikos;
	}
	/**
	 * @param nikoNikos the nikoNikos to set
	 */
	public void setNikoNikos(ArrayList<NikoNiko> nikoNikos) {
		this.nikoNikos = nikoNikos;
	}
	/**
	 * @return the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}
	/**
	 * @param teams the teams to set
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public User(){
		this.nikoNikos = new ArrayList<NikoNiko>();
		this.teams = new ArrayList<Team>();
	}
}
