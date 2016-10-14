package com.fi.eshop.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Seda
 *
 */
public class TestLogging {

	private final static Logger logger = LoggerFactory.getLogger(TestLogging.class);

	public static void main(String[] args) {
		logger.info("INFO TESTING");
		logger.debug("DEBUG TESTING");
		logger.error("ERROR TESTING");
		System.out.println("TEST EXPLICITLY WRITING TO CONSOLE");
	}
}
