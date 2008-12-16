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
package com.googlecode.simplegwt.client.ui;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;

/**
 * A <code>Button</code> that executes a <code>Command</code> when clicked.
 */
public class CommandButton extends Button {
	/**
	 * Creates a new <code>CommandButton</code>.
	 * 
	 * @param text
	 * @param cmd
	 */
	public CommandButton(final String text, final Command cmd) {
		super(text);
		this.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(final MouseUpEvent event) {
				cmd.execute();
			}
		});
	}
}
