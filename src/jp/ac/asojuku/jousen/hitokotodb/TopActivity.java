/**
 *
 */
package jp.ac.asojuku.jousen.hitokotodb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author user
 *
 */
public class TopActivity extends Activity {

	private SQLiteDatabase sqlDB;
	DBManager dbm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top);
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

		TextView text_n = (TextView)findViewById(R.id.Player_name);
		text_n.setText(name);
		TextView text_j = (TextView)findViewById(R.id.Player_job);
		text_j.setText(job);

		ImageView img = (ImageView)findViewById(R.id.imageViewjob);
		if(job.equals("戦士")){
			Resources res = getResources();
			Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.sensi);

			img.setImageBitmap(bmp);
		}

		Button btn_s = (Button)findViewById(R.id.button_setting);
		btn_s.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Intent intent = new Intent(TopActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
	    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
	    alertDialog.setTitle("アプリ終了");
	    alertDialog.setMessage("終了しますか？");
	    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	            // ボタン押下時の処理
	        	moveTaskToBack(true);
	        }
	    });
	    alertDialog.setNegativeButton("Cansel", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自動生成されたメソッド・スタブ

			}
		});
	    // キャンセルイベント
	    alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
	        public void onCancel(DialogInterface dialog) {
	            // キャンセルの処理
	        }
	    });
	    alertDialog.show();
		return super.onKeyDown(keyCode, event);
	}
}
