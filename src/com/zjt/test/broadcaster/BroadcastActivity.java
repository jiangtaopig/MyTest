package com.zjt.test.broadcaster;

import com.example.drawview.R;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastActivity extends Activity {
	
	private final static String BROADCAST_ACTION = "com.com.zjt.test.broadcaster.ACTION";
	private final static String BROADCAST_ACTION2 = "com.com.zjt.test.broadcaster.ACTION";
	public final static String KEY = "KEY";
	private final static String PROVIDER_URI = "content://com.example.databasepro.provider/book";
	
	private final static String TAG = "BroadcastActivity";
	
	private Button sendBroadCast;
	private Button queryProviderData;
	
	private TestBroadcastReceiver mBroadcastReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_broadcast_main_layout);
		
		initView();
		mBroadcastReceiver = new TestBroadcastReceiver();
		initEvent();
		
	}

	private void initView() {
		//sendBroadCast.setEnabled(false); //使button灰化，不可点击
		sendBroadCast = (Button) findViewById(R.id.send_broadcast);
		queryProviderData = (Button) findViewById(R.id.query_provider);
	}
	
	private void initEvent() {
		sendBroadCast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(BROADCAST_ACTION);
				Bundle mBundle = new Bundle();
				mBundle.putString(KEY, "要传递的数据");
				mIntent.putExtras(mBundle);
				sendBroadcast(mIntent);
			}
		});
		
		//通过ContentProvider来访问DataBasePro这个工程中的数据，
		//注意，需要在DataBasePro的manifest文件中注册的provider中添加属性： android:exported="true"
		queryProviderData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//查询数据
				Uri uri2 = Uri.parse(PROVIDER_URI);
				Cursor cursor = getContentResolver().query(uri2, null, null, null, null);
				if(cursor != null){
					while(cursor.moveToNext()){
						String name = cursor.getString(cursor.getColumnIndex("name"));
						String author = cursor.getString(cursor.getColumnIndex("author"));
						int pages = cursor.getInt(cursor.getColumnIndex("pages"));
						double price = cursor.getDouble(cursor.getColumnIndex("price"));
						
						Log.d(TAG, "name = "+name);
						Log.d(TAG, "author = "+author);
						Log.d(TAG, "pages = "+pages);
						Log.d(TAG, "price = "+price);
					}
					cursor.close();
				}
			}
		});
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		/**
		 * 一般在 onResume 中注册广播
		 */
		IntentFilter mIntentFilter = new IntentFilter(BROADCAST_ACTION2);
		registerReceiver(mBroadcastReceiver, mIntentFilter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//在 onDestroy 取消注册的广播
		unregisterReceiver(mBroadcastReceiver);
	}
	

}
