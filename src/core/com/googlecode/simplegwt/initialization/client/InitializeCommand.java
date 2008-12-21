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
package com.googlecode.simplegwt.initialization.client;

import com.google.gwt.user.client.Command;

/**
 * Initializes an {@link Initializable}.
 * 
 * @since 1.0
 */
public class InitializeCommand implements Command {
	private final Initializable initable;

	/**
	 * Creates a new <code>InitializeCommand</code>.
	 * 
	 * @param initable the thing to initialize
	 */
	public InitializeCommand(final Initializable initable) {
		super();
		this.initable = initable;
	}

	/**
	 * @see com.google.gwt.user.client.Command#execute()
	 */
	public void execute() {
		initable.initialize();
	}
}
