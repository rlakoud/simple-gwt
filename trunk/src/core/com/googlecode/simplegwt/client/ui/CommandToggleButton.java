package com.googlecode.simplegwt.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.ToggleButton;

/**
 * TODO JavaDoc comment for <code>CommandToggleButton</code>.<br />
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.simpleGwt-CommandToggleButton { }</li>
 * </ul>
 * 
 * @since 1.0
 */
public class CommandToggleButton extends ToggleButton {
	private class CommandToggleButtonClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			if (isDown()) {
				upCommand.execute();
			} else {
				downCommand.execute();
			}
		}
	}

	private final Command upCommand;
	private final Command downCommand;

	/**
	 * Creates a new <code>CommandToggleButton</code>.
	 * 
	 * @param upText
	 * @param upCommand
	 * @param downText
	 * @param downCommand
	 */
	public CommandToggleButton(final String upText, final Command upCommand, final String downText,
	        final Command downCommand) {
		super(upText, downText);
		this.upCommand = upCommand;
		this.downCommand = downCommand;
		addClickHandler(new CommandToggleButtonClickHandler());

		setStylePrimaryName("simpleGwt-CommandToggleButton");
	}
}