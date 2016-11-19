package com.fi.ls.exceptions;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class ServiceLayerException extends DataAccessException {
    
	private static final long serialVersionUID = 8606352464618478989L;

	public ServiceLayerException(String message) {
        super(message);
    }
     
    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
