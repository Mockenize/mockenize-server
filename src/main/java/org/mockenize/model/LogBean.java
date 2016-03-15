package org.mockenize.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Data
public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID key;

    private LogType type;

    private String url;

    private String path;

    private String method;

    private RequestLogBean request;

    private ResponseLogBean response;

    private Date date = new Date();

    protected LogBean() {
    }

    public LogBean(UUID key, LogType type) {
        this.key = key;
        this.type = type;
    }
}
