
package com.deetechs.wordwiz;
import java.io.IOException;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
*
* @author Benjamin
*/
public class PlayGameActivity extends Activity {
	/** Called when the activity is first created. */
	 Dialog myDialog;
	 XMLParser xmlparser;
	 StringShuffle string_shuffle;
	 int total_points = 0;
	 String word;	 
	 HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	 public static int count = 1;
	 public static int points = 0;
	 public static int level = 1;
	 public static int wrong_tracker = 0;	
	 
	@Override   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);       
        //shuffled words text view
        String shuffled_text;        
        TextView shuffled=(TextView)findViewById(R.id.shuffled_word);  
    	Log.i("MyActivity", "aCTIVITY START: Am a test and am here!!!");
        try {
        	xmlparser = new XMLParser();
			hmap = xmlparser.getEventsFromAnXML(this);	
			word = hmap.get(count).trim().toLowerCase();
			shuffled_text = getShuffledWord(word);
			shuffled.setText(shuffled_text);
		} catch (XmlPullParserException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
        final EditText user_text=(EditText)findViewById(R.id.user_word);         
        Button btn_submit = (Button) findViewById(R.id.btn_submit);        
        //Listening to submit button
        btn_submit.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View arg0) {
            	String user_entry = user_text.getText().toString().trim().toLowerCase();
            	Log.d("MyActivity", "Am a test and am here: word length!!!"+user_entry.length());
            	Log.d("MyActivity", "Word From XML "+word);
                Log.d("MyActivity", "Word From user "+user_entry);
                Log.d("Test", "Count: "+count);
                Log.d("Test", "Wrong: "+wrong_tracker);
                //handle empty words
                if(user_entry.length()==0){
                	// Creating alert Dialog with one button for empty fields
                	myDialog = new Dialog(PlayGameActivity.this);
                	myDialog.setContentView(R.layout.mydialog);                	
                	myDialog.setTitle("No empty entries!");
                	myDialog.setCancelable(true);            	
                	Button button = (Button) myDialog.findViewById(R.id.ok);
                	button.setOnClickListener(new OnClickListener() {
                	public void onClick(View v) {
                			myDialog.dismiss();
                			}
                		});
                			myDialog.show();
                	}                            
                //word is right OR wrong
                else{                    	
                	count++;
            		if(count<20 && user_entry.equalsIgnoreCase(word)){                			
                    	points++; 
                    	// Creating alert Dialog with one button for empty fields
                    	myDialog = new Dialog(PlayGameActivity.this);
                    	myDialog.setContentView(R.layout.passdialog);                	
                    	myDialog.setTitle("Nice One!");
                    	ImageView image = (ImageView) myDialog.findViewById(R.id.image_happy);
            			image.setImageResource(R.drawable.happy2);
                    	myDialog.setCancelable(true);            	
                    	Button button = (Button) myDialog.findViewById(R.id.ok_pass);
                    	button.setOnClickListener(new OnClickListener() {
                    	public void onClick(View v) {

                        	Intent play = new Intent(getApplicationContext(), PlayGameActivity.class);              	
                        	startActivity(play);  
                    		myDialog.dismiss();
                    			}
                    		});		                    	
                    			myDialog.show();                     
                    	
            			}
            		if(count<20 && !user_entry.equalsIgnoreCase(word)){            			
                    	wrong_tracker++;                    	                   		
                    	Intent wrong = new Intent(getApplicationContext(), WrongActivity.class);              	
                    	startActivity(wrong);  
            		} 
            		if(count==21 && points<12){                			
            			count=1;
            			level=1;
            			points=0;
            			wrong_tracker=0;
            			Intent oops = new Intent(getApplicationContext(), OopsActivity.class);              	
                     	startActivity(oops); 
                 	}
            		if(count==21 && points>=12){                			
            			level++;                		
            			Intent level_separator = new Intent(getApplicationContext(), LevelSeparatorActivity.class);              	
                     	startActivity(level_separator); 
                 	}
        			if(count>20 && user_entry.equalsIgnoreCase(word)){            			
                    	points++;                    	
                    	// Creating alert Dialog with one button for empty fields
                    	myDialog = new Dialog(PlayGameActivity.this);
                    	myDialog.setContentView(R.layout.passdialog);                	
                    	myDialog.setTitle("Nice One!");
                    	ImageView image = (ImageView) myDialog.findViewById(R.id.image_happy);
            			image.setImageResource(R.drawable.happy2);
                    	myDialog.setCancelable(true);            	
                    	Button button = (Button) myDialog.findViewById(R.id.ok_pass);
                    	button.setOnClickListener(new OnClickListener() {
                    	public void onClick(View v) {

                        	Intent play = new Intent(getApplicationContext(), PlayGameActivity.class);              	
                        	startActivity(play);  
                    		myDialog.dismiss();
                    			}
                    		});		                    	
                    			myDialog.show();   
            		}
            		if(count>20 && !user_entry.equalsIgnoreCase(word)){            			
            			wrong_tracker++;                    	                   		
                    	Intent wrong = new Intent(getApplicationContext(), WrongActivity.class);              	
                    	startActivity(wrong);  
            		} 
            		if(count==41 && points<25){                			
            			count=1;
            			level=1;
            			points=0;
            			wrong_tracker=0;
            			Intent oops = new Intent(getApplicationContext(), OopsActivity.class);              	
                     	startActivity(oops); 
                 	}
            		 if(count==41 && points>=25){                     		
                 		level++;                  		
            			Intent level_separator = new Intent(getApplicationContext(), LevelSeparatorActivity.class);              	
                     	startActivity(level_separator); 
                 	}  
        			if(count>40 && user_entry.equalsIgnoreCase(word)){            			
                    	points++;                    	
                    	// Creating alert Dialog with one button for empty fields
                    	myDialog = new Dialog(PlayGameActivity.this);
                    	myDialog.setContentView(R.layout.passdialog);                	
                    	myDialog.setTitle("Nice One!");
                    	ImageView image = (ImageView) myDialog.findViewById(R.id.image_happy);
            			image.setImageResource(R.drawable.happy2);
                    	myDialog.setCancelable(true);            	
                    	Button button = (Button) myDialog.findViewById(R.id.ok_pass);
                    	button.setOnClickListener(new OnClickListener() {
                    	public void onClick(View v) {

                        	Intent play = new Intent(getApplicationContext(), PlayGameActivity.class);              	
                        	startActivity(play);  
                    		myDialog.dismiss();
                    			}
                    		});		                    	
                    			myDialog.show();   
            		}
            		if(count>40 && !user_entry.equalsIgnoreCase(word)){                			
            			wrong_tracker++;                    	                   		
                    	Intent wrong = new Intent(getApplicationContext(), WrongActivity.class);              	
                    	startActivity(wrong);  
            		}    
            		if (count==61){
            			count=1;
            			level=1;
            			wrong_tracker=0;
                 		Intent end_game = new Intent(getApplicationContext(), EndGameActivity.class);              	
                     	startActivity(end_game);                      	
                 	}            	
            		              	
                	}                  	
                	    
                }
        });
      //Listening to hint button
        Button btn_hint = (Button) findViewById(R.id.btn_hint); 
        btn_hint.setOnClickListener(new View.OnClickListener() 
        { 
            public void onClick(View arg0) {
            	// Creating alert Dialog with one Button
            	myDialog = new Dialog(PlayGameActivity.this);
            	myDialog.setContentView(R.layout.mydialog);
            	String word_hint=word.substring(0,3);
            	myDialog.setTitle("   Starts with: "+word_hint);
            	myDialog.setCancelable(true);            	
            	Button button = (Button) myDialog.findViewById(R.id.ok);
            	button.setOnClickListener(new OnClickListener() {
            	public void onClick(View v) {
            		myDialog.dismiss();
            		}
            		});
            		myDialog.show();
            		}
            });
        //Listening to save button
        Button btn_save = (Button) findViewById(R.id.btn_save); 
        btn_save.setOnClickListener(new View.OnClickListener() 
        { 
            public void onClick(View arg0) {           
            	//save            	
            	save(1,""+count,""+level,""+wrong_tracker);
            	Log.d("Saved",""+count+""+level+""+wrong_tracker);
            	count=1;
            	level=1;
            	wrong_tracker=0;
            	Intent start_again = new Intent(getApplicationContext(), WordWizAppActivity.class);              	
             	startActivity(start_again); 
             	finish();
            		}            
            });
        //show level and points
        EditText points=(EditText)findViewById(R.id.points_text);
        points.setText(getPoints());        
        
        
    }
	public String getShuffledWord(String word) {
		String un_word="";
		string_shuffle = new StringShuffle();
		un_word = string_shuffle.shuffle(word);
		if (un_word.equalsIgnoreCase(word)) {
            int length = word.length();
            String a = word.substring(0, 2);
            String b = word.substring(2, length);
            un_word = b + a;            
        }        
		return un_word;		
	}
	public String getPoints(){
		String text="";
		if (level==1){
			total_points = (count-wrong_tracker-1)*10;
		}
		if (level==2){
			total_points = (count-wrong_tracker-1)*15;
		}
		if (level==3){
			total_points = (count-wrong_tracker-1)*20;
		}		
		text ="Level: "+level+" Points: "+total_points;
		return text;	
	}
	public void save(int id,String count,String level,String wrong_tracker){		
		 DatabaseHandler db = new DatabaseHandler(this); 
		 int num=db.getContactsCount();
		 Log.d("Records:","Records:"+num);
		 if(num==0){
			 db.addContact(new Contact(id,count,level,wrong_tracker));
		 }
		 else{
			 db.updateContact(new Contact(id,count,level,wrong_tracker));
		 }
	}
	 
}
