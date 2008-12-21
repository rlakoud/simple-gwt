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
package com.googlecode.simplegwt.command.client.ui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.simplegwt.command.client.CommandClickHandler;

/**
 * An composite {@link Widget} displaying an {@link Image} that executes a {@link Command} when
 * clicked.
 * 
 * @since 1.0
 */
public class CommandIcon extends Composite {
	/**
	 * Creates a new <code>CommandIcon</code>.
	 * 
	 * @param url
	 * @param command
	 */
	public CommandIcon(final String url, final Command command) {
		this(new Image(url), command);
	}

	/**
	 * Creates a new <code>CommandIcon</code>.
	 * 
	 * @param image
	 * @param command
	 */
	public CommandIcon(final Image image, final Command command) {
		initWidget(image);

		image.addClickHandler(new CommandClickHandler(command));

		setStylePrimaryName("simpleGwt-CommandIcon");
	}
}
