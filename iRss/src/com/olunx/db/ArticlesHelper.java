package com.olunx.db;

import java.io.File;
import java.util.HashMap;

import com.olunx.util.Config;
import com.olunx.util.Utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ArticlesHelper implements IHelper {

	private final String c_id = "_id";
	public final static String c_title = "title";
	public final static String c_desc = "desc";
	public final static String c_content = "content";
	public final static String c_author = "author";
	public final static String c_publishTime = "publish_time";
	public final static String c_link = "link";
	public final static String c_type = "type";
	public final static String c_unread = "unread";
	public final static String c_stared = "stared";
	public final static String c_tags = "tags";
	public final static String c_feedXmlUrl = "feed_xml_url";

	private static String TABLE = "t_articles";
	private final String TAG = "com.olunx.db.ArticlesHelper";

	private static SQLiteDatabase sqlite = null;

	public ArticlesHelper() {
		super();
		getDB();
		createTable();
	}

	@Override
	public SQLiteDatabase getDB() {
		try {
			if (sqlite == null || !sqlite.isOpen()) {
				File file = Utils.init().createFileIfNotExist(Config.FILE_SDCARD_DATABASE);
				sqlite = SQLiteDatabase.openOrCreateDatabase(file, null);
			}
			return sqlite;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public void close() {
		if (sqlite != null || sqlite.isOpen()) {
			Log.i(TAG, "sqlite close");
			sqlite.close();
		}
	}

	@Override
	public void createTable() {
		Log.i(TAG, "create table");
		this.getDB().execSQL(
				"create table if not exists " + TABLE + "(" + c_id + " int primary key," + c_title + " text," + c_desc + " text,"
						+ c_content + " text," + c_author + " text," + c_publishTime + " text," + c_link + " text," + c_type + " text,"
						+ c_unread + " text," + c_stared + " text," + c_tags + " text," + c_feedXmlUrl + " text);");
	}

	@Override
	public void dropTable() {
		Log.i(TAG, "drop table");
		this.getDB().execSQL("drop table if exists " + TABLE + ";");
	}

	private ContentValues row = null;

	/**
	 * 添加Article
	 * 
	 * @param object
	 */
	public void addRecord(ContentValues values) {
		getDB().insert(TABLE, null, values);
	}

	/**
	 * 更新文章内容
	 * 
	 * @param title
	 * @param content
	 */
	public void updateContent(String title, String content) {
		row = new ContentValues();
		row.put(c_content, content);
		getDB().update(TABLE, row, c_title + "== ? ", new String[] { title });
	}

	/**
	 * 更新Feed的文章数目
	 */
	public void updateFeeds() {
		Cursor result = getDB().query(TABLE, new String[] { c_feedXmlUrl, "count(" + c_feedXmlUrl + ") as count" }, null, null,
				c_feedXmlUrl, null, null);

		int urlColumn = result.getColumnIndex(c_feedXmlUrl);
		int countColumn = result.getColumnIndex("count");

		FeedsHelper helper = new FeedsHelper();

		String url = null;
		String count = null;
		if (result != null) {
			result.moveToFirst();
			while (!result.isAfterLast()) {
				url = result.getString(urlColumn);
				count = result.getString(countColumn);
				helper.updateArticleCount(url, count);
				result.moveToNext();
			}
		}
		result.close();
		helper.close();
	}

	/**
	 * 获取分类下的文章列表
	 * 
	 * @param articleTitle
	 * @return
	 */
	public Cursor getArticlesByXmlUrl(String feedXmlUrl) {
		return getDB().query(TABLE, new String[] { c_id, c_title, c_link }, c_feedXmlUrl + "== ?", new String[] { feedXmlUrl }, null, null,
				null);
	}

	/**
	 * 获取文章
	 * 
	 * @param title
	 * @return
	 */
	public HashMap<String, String> getArticleByTitle(String title) {
		HashMap<String, String> article = new HashMap<String, String>();

		Cursor result = getDB().query(TABLE, new String[] { c_id, c_content }, c_title + "== ?", new String[] { title }, null, null, null);

		if (result != null) {
			result.moveToFirst();
			int contentIndex = result.getColumnIndex(c_content);
			while (!result.isAfterLast()) {
				article.put(c_content, result.getString(contentIndex));
				result.moveToNext();
			}
		}
		result.close();

		return article;
	}

	/**
	 * 判断文章是否存在
	 * 
	 * @param articleTitle
	 * @return
	 */
	public boolean isExistsArticle(String articleTitle) {
		String str = null;
		Cursor result = getDB().query(TABLE, new String[] { c_title }, c_title + "== ?", new String[] { articleTitle }, null, null, null);
		if (result != null) {
			result.moveToFirst();
			int index = result.getColumnIndex(c_title);
			while (!result.isAfterLast()) {
				str = result.getString(index);
				result.moveToNext();
			}
		}
		result.close();
		if (str == null || str == "" || str.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 删除文章
	 * 
	 * @param feedXmlUrl
	 */
	public void deleteRecords(String feedXmlUrl) {
		String sql = "delete from " + TABLE + " where " + c_feedXmlUrl + " == '" + feedXmlUrl + "'";
		this.getDB().execSQL(sql);
	}

}
