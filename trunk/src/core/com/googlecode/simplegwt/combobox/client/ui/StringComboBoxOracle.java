/*
 * Copyright 2008 Isaac Truett.
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
package com.googlecode.simplegwt.combobox.client.ui;

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle;

/**
 * A {@link ComboBox} that displays {@link String}s.
 */
public class StringComboBoxOracle implements ComboBoxOracle<String> {
	private MultiWordSuggestOracle suggestOracle;

	/**
	 * Creates a new <code>StringComboBoxOracle</code>.
	 */
	public StringComboBoxOracle() {
		this.suggestOracle = new MultiWordSuggestOracle();
	}

	/**
	 * @see com.googlecode.simplegwt.combobox.client.ui.ComboBoxOracle#getDisplayText(java.lang.Object)
	 */
	public String getDisplayText(final String value) {
		return value;
	}

	/**
	 * @see com.googlecode.simplegwt.combobox.client.ui.ComboBoxOracle#getSuggestOracle()
	 */
	public SuggestOracle getSuggestOracle() {
		return suggestOracle;
	}

	/**
	 * @see com.googlecode.simplegwt.combobox.client.ui.ComboBoxOracle#getValue(java.lang.String)
	 */
	public String getValue(final String displayText) {
		return displayText;
	}

	/**
	 * Adds an item to be suggested.
	 * 
	 * @param item
	 */
	public void addItem(final String item) {
		suggestOracle.add(item);
	}
}
