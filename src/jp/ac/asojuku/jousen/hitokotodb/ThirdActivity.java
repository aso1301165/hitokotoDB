/**
 *
 */
package jp.ac.asojuku.jousen.hitokotodb;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

		SQLiteCursor cursor_p = null;
		SQLiteCursor cursor_j = null;

		cursor_p = dbm.selectPlayer(sqlDB);
		cursor_p.moveToFirst();
		cursor_j = dbm.selectJob(sqlDB);
		cursor_j.moveToFirst();
		String name = cursor_p.getString(cursor_p.getColumnIndex("name"));
		String job = cursor_j.getString(cursor_j.getColumnIndex("name"));

		TextView text_n = (TextView)findViewById(R.id.textView_addName);
		text_n.setText(name);
		TextView text_j = (TextView)findViewById(R.id.textView_addJob);
		text_j.setText(job);

		Button btn_ok = (Button)findViewById(R.id.buttonOK);
		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Intent intent = new Intent(ThirdActivity.this, TopActivity.class);
				startActivity(intent);
			}
		});

		Button btn_can = (Button)findViewById(R.id.buttonCansel);
		btn_can.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}

}
