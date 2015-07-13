/**
 *
 */
package jp.ac.asojuku.jousen.hitokotodb;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author user
 *
 */
public class DBManager extends SQLiteOpenHelper {

	/* (非 Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */

	public DBManager(Context context){
		super(context, "rpg.sqlite3", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ

		db.execSQL("create table if not exists player(player_id integer primary key autoincrement, name text, job_id integer)");
		db.execSQL("create table if not exists job(job_id integer primary key autoincrement, name text, hp integer, atk integer, mag integer)");
	}

	/* (非 Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("DROP TABLE player");
		onCreate(db);
	}

	public void insertName(SQLiteDatabase db, String inputName){

		String sql = "insert into player(name) values(?)";
		db.execSQL(sql, new String[]{inputName});
	}

	public void deletePlayer(SQLiteDatabase db){
		String sql = "drop table player";
		db.execSQL(sql);
		String del = "drop table job";
		db.execSQL(del);
		db.execSQL("create table if not exists player(player_id integer primary key autoincrement, name text, job_id integer)");
		db.execSQL("create table if not exists job(job_id integer primary key autoincrement, name text, hp integer, atk integer, mag integer)");
		db.execSQL("insert into job (name, hp, atk, mag) values ('戦士', " + 150 + ", " + 15 + ", " + 20 + ")");
		db.execSQL("insert into job (name, hp, atk, mag) values ('魔導士', " + 100 + ", " + 10 + ", " + 1000 + ")");

	}

	public SQLiteCursor selectPlayer(SQLiteDatabase db){
		String sql = "select name from player order by player_id";

		SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sql, null);

		return cursor;
	}

	public SQLiteCursor selectJob(SQLiteDatabase db){
		String sql = "select j.name from player p, job j where p.job_id = j.job_id and p.player_id = 1 order by player_id";

		SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sql, null);

		return cursor;
	}

	public void insertJobAtk(SQLiteDatabase db){

		String sql = "update player set job_id = 1 where player_id = 1";
		db.execSQL(sql);

	}

	public void insertJobMag(SQLiteDatabase db){

		String sql = "update player set job_id = 2 where player_id = 1";
		db.execSQL(sql);

	}

}
