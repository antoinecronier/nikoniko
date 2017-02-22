package com.tactfactory.nikoniko.models.modelbase;

public abstract class DatabaseItem {
	public static String TABLE;
	public static String[] FIELDS;

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
		DatabaseItem.TABLE = table;
		DatabaseItem.FIELDS = fields;
	}

	public DatabaseItem() {
	}
}
