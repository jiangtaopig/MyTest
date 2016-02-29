package com.zjt.test.broadcaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class TestBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle mBundle = intent.getExtras();
		String val = mBundle.getString(BroadcastActivity.KEY, "xxx");
		Log.e("TestBroadcastReceiver", "xxxxxxxxx");
//		try {
//			Thread.sleep(13*1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Log.e("TestBroadcastReceiver", " ok get it , is "+val);
		
		
		
	}

}
