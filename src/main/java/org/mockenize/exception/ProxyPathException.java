package org.mockenize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ProxyPathException extends WebApplicationException {

	private static final long serialVersionUID = 7714442933981404781L;

	public ProxyPathException() {
        super("Can't define proxy path.", Response.Status.INTERNAL_SERVER_ERROR);
    }
}
