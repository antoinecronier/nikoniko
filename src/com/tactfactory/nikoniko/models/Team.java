package com.tactfactory.nikoniko.models;

import java.util.ArrayList;

import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;

public class Team extends DatabaseItem {
	public static final String TABLE = "team";
	public static final String[] FIELDS = { "id", "name", "serial" };

	private String name;
	private String serial;
	private ArrayList<Project> projects;
	private ArrayList<User> users;

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
	 * @return the serial
	 */
	public String getSerial() {
		return serial;
	}

	/**
	 * @param serial
	 *            the serial to set
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}

	/**
	 * @return the projects
	 */
	public ArrayList<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public Team(String name, String serial) {
		super(Team.TABLE, Team.FIELDS);
		this.name = name;
		this.serial = serial;
		this.projects = new ArrayList<Project>();
		this.users = new ArrayList<User>();
	}

	public Team() {
		super(Team.TABLE, Team.FIELDS);
		this.projects = new ArrayList<Project>();
		this.users = new ArrayList<User>();
	}
}
