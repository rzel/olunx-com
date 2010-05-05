package org.wordpress.android;

import java.util.HashMap;
import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class localDraftsDB {

	private static final String CREATE_TABLE_LOCALDRAFTS = "create table if not exists localdrafts (id integer primary key autoincrement, blogID text, uploaded boolean, title text,content text, picturePaths text, tags text, categories text, publish boolean);";
	private static final String CREATE_TABLE_LOCALPAGEDRAFTS = "create table if not exists localpagedrafts (id integer primary key autoincrement, blogID text, uploaded boolean, title text,content text, picturePaths text, publish boolean);";

	private static final String LOCALDRAFTS_TABLE = "localdrafts";
	private static final String LOCALPAGEDRAFTS_TABLE = "localpagedrafts";
	private static final String DATABASE_NAME = "wordpress";
	
	private static final String ADD_LATITUDE = "alter table localdrafts add latitude real";
	private static final String ADD_LONGITUDE = "alter table localdrafts add longitude real";

	private SQLiteDatabase db;

	public localDraftsDB(Context ctx) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		db.execSQL(CREATE_TABLE_LOCALDRAFTS);
		db.execSQL(CREATE_TABLE_LOCALPAGEDRAFTS);
		
		if (db.getVersion() == 4){
			db.execSQL(ADD_LATITUDE);
			db.execSQL(ADD_LONGITUDE);
			db.setVersion(5);
		}
		
		db.close();
	}

	public boolean saveLocalDraft(Context ctx, String blogID, String title, String content, String picturePaths, String tags, String categories, boolean publish, Double latitude, Double longitude) {
		boolean returnValue = false;
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		
			ContentValues values = new ContentValues();
			values.put("blogID", blogID);
			values.put("title", title);
			values.put("content", content);
			values.put("picturePaths", picturePaths);
			values.put("tags", tags);
			values.put("categories", categories);
			values.put("publish", publish);
			values.put("latitude", latitude);
			values.put("longitude", longitude);
			returnValue = db.insert(LOCALDRAFTS_TABLE, null, values) > 0;

		db.close();
		return (returnValue);
	}
	
	public boolean updateLocalDraft(Context ctx, String blogID, String postID, String title, String content, String picturePaths, String tags, String categories, boolean publish) {
		boolean returnValue = false;
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		
			ContentValues values = new ContentValues();
			values.put("blogID", blogID);
			values.put("title", title);
			values.put("content", content);
			values.put("picturePaths", picturePaths);
			values.put("tags", tags);
			values.put("categories", categories);
			values.put("publish", publish);
			returnValue = db.update(LOCALDRAFTS_TABLE, values, "id=" + postID, null) > 0;

		db.close();
		return (returnValue);
	}

	public Vector loadPosts(Context ctx, String blogID) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		Vector returnVector = new Vector();
		Cursor c = db.query(LOCALDRAFTS_TABLE, new String[] { "id", "title", "publish", "uploaded"}, "blogID=" + blogID, null, null, null, "id desc");
		int numRows = c.getCount();
		c.moveToFirst();
		
		for (int i = 0; i < numRows; ++i) {
		if (c.getString(0) != null){
		HashMap returnHash = new HashMap();
		returnHash.put("id", c.getInt(0));
		returnHash.put("title", c.getString(1));
		returnHash.put("publish", c.getInt(2));
		returnHash.put("uploaded", c.getInt(3));
		returnVector.add(i, returnHash);
		}
		c.moveToNext();
		}
		c.close();
		db.close();

		if (numRows == 0){
			returnVector = null;
		}
		
		return returnVector;
	}
	
	public Vector loadPost(Context ctx, String postID) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		Vector returnVector = new Vector();
		Cursor c = db.query(LOCALDRAFTS_TABLE, new String[] { "title", "content", "picturePaths", "tags", "categories", "publish", "latitude", "longitude"}, "id=" + postID, null, null, null, null);
		
		int numRows = c.getCount();
		c.moveToFirst();
		
		for (int i = 0; i < numRows; ++i) {
		if (c.getString(0) != null){
		HashMap returnHash = new HashMap();
		returnHash.put("title", c.getString(0));
		returnHash.put("content", c.getString(1));
		returnHash.put("picturePaths", c.getString(2));
		returnHash.put("tags", c.getString(3));
		returnHash.put("categories", c.getString(4));
		returnHash.put("publish", c.getInt(5));
		returnHash.put("latitude", c.getDouble(6));
		returnHash.put("longitude", c.getDouble(7));
		returnVector.add(i, returnHash);
		}
		c.moveToNext();
		}
		c.close();
		db.close();
		
		if (numRows == 0){
			returnVector = null;
		}
		
		return returnVector;
	}

	public boolean deletePost(Context ctx, String postID) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		Vector returnVector = new Vector();
		
		boolean returnValue = false;

		int result = 0;
		result = db.delete(LOCALDRAFTS_TABLE, "id=" + postID, null);
		db.close();
		
		if (result == 1){
			returnValue = true;
		}
		
		return returnValue;
	}
	
	public boolean saveLocalPageDraft(Context ctx, String blogID, String title, String content, String picturePaths, boolean publish) {
		boolean returnValue = false;
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		
			ContentValues values = new ContentValues();
			values.put("blogID", blogID);
			values.put("title", title);
			values.put("content", content);
			values.put("picturePaths", picturePaths);
			values.put("publish", publish);
			returnValue = db.insert(LOCALPAGEDRAFTS_TABLE, null, values) > 0;

		db.close();
		return (returnValue);
	}
	
	public boolean updateLocalPageDraft(Context ctx, String blogID, String postID, String title, String content, String picturePaths, boolean publish) {
		boolean returnValue = false;
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		
			ContentValues values = new ContentValues();
			values.put("blogID", blogID);
			values.put("title", title);
			values.put("content", content);
			values.put("picturePaths", picturePaths);
			values.put("publish", publish);
			returnValue = db.update(LOCALPAGEDRAFTS_TABLE, values, "id=" + postID, null) > 0;

		db.close();
		return (returnValue);
	}
	
	public Vector loadPageDrafts(Context ctx, String blogID) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		Vector returnVector = new Vector();
		Cursor c = db.query(LOCALPAGEDRAFTS_TABLE, new String[] { "id", "title", "publish", "uploaded"}, "blogID=" + blogID, null, null, null, "id desc");
		int numRows = c.getCount();
		c.moveToFirst();
		
		for (int i = 0; i < numRows; ++i) {
		if (c.getString(0) != null){
		HashMap returnHash = new HashMap();
		returnHash.put("id", c.getInt(0));
		returnHash.put("title", c.getString(1));
		returnHash.put("publish", c.getInt(2));
		returnHash.put("uploaded", c.getInt(3));
		returnVector.add(i, returnHash);
		}
		c.moveToNext();
		}
		c.close();
		db.close();

		if (numRows == 0){
			returnVector = null;
		}
		
		return returnVector;
	}
	
	public Vector loadPageDraft(Context ctx, String postID) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		Vector returnVector = new Vector();
		Cursor c = db.query(LOCALPAGEDRAFTS_TABLE, new String[] { "title", "content", "picturePaths", "publish"}, "id=" + postID, null, null, null, null);
		
		int numRows = c.getCount();
		c.moveToFirst();
		
		for (int i = 0; i < numRows; ++i) {
		if (c.getString(0) != null){
		HashMap returnHash = new HashMap();
		returnHash.put("title", c.getString(0));
		returnHash.put("content", c.getString(1));
		returnHash.put("picturePaths", c.getString(2));
		returnHash.put("publish", c.getInt(3));
		returnVector.add(i, returnHash);
		}
		c.moveToNext();
		}
		c.close();
		db.close();
		
		if (numRows == 0){
			returnVector = null;
		}
		
		return returnVector;
	}

	public boolean deletePageDraft(Context ctx, String postID) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		Vector returnVector = new Vector();
		
		boolean returnValue = false;

		int result = 0;
		result = db.delete(LOCALPAGEDRAFTS_TABLE, "id=" + postID, null);
		db.close();
		
		if (result == 1){
			returnValue = true;
		}
		
		return returnValue;
	}

	public int getLatestDraftID(Context ctx, String id) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		Cursor c = db.query(LOCALDRAFTS_TABLE, new String[] {"id"}, "blogID=" + id, null, null, null, "id desc", "1");
		
		int latestID = -1;
		int numRows = c.getCount();
		if (numRows != 0){
			c.moveToFirst();
			latestID = c.getInt(0);
		}
		c.close();
		db.close();
		
		return latestID;
	}
	
	public int getLatestPageDraftID(Context ctx, String id) {
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		Cursor c = db.query(LOCALPAGEDRAFTS_TABLE, new String[] {"id"}, "blogID=" + id, null, null, null, "id desc", "1");
		
		int latestID = -1;
		int numRows = c.getCount();
		if (numRows != 0){
			c.moveToFirst();
			latestID = c.getInt(0);
		}
		c.close();
		db.close();
		
		return latestID;
	}

}
