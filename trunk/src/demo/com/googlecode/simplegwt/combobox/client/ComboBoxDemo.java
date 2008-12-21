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
package com.googlecode.simplegwt.combobox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.simplegwt.combobox.client.ui.ComboBox;
import com.googlecode.simplegwt.combobox.client.ui.StringComboBoxOracle;

/**
 * Demonstration of a {@link ComboBox}.
 */
public class ComboBoxDemo implements EntryPoint {
	/**
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		final StringComboBoxOracle oracle = new StringComboBoxOracle();
		final ComboBox<String> box = new ComboBox<String>(oracle);

		oracle.addItem("Avocado");
		oracle.addItem("Olive");
		oracle.addItem("Tomato");

		RootPanel.get().add(new Label("Enter your favorite fruit or select one from the list: "));
		RootPanel.get().add(box);
	}
}
