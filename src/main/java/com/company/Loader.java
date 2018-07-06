package com.company;

import java.util.Properties;

import com.company.services.FileWrapper.FileWrapper;

/**
 * Load all dependecies
 * @author Michael
 *
 */
public class Loader {
	private static FileWrapper fileWrapper;
	private Properties properties;
	
	public Loader(Properties prop) {
		// TODO Auto-generated constructor stub
		this.properties = prop;
		this.fileWrapper = FileWrapper.getInstance();
	}

	/**
	 * Build current loader
	 */
	public void build() {
		// TODO Auto-generated method stub
		
	}
}
