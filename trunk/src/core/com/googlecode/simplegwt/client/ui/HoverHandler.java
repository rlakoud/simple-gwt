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

import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite handler that toggles a style name suffix when the mouse enters and leaves the target
 * {@link Widget}. The default style name suffix of "hover" can be overridden using the
 * {@link #HoverHandler(String)} constructor.
 * 
 * @since 1.0
 */
public class HoverHandler implements MouseOverHandler, MouseOutHandler {
	private final String styleSuffix;

	/**
	 * Creates a new <code>HoverHandler</code> with the specified style suffix.
	 * 
	 * @param styleSuffix the style name suffix to be applied to the <code>Widget</code> on mouse
	 *            over
	 */
	public HoverHandler(final String styleSuffix) {
		super();
		this.styleSuffix = styleSuffix;
	}

	/**
	 * Creates a new <code>HoverHandler</code>.
	 */
	public HoverHandler() {
		this("hover");
	}

	/**
	 * @see com.google.gwt.event.dom.client.MouseOverHandler#onMouseOver(com.google.gwt.event.dom.client.MouseOverEvent)
	 */
	public void onMouseOver(final MouseOverEvent event) {
		getUIObjectFromEvent(event).addStyleDependentName(styleSuffix);
	}

	/**
	 * @see com.google.gwt.event.dom.client.MouseOutHandler#onMouseOut(com.google.gwt.event.dom.client.MouseOutEvent)
	 */
	public void onMouseOut(final MouseOutEvent event) {
		getUIObjectFromEvent(event).removeStyleDependentName(styleSuffix);
	}

	/**
	 * Casts the event source to a <code>UIObject</code>.
	 * 
	 * @param event
	 * @return
	 */
	private UIObject getUIObjectFromEvent(final GwtEvent<?> event) {
		final Object source = event.getSource();
		assert (source instanceof Widget) : "Source must be a Widget";
		final UIObject uiObject = (Widget) source;
		return uiObject;
	}

	/**
	 * Registers this <code>HoverHandler</code> as a {@link MouseOverHandler} and a
	 * {@link MouseOutHandler} on the specified {@link Widget}.
	 * 
	 * @param <H> artificial type comprising the required event hooks
	 * @param widget the {@link Widget} context for the popup
	 */
	public <H extends HasMouseOverHandlers & HasMouseOutHandlers> void registerOn(H widget) {
		widget.addMouseOverHandler(this);
		widget.addMouseOutHandler(this);
	}
}
