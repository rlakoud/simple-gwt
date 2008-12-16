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
package com.googlecode.simplegwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.simplegwt.client.ui.CommandButton;
import com.googlecode.simplegwt.client.ui.CommandLabel;
import com.googlecode.simplegwt.client.ui.ContextualPopup;
import com.googlecode.simplegwt.client.ui.HoverHandler;
import com.googlecode.simplegwt.client.ui.SimpleTooltip;
import com.googlecode.simplegwt.client.ui.initialization.InitializationEvent;
import com.googlecode.simplegwt.client.ui.initialization.InitializationEventHandler;

/**
 * A demo of several simple <code>Widgets</code>, <code>HoverHandler</code>, and the initialization
 * API.
 */
public class SimpleWidgetsDemo implements EntryPoint {
	/**
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
		final Command helloWorldCommand = new Command() {
			public void execute() {
				Window.alert("Hello World!");
			}
		};

		final CommandButton helloButton = new CommandButton("Hello", helloWorldCommand);
		final CommandLabel helloLabel = new CommandLabel("Hello", helloWorldCommand);
		final ContextualPopup<String> tooltip = new SimpleTooltip("Click to say hi");

		new HoverHandler().registerOn(helloLabel);

		ContextualPopup.registerContextualPopup(helloButton, tooltip);
		ContextualPopup.registerContextualPopup(helloLabel, tooltip);

		RootPanel.get().add(helloButton);
		RootPanel.get().add(helloLabel);

		tooltip.addIntializationEventHandler(new InitializationEventHandler() {
			public void onInitialize(InitializationEvent event) {
				RootPanel.get().add(new Label("The contextual popup was initialized!"));
			}
		});
	}
}
