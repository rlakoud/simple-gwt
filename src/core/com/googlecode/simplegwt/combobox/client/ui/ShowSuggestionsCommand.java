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
package com.googlecode.simplegwt.combobox.client.ui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.SuggestBox;

/**
 * A {@link Command} that, when executed, hides a {@link SuggestBox}'s list of suggestions.
 */
public class ShowSuggestionsCommand implements Command {
	private final SuggestBox suggestBox;

	/**
	 * Creates a new <code>ShowSuggestionsCommand</code>.
	 * 
	 * @param suggestBox
	 */
	public ShowSuggestionsCommand(final SuggestBox suggestBox) {
		super();
		this.suggestBox = suggestBox;
	}

	/**
	 * @see com.google.gwt.user.client.Command#execute()
	 */
	public void execute() {
		suggestBox.showSuggestions();
	}
}
