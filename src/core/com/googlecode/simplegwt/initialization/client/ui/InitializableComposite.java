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
package com.googlecode.simplegwt.initialization.client.ui;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.googlecode.simplegwt.initialization.client.Initializable;
import com.googlecode.simplegwt.initialization.client.InitializeCommand;
import com.googlecode.simplegwt.initialization.client.event.InitializationEvent;
import com.googlecode.simplegwt.initialization.client.event.InitializationEventHandler;

/**
 * Abstract parent class for {@link Composite}s that can be lazily initialized.
 * 
 * @since 1.0
 */
public abstract class InitializableComposite extends Composite implements Initializable {
	/**
	 * Adds a {@link DeferredCommand} to initialize the specified {@link Initializable}.
	 * 
	 * @param initializable
	 */
	public static void deferredInit(final Initializable initializable) {
		DeferredCommand.addCommand(new InitializeCommand(initializable));
	}

	private boolean initialized;

	/**
	 * @see com.googlecode.simplegwt.initialization.client.Initializable#initialize()
	 */
	public final void initialize() {
		if (!initialized) {
			initialized = true;
			onInitialize();
			HandlerManager handlers = getHandlers();

			if (handlers != null) {
				handlers.fireEvent(new InitializationEvent());
			}
		}
	}

	/**
	 * @see com.googlecode.simplegwt.initialization.client.Initializable#isInitialized()
	 */
	public final boolean isInitialized() {
		return initialized;
	}

	/**
	 * Called when the <code>InitializableComposite</code> is initialized.
	 */
	protected abstract void onInitialize();

	/**
	 * @see com.googlecode.simplegwt.initialization.client.Initializable#addIntializationEventHandler(com.googlecode.simplegwt.initialization.client.event.InitializationEventHandler)
	 */
	public HandlerRegistration addIntializationEventHandler(InitializationEventHandler handler) {
		return addHandler(handler, InitializationEvent.getType());
	}
}
