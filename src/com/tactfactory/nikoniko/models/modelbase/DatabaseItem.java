package com.tactfactory.nikoniko.models.modelbase;

public abstract class DatabaseItem {

	public String table;
	public String[] fields;

	private long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public DatabaseItem(String table, String[] fields) {
		this.table = table;
		this.fields = fields;
	}

	public DatabaseItem() {
	}
}
