/**
 *
 */
package jp.ac.asojuku.jousen.hitokotodb;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author user
 *
 */
public class SecondActivity extends Activity {

	private SQLiteDatabase sqlDB;
	DBManager dbm;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		dbm = new DBManager(this);
		sqlDB = dbm.getWritableDatabase();

		Button job_a = (Button)findViewById(R.id.button_attack);
		job_a.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ

				dbm.insertJobAtk(sqlDB);

				Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
				startActivity(intent);

			}
		});
	}

}
