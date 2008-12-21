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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ImageBundle;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.googlecode.simplegwt.command.client.ui.CommandIcon;

/**
 * An enhanced {@link SuggestBox} that adds a {@link CommandIcon} which allows the user to display
 * the suggestion list without typing anything.
 * 
 * @param <T>
 */
public class ComboBox<T> extends Composite implements HasValue<T> {
	/**
	 * An {@link ImageBundle} that defines images used by a <code>ComboBox</code>.
	 */
	public interface ComboBoxImageBundle extends ImageBundle {
		/**
		 * The icon for opening the suggestion list.
		 * 
		 * @return the image
		 */
		@Resource("openIcon.gif")
		AbstractImagePrototype getOpenIcon();
	}

	private static Image getDefaultOpenIcon() {
		return ((ComboBoxImageBundle) GWT.create(ComboBoxImageBundle.class)).getOpenIcon()
		        .createImage();
	}

	private final SuggestBox suggestBox;

	private final CommandIcon openIcon;

	private final ComboBoxOracle<T> oracle;

	/**
	 * Creates a new <code>ComboBox</code>.
	 * 
	 * @param openIconImage
	 * @param oracle
	 */
	public ComboBox(final Image openIconImage, final ComboBoxOracle<T> oracle) {
		this.oracle = oracle;
		suggestBox = new SuggestBox(oracle.getSuggestOracle());
		openIcon = new CommandIcon(openIconImage, new ShowSuggestionsCommand(suggestBox));

		final Panel wrapper = new HorizontalPanel();

		wrapper.add(suggestBox);
		wrapper.add(openIcon);

		initWidget(wrapper);

		setStylePrimaryName("simpleGwt-ComboBox");
	}

	/**
	 * Creates a new <code>ComboBox</code>.
	 * 
	 * @param oracle
	 */
	public ComboBox(final ComboBoxOracle<T> oracle) {
		this(getDefaultOpenIcon(), oracle);
	}

	/**
	 * @see com.google.gwt.user.client.ui.HasValue#getValue()
	 */
	public T getValue() {
		return oracle.getValue(suggestBox.getText());
	}

	/**
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object)
	 */
	public void setValue(final T value) {
		setValue(value, false);
	}

	/**
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object, boolean)
	 */
	public void setValue(final T value, final boolean fireEvents) {
		final T oldValue = getValue();

		suggestBox.setText(oracle.getDisplayText(value));

		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
		}
	}

	/**
	 * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
	 */
	public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> handler) {
		return getHandlers().addHandler(ValueChangeEvent.getType(), handler);
	}
}
