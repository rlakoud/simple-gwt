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
package com.googlecode.simplegwt.client.ui;

import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * A simple <code>ContextualPopup</code> that displays a <code>String</code>.
 */
public class SimpleTooltip extends ContextualPopup<String> {
	/**
	 * Creates a new <code>SimpleTooltip</code>.
	 * 
	 * @param text
	 */
	public SimpleTooltip(final String text) {
		super(text);
	}

	/**
	 * @see com.googlecode.simplegwt.client.ui.ContextualPopup#buildPopup(com.google.gwt.user.client.ui.FocusPanel)
	 */
	@Override
	protected void buildPopup(final FocusPanel focusPanel) {
		focusPanel.add(new Label(getValue()));
	}
}
