package com.company.services.Telemetry;

import org.apache.log4j.Logger;

public class AddCasus {

    private final static Logger logger = Logger.getLogger(AddCasus.class);

    public static void Error(String message, String stackTrace, Priority priority){
        logger.error(message);
    }

}
