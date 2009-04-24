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
package com.googlecode.simplegwt.tbg.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.simplegwt.command.client.ui.CommandLabel;
import com.googlecode.simplegwt.tbg.client.ui.ButtonGrid;
import com.googlecode.simplegwt.tbg.client.ui.GridControls;
import com.googlecode.simplegwt.tbg.client.ui.LoginWidget;

/**
 * EntryPoint for the SimpleGWT ToggleButtonGame (TBG).
 */
public class TbgEntryPoint implements EntryPoint {
	private static final int DEFAULT_GRID_HEIGHT = 5;
	private static final int DEFAULT_GRID_WIDTH = 5;

	/**
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		final ButtonGrid buttonGrid = new ButtonGrid(DEFAULT_GRID_HEIGHT, DEFAULT_GRID_WIDTH);
		final GridControls gridControls = new GridControls(buttonGrid);

		final DialogBox dialog = new DialogBox(false, false);
		dialog.setWidget(new Label("Click a button to toggle it on/off. "
		        + "Adjacent buttons will also reverse their state.", true));
		dialog.setText("Help - ToggleButtonGame");
		dialog.addStyleName("tbg-help-dialog");
		final FlowPanel gridHeaderBar = new FlowPanel();
		final Label helpLabel = new CommandLabel("Help", new Command() {
			boolean shownOnce = false;

			public void execute() {
				if (dialog.isShowing()) {
					dialog.hide();
				} else {
					if (shownOnce) {
						dialog.show();
					} else {
						dialog.center();
						shownOnce = true;
					}
				}
			}
		});
		helpLabel.setStylePrimaryName("tbg-help");
		gridHeaderBar.add(new LoginWidget(gridControls));
		gridHeaderBar.add(helpLabel);

		final DecoratorPanel decoration = new DecoratorPanel();
		final FlowPanel wrapper = new FlowPanel();

		wrapper.add(gridHeaderBar);
		wrapper.add(gridControls);
		wrapper.add(buttonGrid);

		decoration.add(wrapper);

		RootPanel.get("gwt").add(decoration);

		Window.addResizeHandler(new ResizeHandler() {
			public void onResize(ResizeEvent event) {
				resize(event.getHeight(), event.getWidth());
			}
		});

		resize(Window.getClientHeight(), Window.getClientWidth());
	}

	/**
	 * Resize the root panel to the new page size.
	 * 
	 * @param height
	 * @param width
	 */
	private void resize(final int height, final int width) {
		final RootPanel page = RootPanel.get("page");
		page.setHeight(height + "px");
		page.setWidth(width + "px");
	}
}
