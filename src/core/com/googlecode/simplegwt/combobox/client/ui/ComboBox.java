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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ImageBundle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.googlecode.simplegwt.command.client.CommandClickHandler;
import com.googlecode.simplegwt.command.client.ui.CommandIcon;
import com.googlecode.simplegwt.style.client.ui.HoverStyler;

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
		@Resource("openIcon.png")
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

		final ShowSuggestionsCommand command = new ShowSuggestionsCommand(suggestBox);

		openIcon = new CommandIcon(openIconImage, command);

		final HorizontalPanel layout = new HorizontalPanel();
		final FocusPanel iconWrapper = new FocusPanel();
		iconWrapper.setStylePrimaryName("openIconWrapper");
		iconWrapper.add(openIcon);
		iconWrapper.addClickHandler(new CommandClickHandler(command));

		KeyDownHandler keyDownHandler = new KeyDownHandler() {
			private int selectedIndex = -1;

			public void onKeyDown(KeyDownEvent event) {
				boolean navKey = false;

				switch (event.getNativeKeyCode()) {
					case KeyCodes.KEY_UP:
						selectedIndex--;
						navKey = true;
						break;
					case KeyCodes.KEY_DOWN:
						selectedIndex++;
						navKey = true;
						break;
					case KeyCodes.KEY_ESCAPE:
						suggestBox.hideSuggestions();
						selectedIndex = -1;
						break;
					case KeyCodes.KEY_ENTER:
					case KeyCodes.KEY_TAB:
						if (suggestBox.getSuggestionMenuSelectedItemIndex() < 0) {
							suggestBox.hideSuggestions();
						} else if (suggestBox.isShowingSuggestions()) {
							suggestBox.doSelectedItemAction();
						}
						break;
				}

				if (navKey) {
					if (selectedIndex > -1) {
						if (!suggestBox.isShowingSuggestions()) {
							command.execute();
						}

						suggestBox.selectItem(selectedIndex);
					} else if (selectedIndex < -1) {
						selectedIndex = -1;
					}
				}
			}
		};

		suggestBox.addKeyDownHandler(keyDownHandler);
		iconWrapper.addKeyDownHandler(keyDownHandler);

		layout.add(suggestBox);
		layout.add(iconWrapper);

		initWidget(layout);

		setStylePrimaryName("simpleGwt-ComboBox");

		new HoverStyler().registerOn(iconWrapper);
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
