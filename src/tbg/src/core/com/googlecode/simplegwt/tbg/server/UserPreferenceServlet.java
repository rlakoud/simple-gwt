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
package com.googlecode.simplegwt.tbg.server;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.simplegwt.tbg.common.AsyncResponse;
import com.googlecode.simplegwt.tbg.common.Preferences;
import com.googlecode.simplegwt.tbg.common.UserPreferenceException;
import com.googlecode.simplegwt.tbg.common.UserPreferenceService;
import com.googlecode.simplegwt.tbg.common.AsyncResponse.VoidData;
import com.googlecode.simplegwt.tbg.server.data.PersistentPreferences;

/**
 * Servlet implementation of the {@link UserPreferenceService}.
 */
@SuppressWarnings("serial")
public class UserPreferenceServlet extends RemoteServiceServlet implements UserPreferenceService {
	private static final PersistenceManagerFactory factory = JDOHelper
	        .getPersistenceManagerFactory("transactions-optional");

	/**
	 * @see com.googlecode.simplegwt.tbg.common.UserPreferenceService#getUserPreferences()
	 */
	public AsyncResponse<Preferences> getUserPreferences() throws UserPreferenceException {
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		final boolean success;
		final Preferences prefs;
		final String message;

		if (user == null) {
			final String loginUrl = userService.createLoginURL(getInitParameter("index"));

			success = false;
			prefs = null;
			message = "<a href=\"" + loginUrl + "\">Login</a>";
		} else {
			success = true;

			final PersistentPreferences persistentPreferences = getPreferences(user);

			prefs = new Preferences();
			prefs.setUserName(user.getNickname());

			if (persistentPreferences != null) {
				prefs.setColumns(persistentPreferences.getColumns());
				prefs.setRows(persistentPreferences.getRows());
				prefs.setDownText(persistentPreferences.getDownText());
				prefs.setUpText(persistentPreferences.getUpText());
			}

			message = null;
		}

		return new AsyncResponse<Preferences>(success, prefs, message);
	}

	/**
	 * @see com.googlecode.simplegwt.tbg.common.UserPreferenceService#saveUserPreferences(com.googlecode.simplegwt.tbg.common.Preferences)
	 */
	public AsyncResponse<VoidData> saveUserPreferences(final Preferences prefs)
	        throws UserPreferenceException {
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();

		final PersistenceManager pm = factory.getPersistenceManager();

		final PersistentPreferences persistentPreferences = getOrCreatePreferences(user);

		persistentPreferences.setRows(prefs.getRows());
		persistentPreferences.setColumns(prefs.getColumns());
		persistentPreferences.setDownText(prefs.getDownText());
		persistentPreferences.setUpText(prefs.getUpText());

		try {
			pm.makePersistent(persistentPreferences);
		} finally {
			pm.close();
		}

		return new AsyncResponse<VoidData>(true);
	}

	private PersistentPreferences getOrCreatePreferences(final User user) {
		PersistentPreferences prefs = getPreferences(user);

		if (prefs == null) {
			prefs = new PersistentPreferences(user);
		}

		return prefs;
	}

	@SuppressWarnings("unchecked")
	private PersistentPreferences getPreferences(final User user) {
		final PersistenceManager pm = factory.getPersistenceManager();
		try {
			final List<PersistentPreferences> results = (List<PersistentPreferences>) pm.newQuery(
			        "select from " + PersistentPreferences.class.getName()
			                + " where user == userParam PARAMETERS " + User.class.getName()
			                + " userParam").execute(user);
			return results.isEmpty() ? null : results.get(0);
		} finally {
			pm.close();
		}
	}
}
