package com.mockenize.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * Created by rwatanabe on 10/02/16.
 */
public interface ResponseService {
    Response getResponse(HttpServletRequest request);
}
