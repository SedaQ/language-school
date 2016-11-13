package com.fi.ls.exceptions;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class ServiceException extends Exception {
    
    public ServiceException() {
        super();
    }
    
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(Throwable cause) {
        super(cause);
    }
        
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
