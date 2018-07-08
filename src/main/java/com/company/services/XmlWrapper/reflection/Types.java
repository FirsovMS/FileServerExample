package com.company.services.XmlWrapper.reflection;

/**
 * Full description of used types. 
 * @author Michael
 */
public enum Types {
	BYTE,
    BOOLEAN,
    SHORT,
    CHAR,
    INT,
    FLOAT,
    LONG,
    DOUBLE,
    STRING;
	
	/**
	 * Return Types value by 'class' name
	 * @param className
	 * @return
	 */
	public static Types geTypes(Class<?> className) {
		return Types.valueOf(className.getSimpleName().toUpperCase());
	}
}
