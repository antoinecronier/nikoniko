package com.tactfactory.nikoniko.models;

import java.util.ArrayList;

import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;

public class Team extends DatabaseItem {
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
	 * @param name the name to set
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
	 * @param serial the serial to set
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
	 * @param projects the projects to set
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
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public Team(){
		this.projects = new ArrayList<Project>();
		this.users = new ArrayList<User>();
	}
}
