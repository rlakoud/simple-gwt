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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.simplegwt.tbg.common.AsyncResponse.VoidData;

/**
 * Asynchronous service interface for accessing user preferences.
 */
public interface UserPreferenceServiceAsync {
	/**
	 * Returns the current user's preferences.
	 * 
	 * @param callback
	 */
	void getUserPreferences(AsyncCallback<AsyncResponse<Preferences>> callback);

	/**
	 * Saves the current user's preferences.
	 * 
	 * @param prefs
	 * @param callback
	 */
	void saveUserPreferences(Preferences prefs, AsyncCallback<AsyncResponse<VoidData>> callback);
}
