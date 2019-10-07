package com.deetechs.wordwiz;

import java.io.IOException;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

public class XMLParser {
	private static final String DESCRIPTION = "description";
	private static final String ID = "id";
	
	public HashMap<Integer, String> getEventsFromAnXML(Activity activity)
						throws XmlPullParserException, IOException
    {
    	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		String question = "";
		String qid = "";
    	int id = 0;
    	Resources res = activity.getResources();
    	XmlResourceParser parser = res.getXml(R.xml.myxml);    	
    	int eventType = parser.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT)
    	{
    		switch(eventType) {
    			// at start of document: START_DOCUMENT
                case XmlPullParser.START_DOCUMENT:
                   // study = new Study();
                        break;    			 
                    // at start of a tag: START_TAG
                case XmlPullParser.START_TAG:
                    // get tag name
                    String tagName = parser.getName();
                    if(tagName.equalsIgnoreCase(ID)) {
                        qid = parser.nextText();
                        id = Integer.parseInt(qid);
                        }                       
                    if(tagName.equalsIgnoreCase(DESCRIPTION)) {
                        question = parser.nextText();
                        }                    
                    break;
    		      }//End of switch
    		hmap.put(id, question);
    		eventType = parser.next();
    	}//End of while
    	return hmap;
    }
	//constructor for Parser
	public XMLParser() {
		super();		
	}

}
