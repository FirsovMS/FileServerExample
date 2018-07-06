package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    
    public static void main(String[] args) {
    	// load properties
		Properties props = new Properties();
		try(InputStream in = new FileInputStream("config.properties"))
		{
			props.load(in);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		
		// Load all services
		Loader loader = new Loader(props);
    	loader.build();
    }
}
