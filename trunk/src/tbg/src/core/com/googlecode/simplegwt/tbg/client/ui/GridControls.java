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
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.simplegwt.command.client.ui.CommandButton;
import com.googlecode.simplegwt.contextualpopup.client.ui.SimpleTooltip;
import com.googlecode.simplegwt.tbg.common.AsyncResponse;
import com.googlecode.simplegwt.tbg.common.Preferences;
import com.googlecode.simplegwt.tbg.common.UserPreferenceService;
import com.googlecode.simplegwt.tbg.common.UserPreferenceServiceAsync;
import com.googlecode.simplegwt.tbg.common.AsyncResponse.VoidData;

/**
 * A set of controls for configuring the ButtonGrid.
 */
public class GridControls extends Composite {
	private TextBox rowsField;
	private TextBox colsField;
	private TextBox upTextField;
	private TextBox downTextField;
	private boolean loggedIn;
	private Command savePrefsCommand = new Command() {
		public void execute() {
			final Preferences prefs = new Preferences();

			prefs.setColumns(Integer.parseInt(colsField.getText()));
			prefs.setDownText(downTextField.getText());
			prefs.setRows(Integer.parseInt(rowsField.getText()));
			prefs.setUpText(upTextField.getText());

			final UserPreferenceServiceAsync service = GWT.create(UserPreferenceService.class);

			service.saveUserPreferences(prefs, new AsyncCallback<AsyncResponse<VoidData>>() {
				public void onFailure(Throwable caught) {
					GWT.log("An error occurred while saving user preferences", caught);
					Window
					        .alert("An unexpected error was encountered while saving user preferences.");
				}

				public void onSuccess(AsyncResponse<VoidData> result) {
					// Do nothing.
				}
			});
		}
	};

	/**
	 * Creates a new <code>GridControls</code>.
	 * 
	 * @param buttonGrid
	 */
	public GridControls(final ButtonGrid buttonGrid) {
		super();

		final FlowPanel wrapper = new FlowPanel();

		wrapper.setStylePrimaryName("tbg-GridControls");

		final Label rowsLabel = new Label("Rows");
		final Label colsLabel = new Label("Columns");
		final Label upTextLabel = new Label("Up text");
		final Label downTextLabel = new Label("Down text");

		new SimpleTooltip("The number of rows in the grid").registerOn(rowsLabel);
		new SimpleTooltip("The number of columns in the grid").registerOn(colsLabel);
		new SimpleTooltip("The text displayed by a button in the up, or off, state")
		        .registerOn(upTextLabel);
		new SimpleTooltip("The text displayed by a button in the down, or on, state")
		        .registerOn(downTextLabel);

		rowsField = new TextBox();
		colsField = new TextBox();
		upTextField = new TextBox();
		downTextField = new TextBox();

		rowsField.setText(String.valueOf(buttonGrid.getRowCount()));
		colsField.setText(String.valueOf(buttonGrid.getColumnCount()));
		upTextField.setText(buttonGrid.getUpText());
		downTextField.setText(buttonGrid.getDownText());

		final ValueChangeHandler<String> resizeHandler = new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				final int rows = Integer.parseInt(rowsField.getText());
				final int cols = Integer.parseInt(colsField.getText());
				buttonGrid.resize(rows, cols);
				maybeSave();
			}
		};
		rowsField.addValueChangeHandler(resizeHandler);
		colsField.addValueChangeHandler(resizeHandler);
		upTextField.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(final ValueChangeEvent<String> event) {
				buttonGrid.setUpText(event.getValue());
				maybeSave();
			}
		});
		downTextField.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(final ValueChangeEvent<String> event) {
				buttonGrid.setDownText(event.getValue());
				maybeSave();
			}
		});

		final CommandButton resetButton = new CommandButton("Reset Grid", new Command() {
			public void execute() {
				buttonGrid.reset();
				maybeSave();
			}
		});

		new SimpleTooltip("Return all of the buttons to their up, or off, state")
		        .registerOn(resetButton);

		wrapper.add(rowsLabel);
		wrapper.add(rowsField);
		wrapper.add(colsLabel);
		wrapper.add(colsField);
		wrapper.add(upTextLabel);
		wrapper.add(upTextField);
		wrapper.add(downTextLabel);
		wrapper.add(downTextField);
		wrapper.add(resetButton);

		initWidget(wrapper);
	}

	/**
	 * Sets the control fields based on user preferences.
	 * 
	 * @param prefs
	 */
	public void setPreferences(final Preferences prefs) {
		rowsField.setValue(String.valueOf(prefs.getRows()), true);
		colsField.setValue(String.valueOf(prefs.getColumns()), true);
		upTextField.setValue(prefs.getUpText(), true);
		downTextField.setValue(prefs.getDownText(), true);

		if (prefs.getUserName() != null) {
			loggedIn = true;
		}
	}

	/**
	 * If the user is logged in, save they current preferences.
	 */
	protected void maybeSave() {
		if (loggedIn) {
			DeferredCommand.addCommand(savePrefsCommand);
		}
	}
}
