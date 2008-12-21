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

import com.google.gwt.user.client.ui.SuggestOracle;

/**
 * Provides suggestions for a {@link ComboBox} and converts between strongly typed values and their
 * corresponding display text.
 * 
 * @param <T>
 */
public interface ComboBoxOracle<T> {
	/**
	 * Returns the {@link SuggestOracle} used for obtaining suggestions.
	 * 
	 * @return the SuggestOracle
	 */
	public SuggestOracle getSuggestOracle();

	/**
	 * Returns the strongly typed value for the specified display text.
	 * 
	 * @param displayText
	 * @return the strongly typed value
	 */
	public T getValue(String displayText);

	/**
	 * Returns the display text for the specified value.
	 * 
	 * @param value
	 * @return the display text
	 */
	public String getDisplayText(T value);
}
