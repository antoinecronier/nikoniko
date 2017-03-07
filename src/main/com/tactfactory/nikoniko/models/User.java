package com.tactfactory.nikoniko.models;

import java.util.ArrayList;

import com.tactfactory.nikoniko.models.security.SecurityUser;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;
import com.tactfactory.nikoniko.utils.mysql.MySQLTypes;

public class User extends SecurityUser {

	public static final String TABLE = "user";
	public static final String[] FIELDS = { "id", "login", "password", "sex", "lastname", "firstname",
			"registration_cgi" };

	@MySQLAnnotation(fieldName = "lastname", mysqlType = MySQLTypes.VARCHAR)
	private String lastname;

	@MySQLAnnotation(fieldName = "firstname", mysqlType = MySQLTypes.VARCHAR)
	private String firstname;

	@MySQLAnnotation(fieldName = "registration_cgi", mysqlType = MySQLTypes.VARCHAR)
	private String registration_cgi;

	@MySQLAnnotation(fieldName = "id_User", mysqlType = MySQLTypes.ASSOCIATION,
			associationTable = "nikoniko", nullable = true)
	private ArrayList<NikoNiko> nikoNikos;

	@MySQLAnnotation(fieldName = "id_User", mysqlType = MySQLTypes.ASSOCIATION,
			associationTable = "user_team", nullable = true)

	private ArrayList<Team> teams;

	@MySQLAnnotation(fieldName = "sex", mysqlType = MySQLTypes.VARCHAR)
	private char sex;

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the sex
	 */
	public char getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(char sex) {
		this.sex = sex;
	}

	/**
	 * @param lastname
	 *            the lastname to set
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
	 * @param firstname
	 *            the firstname to set
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
	 * @param registration_cgi
	 *            the registration_cgi to set
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
	 * @param nikoNikos
	 *            the nikoNikos to set
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
	 * @param teams
	 *            the teams to set
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public User(String login, String password, String lastname, String firstname, String registration_cgi) {
		super(User.TABLE, User.FIELDS, login, password);
		this.lastname = lastname;
		this.firstname = firstname;
		this.registration_cgi = registration_cgi;
		this.nikoNikos = new ArrayList<NikoNiko>();
		this.teams = new ArrayList<Team>();
	}

	public User() {
		super(User.TABLE, User.FIELDS);
		this.nikoNikos = new ArrayList<NikoNiko>();
		this.teams = new ArrayList<Team>();
	}

	public User(String firstname, String lastname) {
		this(firstname, lastname, 'I');
	}

	public User(String firstname, String lastname, char sex) {
		super(User.TABLE, User.FIELDS);
		this.lastname = lastname;
		this.firstname = firstname;
		this.sex = sex;
		this.nikoNikos = new ArrayList<NikoNiko>();
		this.teams = new ArrayList<Team>();
	}
}
