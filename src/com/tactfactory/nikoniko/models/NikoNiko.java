package com.tactfactory.nikoniko.models;

import java.util.Date;

import com.tactfactory.nikoniko.manager.NikoNikoManager;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;

public class NikoNiko extends DatabaseItem {
	private Date log_date;
	private Date change_date;
	private int satisfaction;
	private String comment;
	private Boolean isAnonymous;
	private User user;
	private Project project;

	/**
	 * @return the log_date
	 */
	public Date getLog_date() {
		return log_date;
	}
	/**
	 * @param log_date the log_date to set
	 */
	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}
	/**
	 * @return the change_date
	 */
	public Date getChange_date() {
		return change_date;
	}
	/**
	 * @param change_date the change_date to set
	 */
	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}
	/**
	 * @return the satisfaction
	 */
	public int getSatisfaction() {
		return satisfaction;
	}
	/**
	 * @param satisfaction the satisfaction to set
	 */
	public void setSatisfaction(int satisfaction) {
		this.satisfaction = NikoNikoManager.satisfactionRule(satisfaction);
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the isAnonymous
	 */
	public Boolean getIsAnonymous() {
		return isAnonymous;
	}
	/**
	 * @param isAnonymous the isAnonymous to set
	 */
	public void setIsAnonymous(Boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	public NikoNiko(User user, Project project, int satisfaction) {
		super();
		this.user = user;
		this.project = project;
		this.setSatisfaction(satisfaction);
		this.log_date = new Date();

		this.user.getNikoNikos().add(this);
		this.project.getNikoNikos().add(this);
	}

	public NikoNiko(User user, Project project, int satisfaction, String comment) {
		this(user,project,satisfaction);
		this.comment = comment;
	}

	public NikoNiko(User user, Project project, int satisfaction, Boolean isAnonimous) {
		this(user,project,satisfaction);
		this.isAnonymous = isAnonimous;
	}

	public NikoNiko(User user, Project project, int satisfaction, String comment, Boolean isAnonimous) {
		this(user,project,satisfaction);
		this.comment = comment;
		this.isAnonymous = isAnonimous;
	}
}
