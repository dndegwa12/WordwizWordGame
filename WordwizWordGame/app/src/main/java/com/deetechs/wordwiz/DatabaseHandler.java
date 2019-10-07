package com.deetechs.wordwiz;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "dbName.db";

	// Contacts table name
	private static final String TABLE_SCORES = "scores";

	// Contacts Table Columns names	
	private static final String id="id";
	private static final String count="count" ;
	private static final String level="level";
	private static final String wrong_tracker="wrong_tracker";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+TABLE_SCORES+"("+id+" INTEGER, "+
			        count+" TEXT, "+level+" TEXT, "+wrong_tracker+" TEXT);");		  
		
	}
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
		// Create tables again
		onCreate(db);
	}
	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */
	// Adding new contact
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(id, contact.getId()); // Contact id
		values.put(count, contact.getCount()); // Contact count
		values.put(level, contact.getLevel()); // Contact level
		values.put(wrong_tracker, contact.getWrong_tracker()); // Contact count
		

		// Inserting Row
		db.insert(TABLE_SCORES, null, values);
		db.close(); // Closing database connection
	}

	// Getting single scores list
	public String getContact() {
		SQLiteDatabase db = this.getReadableDatabase();		
		String [] columns=new String[]{id,count,level,wrong_tracker};
		Cursor c=db.query(TABLE_SCORES, columns,null, null, null, null, null);
		if (c != null)
			c.moveToFirst();
		String rs=c.getString(0)+","+c.getString(1)+","+c.getString(2)+","+c.getString(3);
		Log.d("Query: ",rs);
		return rs;		
	}
	
	// Getting All Contacts
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SCORES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setId(Integer.parseInt(cursor.getString(0)));
				contact.setCount(cursor.getString(1));
				contact.setLevel(cursor.getString(2));
				contact.setWrong_tracker(cursor.getString(3));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}
	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(id, contact.getId()); // Contact id
		values.put(count, contact.getCount()); // Contact count
		values.put(level, contact.getLevel()); // Contact level
		values.put(wrong_tracker, contact.getWrong_tracker()); // Contact count

		// updating row
		return db.update(TABLE_SCORES, values, id + " = ?",
				new String[] { String.valueOf(contact.getId()) });
	}
	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SCORES, id + " = ?",
				new String[] { String.valueOf(contact.getId()) });
		db.close();
	}
	// Getting contacts Count
	public int getContactsCount() {		
		
		SQLiteDatabase db = this.getReadableDatabase();
		String countQuery = "SELECT  * FROM " + TABLE_SCORES;
		Cursor cursor = db.rawQuery(countQuery, null);
		// return count
		return cursor.getCount();
	}

}
