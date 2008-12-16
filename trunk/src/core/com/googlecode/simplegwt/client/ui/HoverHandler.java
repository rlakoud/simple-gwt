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
 * A composite <code>Handler</code> that toggles a style name suffix on and off when the mouse
 * enters and leaves the target <code>Widget</code>. The default style name suffix is "hover" which
 * can be overridden using the <code>HoverHandler(String)</code> constructor.
 */
public class HoverHandler implements MouseOverHandler, MouseOutHandler {
	private final String styleSuffix;

	/**
	 * Creates a new <code>HoverHandler</code> with the specified style suffix.
	 * 
	 * @param styleSuffix
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
	 * Registers this <code>HoverHandler</code> as a <code>MouseOverHandler</code> and a
	 * <code>MouseOutHandler</code> on the specified event source.
	 * 
	 * @param <S>
	 * @param eventSource
	 */
	public <S extends HasMouseOverHandlers & HasMouseOutHandlers> void registerOn(S eventSource) {
		eventSource.addMouseOverHandler(this);
		eventSource.addMouseOutHandler(this);
	}
}
