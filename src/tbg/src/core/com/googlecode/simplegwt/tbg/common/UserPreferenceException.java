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
package com.googlecode.simplegwt.tbg.common;

/**
 * Exception resulting from a request for user preferences.
 */
@SuppressWarnings("serial")
public class UserPreferenceException extends Exception {

	/**
	 * Creates a new <code>UserPreferenceException</code>.
	 */
	public UserPreferenceException() {
		super();
	}

	/**
	 * Creates a new <code>UserPreferenceException</code>.
	 * 
	 * @param message
	 */
	public UserPreferenceException(final String message) {
		super(message);
	}

	/**
	 * Creates a new <code>UserPreferenceException</code>.
	 * 
	 * @param message
	 * @param cause
	 */
	public UserPreferenceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates a new <code>UserPreferenceException</code>.
	 * 
	 * @param cause
	 */
	public UserPreferenceException(final Throwable cause) {
		super(cause);
	}

}
