/*
 *	Copyright 2008 Isaac Truett.
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package com.googlecode.simplegwt.command.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.ToggleButton;

/**
 * A {@link ToggleButton} that executes {@link Command}s when clicked on and off.<br />
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.simpleGwt-CommandToggleButton { }</li>
 * </ul>
 * 
 * @since 1.0
 */
public class CommandToggleButton extends ToggleButton {
	private class CommandToggleButtonClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			if (isDown()) {
				upCommand.execute();
			} else {
				downCommand.execute();
			}
		}
	}

	private final Command upCommand;
	private final Command downCommand;

	/**
	 * Creates a new <code>CommandToggleButton</code>.
	 * 
	 * @param upText
	 * @param upCommand
	 * @param downText
	 * @param downCommand
	 */
	public CommandToggleButton(final String upText, final Command upCommand, final String downText,
	        final Command downCommand) {
		super(upText, downText);
		this.upCommand = upCommand;
		this.downCommand = downCommand;
		addClickHandler(new CommandToggleButtonClickHandler());

		setStylePrimaryName("simpleGwt-CommandToggleButton");
	}
}