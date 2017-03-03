package com.tactfactory.nikoniko.models;

import java.util.Date;

import com.tactfactory.nikoniko.manager.NikoNikoManager;
import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;
import com.tactfactory.nikoniko.utils.mysql.MySQLAnnotation;
import com.tactfactory.nikoniko.utils.mysql.MySQLTypes;

public class NikoNiko extends DatabaseItem {
	
	public static final String TABLE = "nikoniko";
	public static final String[] FIELDS = { "id", "log_Date", "change_Date", "satisfaction", "nikoniko_comment",
			"isanonymous", "id_User", "id_Project" };

	@MySQLAnnotation(fieldName = "log_Date", mysqlType = MySQLTypes.DATETIME)
	private Date log_date;

	@MySQLAnnotation(fieldName = "change_Date", mysqlType = MySQLTypes.DATETIME, nullable = true)
	private Date change_date;

	@MySQLAnnotation(fieldName = "satisfaction", mysqlType = MySQLTypes.INT)
	private Integer satisfaction;

	@MySQLAnnotation(fieldName = "nikoniko_comment", mysqlType = MySQLTypes.TEXT, nullable = true)
	private String comment;

	@MySQLAnnotation(fieldName = "isanonymous", mysqlType = MySQLTypes.TINYINT)
	private Boolean isAnonymous;

	@MySQLAnnotation(fieldName = "id_User",mysqlType = MySQLTypes.DATABASE_ITEM, nullable = true)
	private User user;

	@MySQLAnnotation(fieldName = "id_Project",mysqlType = MySQLTypes.DATABASE_ITEM, nullable = true)
	private Project project;

	/**
	 * @return the log_date
	 */
	public Date getLog_date() {
		if (this.log_date == null) {
			this.log_date = new Date();
		}
		return log_date;
	}

	/**
	 * @param log_date
	 *            the log_date to set
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
	 * @param change_date
	 *            the change_date to set
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
	 * @param satisfaction
	 *            the satisfaction to set
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
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the isAnonymous
	 */
	public Boolean getIsAnonymous() {
		if (this.isAnonymous == null) {
			this.isAnonymous = true;
		}
		return isAnonymous;
	}

	/**
	 * @param isAnonymous
	 *            the isAnonymous to set
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
	 * @param user
	 *            the user to set
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
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	public NikoNiko(User user, Project project, int satisfaction) {
		this();
		this.user = user;
		this.project = project;
		this.setSatisfaction(satisfaction);
		this.log_date = new Date();
		this.user.getNikoNikos().add(this);
		this.project.getNikoNikos().add(this);
	}

	public NikoNiko(User user, Project project, int satisfaction, String comment) {
		this(user, project, satisfaction);
		this.comment = comment;
	}

	public NikoNiko(User user, Project project, int satisfaction, Boolean isAnonymous) {
		this(user, project, satisfaction);
		this.isAnonymous = isAnonymous;
	}

	public NikoNiko(User user, Project project, int satisfaction, String comment, Boolean isAnonymous) {
		this(user, project, satisfaction);
		this.comment = comment;
		this.isAnonymous = isAnonymous;
	}

	public NikoNiko() {
		super(NikoNiko.TABLE, NikoNiko.FIELDS);
	}

	@Override
	public String toString() {
		return "NikoNiko [log_date=" + log_date + ", change_date=" + change_date + ", satisfaction=" + satisfaction
				+ ", comment=" + comment + ", isAnonymous=" + isAnonymous + "]";
	}
}
