package com.company.services.XmlWrapper.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.company.services.XmlWrapper.reflection.ReflectionHelper;

@SuppressWarnings("UnusedDeclaration")
public class XmlSAXHandler extends DefaultHandler {
	private static final String CLASSNAME = "class";
	private String element = null;
	private Object object = null;
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(!qName.equals(CLASSNAME)) {
			element = qName;
		} else {
			String className = attributes.getValue(0);
			object = ReflectionHelper.createInstance(className);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		element = null;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(element != null) {
			String value = new String(ch, start, length);
			ReflectionHelper.setFieldValue(object, element, value);
		}
	}
	
	public Object getObject() {
		return object;
	}
}
