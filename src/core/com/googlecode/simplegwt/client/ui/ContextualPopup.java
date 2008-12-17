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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.googlecode.simplegwt.client.ui.initialization.InitializableComposite;

/**
 * A context-sensitive popup that appears when the user mouses over a {@link Widget}. The popup
 * disappears if the user's mouse leaves the popup or if the user clicks on the {@link Widget}.
 * 
 * @param <T> the type of value contained in this popup
 * @since 1.0
 */
public abstract class ContextualPopup<T> extends InitializableComposite implements HasValue<T>,
        HasMouseOverHandlers, HasMouseOutHandlers {
	/**
	 * Handles hiding the popup when the mouse leaves.
	 */
	private class PopupMouseHandler implements MouseOverHandler, MouseOutHandler {
		/**
		 * @see com.google.gwt.event.dom.client.MouseOutHandler#onMouseOut(com.google.gwt.event.dom.client.MouseOutEvent)
		 */
		public void onMouseOut(MouseOutEvent event) {
			hide();
		}

		/**
		 * @see com.google.gwt.event.dom.client.MouseOverHandler#onMouseOver(com.google.gwt.event.dom.client.MouseOverEvent)
		 */
		public void onMouseOver(MouseOverEvent event) {
			hideTimer.cancel();
		}
	}

	/**
	 * Handles showing the popup when the user mouses over the contextual <code>Widget</code> and
	 * hiding the popup if the user clicks on the <code>Widget</code>.
	 */
	private static final class WidgetContextMouseHandler implements MouseOverHandler,
	        MouseOutHandler, MouseMoveHandler, ClickHandler {
		private static final int DELAY = 250;

		private int currentX;
		private int currentY;

		private final ContextualPopup<?> contextualPopup;

		private final Timer timer = new Timer() {
			@Override
			public void run() {
				contextualPopup.show(currentX, currentY);
				contextualPopup.hideTimer.cancel();
			}
		};

		public WidgetContextMouseHandler(final ContextualPopup<?> contextualPopup) {
			super();
			this.contextualPopup = contextualPopup;
		}

		public void onClick(final ClickEvent event) {
			contextualPopup.hide();
		}

		public void onMouseMove(final MouseMoveEvent event) {
			currentX = event.getClientX();
			currentY = event.getClientY();
		}

		public void onMouseOut(final MouseOutEvent event) {
			timer.cancel();
			contextualPopup.hide();
		}

		public void onMouseOver(final MouseOverEvent event) {
			timer.schedule(DELAY);
		}
	}

	private static final int HIDE_DELAY = 500;

	private T value;

	private final Timer hideTimer = new Timer() {
		@Override
		public void run() {
			getPopupPanel().hide();
		}
	};

	private PopupPanel popupPanel;
	private FocusPanel focusPanel;

	/**
	 * Creates a new <code>ContextualPopup</code>.
	 * 
	 * @param value the value contained in this popup
	 */
	public ContextualPopup(final T value) {
		super();
		assert value != null : "Cannot create a ContextualPopup with a null value";
		setValue(value);
	}

	/**
	 * @see com.google.gwt.event.dom.client.HasMouseOutHandlers#addMouseOutHandler(com.google.gwt.event.dom.client.MouseOutHandler)
	 */
	public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
		return getHandlers().addHandler(MouseOutEvent.getType(), handler);
	}

	/**
	 * @see com.google.gwt.event.dom.client.HasMouseOverHandlers#addMouseOverHandler(com.google.gwt.event.dom.client.MouseOverHandler)
	 */
	public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
		return getHandlers().addHandler(MouseOverEvent.getType(), handler);
	}

	/**
	 * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
	 */
	public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> handler) {
		return getHandlers().addHandler(ValueChangeEvent.getType(), handler);
	}

	/**
	 * @see com.google.gwt.user.client.ui.HasValue#getValue()
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Registers this <code>ContextualPopup</code> as a {@link MouseOverHandler}, a
	 * {@link MouseOutHandler}, and a {@link MouseMoveHandler} on the specified {@link Widget}.
	 * 
	 * @param <H> artificial type comprising the required event hooks
	 * @param widget the <code>Widget</code> context for the popup
	 */
	public <H extends HasMouseOutHandlers & HasMouseOverHandlers & HasMouseMoveHandlers> void registerOn(
	        final H widget) {
		final WidgetContextMouseHandler handler = new WidgetContextMouseHandler(
		        ContextualPopup.this);
		widget.addMouseOverHandler(handler);
		widget.addMouseOutHandler(handler);
		widget.addMouseMoveHandler(handler);
	}

	/**
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object)
	 */
	public void setValue(final T value) {
		this.value = value;
	}

	/**
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object, boolean)
	 */
	public void setValue(final T value, boolean fireEvents) {
		final T oldValue = getValue();

		setValue(value);

		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
		}
	}

	/**
	 * Called when the popup is initialized. Subclasses can add content to the
	 * <code>FocusPanel</code>.
	 * 
	 * @param panel
	 */
	protected abstract void buildPopup(final FocusPanel panel);

	/**
	 * Creates and configures a new {@link PopupPanel}. Override to change {@link PopupPanel}
	 * implementation, disable animation, etc.
	 * 
	 * @return the contextual {@link PopupPanel}
	 */
	protected PopupPanel createPopupPanel() {
		final DecoratedPopupPanel newPopupPanel = new DecoratedPopupPanel();
		newPopupPanel.setAnimationEnabled(true);
		return newPopupPanel;
	}

	/**
	 * @see com.googlecode.simplegwt.client.ui.initialization.InitializableComposite#onInitialize()
	 */
	@Override
	protected void onInitialize() {
		popupPanel = createPopupPanel();
		focusPanel = new FocusPanel();
		popupPanel.add(focusPanel);
		buildPopup(focusPanel);

		focusPanel.addMouseOverHandler(new PopupMouseHandler());
		focusPanel.addMouseOutHandler(new PopupMouseHandler());
	}

	/**
	 * Show the popup.
	 * 
	 * @param y the y coordinate relative to which the popup is shown
	 * @param x the x coordinate relative to which the popup is shown
	 */
	protected void show(final int x, final int y) {
		getPopupPanel().setPopupPositionAndShow(new PositionCallback() {
			public void setPosition(final int offsetWidth, final int offsetHeight) {
				getPopupPanel().setPopupPosition(x + 5, Math.max(y - offsetHeight, 0));
			}
		});
	}

	private PopupPanel getPopupPanel() {
		if (!isInitialized()) {
			initialize();
		}

		return popupPanel;
	}

	private void hide() {
		hideTimer.schedule(HIDE_DELAY);
	}
}
