/**
 *
 */
package jp.ac.asojuku.jousen.hitokotodb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author user
 *
 */
public class SettingActivity extends Activity {

    private AlertDialog.Builder alertDialog;
	private EditText edit;
	private SQLiteDatabase sqlDB;
	DBManager dbm;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		dbm = new DBManager(this);
		sqlDB = dbm.getWritableDatabase();

		alertDialog = new AlertDialog.Builder(this);

		Button btn_n = (Button)findViewById(R.id.NameChange);
		btn_n.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				edit = new EditText(getApplicationContext());

			    alertDialog.setTitle("ニックネーム変更");
			    alertDialog.setView(edit);
			    alertDialog.setPositiveButton("変更", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int whichButton) {
			            // ボタン押下時の処理
						String name = edit.getText().toString();
						if(edit != null)dbm.insertName(sqlDB, name);
						edit.setText("");
			        }
			    });
			    alertDialog.setNegativeButton("キャンセル", new OnClickListener() {
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
			}
		});

		Button btn_f = (Button)findViewById(R.id.setting_format);
		btn_f.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
			    alertDialog.setTitle("初期化");
			    alertDialog.setMessage("初期化しますか？");
			    alertDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int whichButton) {
			            // ボタン押下時の処理
						dbm.deletePlayer(sqlDB);
			        	moveTaskToBack(true);
			        }
			    });
			    alertDialog.setNegativeButton("いいえ", new OnClickListener() {
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

			}
		});

		Button btn_t = (Button)findViewById(R.id.button_top);
		btn_t.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Intent intent = new Intent(SettingActivity.this, TopActivity.class);
				startActivity(intent);
			}
		});
	}

}
