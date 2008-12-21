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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

/**
 * A {@link ComboBox} that displays {@link String}s.
 */
public class StringComboBoxOracle implements ComboBoxOracle<String> {
	/**
	 * A {@link Suggestion} that is simply a {@link String}.
	 */
	public class StringSuggestion implements Suggestion {
		private final String string;

		/**
		 * Creates a new <code>StringSuggestion</code>.
		 * 
		 * @param string
		 */
		public StringSuggestion(final String string) {
			super();
			this.string = string;
		}

		/**
		 * @see com.google.gwt.user.client.ui.SuggestOracle.Suggestion#getDisplayString()
		 */
		public String getDisplayString() {
			return string;
		}

		/**
		 * @see com.google.gwt.user.client.ui.SuggestOracle.Suggestion#getReplacementString()
		 */
		public String getReplacementString() {
			return string;
		}
	}

	/**
	 * A {@link SuggestOracle} that suggests {@link String}s.
	 */
	public class StringSuggestOracle extends SuggestOracle {
		private TreeSet<String> items = new TreeSet<String>();

		/**
		 * @see com.google.gwt.user.client.ui.SuggestOracle#requestSuggestions(com.google.gwt.user.client.ui.SuggestOracle.Request,
		 *      com.google.gwt.user.client.ui.SuggestOracle.Callback)
		 */
		@Override
		public void requestSuggestions(final Request request, final Callback callback) {
			final List<Suggestion> suggestions = getSuggestions(request);

			final Response response = new Response(suggestions);
			callback.onSuggestionsReady(request, response);
		}

		private List<Suggestion> getSuggestions(final Request request) {
			final List<Suggestion> suggestions = new ArrayList<Suggestion>();
			final Iterator<String> i = items.iterator();
			final String lowerCaseQuery = request.getQuery().toLowerCase();

			while (suggestions.size() < request.getLimit() && i.hasNext()) {
				String next = i.next();

				if (next.toLowerCase().startsWith(lowerCaseQuery)) {
					suggestions.add(new StringSuggestion(next));
				}
			}

			return suggestions;
		}

		/**
		 * @see com.google.gwt.user.client.ui.SuggestOracle#requestDefaultSuggestions(com.google.gwt.user.client.ui.SuggestOracle.Request,
		 *      com.google.gwt.user.client.ui.SuggestOracle.Callback)
		 */
		@Override
		public void requestDefaultSuggestions(final Request request, final Callback callback) {
			request.setQuery("");
			final List<Suggestion> suggestions = getSuggestions(request);

			final Response response = new Response(suggestions);
			callback.onSuggestionsReady(request, response);
		}

		/**
		 * Adds the specified {@link String} to the oracle.
		 * 
		 * @param item
		 */
		public void add(final String item) {
			assert item != null : "Item cannot be null";
			items.add(item);
		}
	}

	private StringSuggestOracle suggestOracle;

	/**
	 * Creates a new <code>StringComboBoxOracle</code>.
	 */
	public StringComboBoxOracle() {
		this.suggestOracle = new StringSuggestOracle();
	}

	/**
	 * @see com.googlecode.simplegwt.combobox.client.ui.ComboBoxOracle#getDisplayText(java.lang.Object)
	 */
	public String getDisplayText(final String value) {
		return value;
	}

	/**
	 * @see com.googlecode.simplegwt.combobox.client.ui.ComboBoxOracle#getSuggestOracle()
	 */
	public SuggestOracle getSuggestOracle() {
		return suggestOracle;
	}

	/**
	 * @see com.googlecode.simplegwt.combobox.client.ui.ComboBoxOracle#getValue(java.lang.String)
	 */
	public String getValue(final String displayText) {
		return displayText;
	}

	/**
	 * Adds an item to be suggested.
	 * 
	 * @param item
	 */
	public void addItem(final String item) {
		suggestOracle.add(item);
	}
}
