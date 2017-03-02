package com.tactfactory.nikoniko.models;

import java.util.ArrayList;
import java.util.Date;

import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;
import com.tactfactory.nikoniko.utils.mysql.MySQLTypes;

public class Project extends DatabaseItem {
	
	public static final String TABLE = "project";
<<<<<<< HEAD
	public static final String[] FIELDS = { "id", "name", "start_Date",
			"end_Date" };
=======
	public static final String[] FIELDS = { "id", "name", "start_Date", "end_Date" };
>>>>>>> master

	@MySQLAnnotation(fieldName = "name", mysqlType = MySQLTypes.VARCHAR)
	private String name;

	@MySQLAnnotation(fieldName = "start_Date", mysqlType = MySQLTypes.DATETIME, nullable = true)
	private Date start_date;

	@MySQLAnnotation(fieldName = "end_Date", mysqlType = MySQLTypes.DATETIME, nullable = true)
	private Date end_date;

	@MySQLAnnotation(mysqlType = MySQLTypes.ASSOCIATION, associationTable = "nikoniko")
	private ArrayList<NikoNiko> nikoNikos;

	@MySQLAnnotation(mysqlType = MySQLTypes.ASSOCIATION, associationTable = "team_project")
	private ArrayList<Team> teams;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date
	 *            the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date
	 *            the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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

	public Project(String name, Date start_date) {
		super(Project.TABLE, Project.FIELDS);
		this.name = name;
		this.start_date = start_date;
		this.teams = new ArrayList<Team>();
		this.nikoNikos = new ArrayList<NikoNiko>();
	}

	public Project(String name, Date start_date, Date end_date) {
		super(Project.TABLE, Project.FIELDS);
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public Project() {
		super(Project.TABLE, Project.FIELDS);
		this.nikoNikos = new ArrayList<NikoNiko>();
		this.teams = new ArrayList<Team>();
	}
}
