/*
 * Copyright 2009 Isaac Truett.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.simplegwt.tbg.server.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;
import com.googlecode.simplegwt.tbg.common.Preferences;

/**
 * Preferences data object that is persisted to the data store. Use {@link Preferences} for data
 * transfer to/from the client.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class PersistentPreferences {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String id;

	@Persistent
	private User user;

	@Persistent
	private int rows;

	@Persistent
	private int columns;

	@Persistent
	private String upText;

	@Persistent
	private String downText;

	/**
	 * Creates a new <code>PersistentPreferences</code>.
	 * 
	 * @param user
	 */
	public PersistentPreferences(final User user) {
		this.user = user;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return the downText
	 */
	public String getDownText() {
		return downText;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the upText
	 */
	public String getUpText() {
		return upText;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * @param downText the downText to set
	 */
	public void setDownText(String downText) {
		this.downText = downText;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @param upText the upText to set
	 */
	public void setUpText(String upText) {
		this.upText = upText;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return rows + "x" + columns + " (" + upText + "/" + downText + ")";
	}
}
