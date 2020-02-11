package com.company.Services.XmlWrapper.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFileSAX {
	public static Object readXML(String xmlFile) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			
			XmlSAXHandler handler = new XmlSAXHandler();
			parser.parse(xmlFile, handler);
			
			return handler.getObject();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
