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

import java.io.Serializable;

/**
 * Wrapper for an RPC response. Contains a success flag, an optional message, and optional data.
 * 
 * @param <T> The type of data included in the response. Use
 *            AsyncResponse&lt;AsyncReponse.VoidData&gt; if no data will ever be returned.
 */
@SuppressWarnings("serial")
public class AsyncResponse<T extends Serializable> implements Serializable {
	/**
	 * Serializable type for an {@link AsyncResponse} that never returns data.
	 */
	public static class VoidData implements Serializable {
		private VoidData() {
			super();
		}
	}

	private boolean success;
	private T data;
	private String message;

	/**
	 * Creates a new <code>AsyncResponse</code>.
	 * 
	 * @param success
	 */
	public AsyncResponse(final boolean success) {
		this(success, null, null);
	}

	/**
	 * Creates a new <code>AsyncResponse</code>.
	 * 
	 * @param success
	 * @param data
	 */
	public AsyncResponse(final boolean success, final T data) {
		this(success, data, null);
	}

	/**
	 * Creates a new <code>AsyncResponse</code>.
	 * 
	 * @param success
	 * @param data
	 * @param message
	 */
	public AsyncResponse(final boolean success, final T data, final String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}

	/**
	 * Zero-argument constructor for serialization.
	 */
	@SuppressWarnings("unused")
	private AsyncResponse() {
		super();
	}

	/**
	 * @return the data, or null if no data is available
	 */
	public T getData() {
		return data;
	}

	/**
	 * @return the message, or null if no message is available
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return true if the request was completed successfully, otherwise false
	 */
	public boolean isSuccess() {
		return success;
	}
}
