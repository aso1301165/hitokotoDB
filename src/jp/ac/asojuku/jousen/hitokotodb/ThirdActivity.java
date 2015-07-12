/**
 *
 */
package jp.ac.asojuku.jousen.hitokotodb;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author user
 *
 */
public class ThirdActivity extends Activity {

	private SQLiteDatabase sqlDB;
	DBManager dbm;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		dbm = new DBManager(this);
		sqlDB = dbm.getWritableDatabase();

		SQLiteCursor cursor = null;

		cursor = dbm.selectPlayer(sqlDB);
		String[] from = {"name"};

		TextView name = (TextView)findViewById(R.id.textView_addName);
	}

}
