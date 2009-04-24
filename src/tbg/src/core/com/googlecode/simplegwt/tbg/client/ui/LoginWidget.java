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
package com.googlecode.simplegwt.tbg.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.simplegwt.tbg.common.AsyncResponse;
import com.googlecode.simplegwt.tbg.common.Preferences;
import com.googlecode.simplegwt.tbg.common.UserPreferenceService;
import com.googlecode.simplegwt.tbg.common.UserPreferenceServiceAsync;

/**
 * Widget for logging in via Google account.
 */
public class LoginWidget extends Composite {
	private SimplePanel wrapper = new SimplePanel();

	/**
	 * Creates a new <code>LoginWidget</code>.
	 * 
	 * @param gridControls
	 */
	public LoginWidget(final GridControls gridControls) {
		initWidget(wrapper);

		final UserPreferenceServiceAsync service = GWT.create(UserPreferenceService.class);

		service.getUserPreferences(new AsyncCallback<AsyncResponse<Preferences>>() {
			public void onFailure(Throwable caught) {
				GWT.log("An error occurred while retrieving user preferences", caught);
				Window
				        .alert("An unexpected error was encountered while retrieving user preferences.");
			}

			public void onSuccess(final AsyncResponse<Preferences> response) {
				if (response.isSuccess()) {
					final Preferences data = response.getData();
					gridControls.setPreferences(data);
					wrapper.setWidget(new Label("Welcome, " + data.getUserName()));
				} else {
					wrapper.setWidget(new HTML(response.getMessage()));
				}
			}
		});

		setStylePrimaryName("tbg-LoginWidget");
	}
}
