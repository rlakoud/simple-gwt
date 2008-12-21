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

import com.google.gwt.event.shared.HandlerRegistration;
import com.googlecode.simplegwt.initialization.client.event.InitializationEvent;
import com.googlecode.simplegwt.initialization.client.event.InitializationEventHandler;

/**
 * Interface for objects that can be lazily initialized.
 * 
 * @since 1.0
 */
public interface Initializable {
	/**
	 * @return true if this object has been initialized.
	 */
	boolean isInitialized();

	/**
	 * Initializes this object.
	 */
	void initialize();

	/**
	 * Adds a {@link InitializationEvent} handler.
	 * 
	 * @param handler the initialization event handler
	 * @return {@link HandlerRegistration} used to remove this handler
	 */
	HandlerRegistration addIntializationEventHandler(InitializationEventHandler handler);
}
