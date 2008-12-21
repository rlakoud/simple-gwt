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
package com.googlecode.simplegwt.contextualpopup.client.ui;

import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

/**
 * A simple {@link ContextualPopup} that displays a {@link String}.<br />
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.simpleGwt-SimpleTooltip { }</li>
 * </ul>
 * 
 * @since 1.0
 */
public class SimpleTooltip extends ContextualPopup<String> {
	private boolean isHtml;

	/**
	 * Creates a new {@link SimpleTooltip}.
	 * 
	 * @param text the text to display in the popup
	 */
	public SimpleTooltip(final String text) {
		this(text, false);
	}

	/**
	 * Creates a new {@link SimpleTooltip}.
	 * 
	 * @param text the text to display in the popup
	 * @param isHtml if <code>true</code>, treat the text as HTML
	 */
	public SimpleTooltip(final String text, final boolean isHtml) {
		super(text);
		this.isHtml = isHtml;
	}

	/**
	 * @see com.googlecode.simplegwt.client.ui.ContextualPopup#buildPopup(com.google.gwt.user.client.ui.FocusPanel)
	 */
	@Override
	protected void buildPopup(final FocusPanel focusPanel) {
		if (isHtml) {
			focusPanel.add(new HTML(getValue()));
		} else {
			focusPanel.add(new Label(getValue()));
		}

		focusPanel.setStylePrimaryName("simpleGwt-SimpleTooltip");
	}
}
