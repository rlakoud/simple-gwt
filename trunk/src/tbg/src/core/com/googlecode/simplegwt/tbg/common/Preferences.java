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
package com.googlecode.simplegwt.tbg.common;

import java.io.Serializable;

/**
 * DTO for user preferences.
 */
@SuppressWarnings("serial")
public class Preferences implements Serializable {
	private String userName;
	private int rows = 5;
	private int columns = 5;
	private String upText = "off";
	private String downText = "on";

	/**
	 * Creates a new <code>Preferences</code>.
	 */
	public Preferences() {
		super();
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(final int columns) {
		this.columns = columns;
	}

	/**
	 * @param downText the downText to set
	 */
	public void setDownText(final String downText) {
		this.downText = downText;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(final int rows) {
		this.rows = rows;
	}

	/**
	 * @param upText the upText to set
	 */
	public void setUpText(final String upText) {
		this.upText = upText;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}
}
