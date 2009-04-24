/*
 * Copyright 2009 Isaac Truett.
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
package com.googlecode.simplegwt.tbg.client.ui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.googlecode.simplegwt.command.client.ui.CommandToggleButton;

/**
 * A grid of toggle buttons that toggle their neighbors when clicked.
 */
public class ButtonGrid extends Composite {
	private final Grid grid;
	private CommandToggleButton[][] buttons;
	private String upText = "Off";
	private String downText = "On";

	/**
	 * Creates a new <code>ButtonGrid</code>.
	 * 
	 * @param rows
	 * @param columns
	 */
	public ButtonGrid(final int rows, final int columns) {
		super();
		grid = new Grid(rows, columns);

		initWidget(grid);

		grid.setStylePrimaryName("tbg-ButtonGrid");

		initializeGrid();
	}

	/**
	 * @return the number of columns in the grid
	 */
	public int getColumnCount() {
		return grid.getColumnCount();
	}

	/**
	 * @return the downText
	 */
	public String getDownText() {
		return downText;
	}

	/**
	 * @return the number of rows in the grid
	 */
	public int getRowCount() {
		return grid.getRowCount();
	}

	/**
	 * @return the upText
	 */
	public String getUpText() {
		return upText;
	}

	/**
	 * Resets all buttons to their up state.
	 */
	public void reset() {
		for (CommandToggleButton[] row : buttons) {
			for (CommandToggleButton button : row) {
				button.setDown(false);
			}
		}
	}

	/**
	 * Resize the grid to the specified number of rows and columns.
	 * 
	 * @param rows
	 * @param columns
	 */
	public void resize(final int rows, final int columns) {
		grid.resize(rows, columns);
		initializeGrid();
	}

	/**
	 * @param downText the downText to set
	 */
	public void setDownText(final String downText) {
		this.downText = downText;

		for (CommandToggleButton[] row : buttons) {
			for (CommandToggleButton button : row) {
				if (button.isDown()) {
					button.setText(downText);
				} else {
					button.setDown(true);
					button.setText(downText);
					button.setDown(false);
				}
			}
		}
	}

	/**
	 * @param upText the upText to set
	 */
	public void setUpText(final String upText) {
		this.upText = upText;

		for (CommandToggleButton[] row : buttons) {
			for (CommandToggleButton button : row) {
				if (button.isDown()) {
					button.setDown(false);
					button.setText(upText);
					button.setDown(true);
				} else {
					button.setText(upText);
				}
			}
		}
	}

	private void initializeGrid() {
		if (buttons == null) {
			buttons = new CommandToggleButton[getRowCount()][getColumnCount()];
		} else if (getRowCount() > buttons.length || getColumnCount() > buttons[0].length) {
			final CommandToggleButton[][] oldButtons = buttons;
			buttons = new CommandToggleButton[Math.max(getRowCount(), oldButtons.length)][Math.max(
			        getColumnCount(), oldButtons[0].length)];

			for (int a = 0; a < oldButtons.length; a++) {
				System.arraycopy(oldButtons[a], 0, buttons[a], 0, oldButtons[a].length);
			}
		}

		for (int r = 0; r < getRowCount(); r++) {
			for (int c = 0; c < getColumnCount(); c++) {
				if (buttons[r][c] == null) {
					final int row = r;
					final int col = c;

					final Command toggleCommand = new Command() {
						public void execute() {
							if (row > 0) {
								toggle(buttons[row - 1][col]);
							}

							if (row < grid.getRowCount() - 1) {
								toggle(buttons[row + 1][col]);
							}

							if (col > 0) {
								toggle(buttons[row][col - 1]);
							}

							if (col < grid.getColumnCount() - 1) {
								toggle(buttons[row][col + 1]);
							}
						}

						private void toggle(final CommandToggleButton button) {
							button.setDown(!button.isDown());
						}
					};

					final CommandToggleButton button = new CommandToggleButton(upText,
					        toggleCommand, downText, toggleCommand);

					buttons[r][c] = button;
				}

				grid.setWidget(r, c, buttons[r][c]);
			}
		}
	}
}
