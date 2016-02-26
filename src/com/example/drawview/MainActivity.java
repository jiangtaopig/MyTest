package com.example.drawview;

import com.example.drawview.view.GuaGuaKaView;
import com.example.drawview.view.PorterDuffXfermodeView;
import com.example.drawview.view.PoterDuffLoadingView;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.custom_view01_layout);
		//setContentView(R.layout.custom_view02_layout);
		//setContentView(R.layout.view_groupe_layout);
		//drawTest();
		
		//setContentView(R.layout.my_test_view_layout);
		//setContentView(new PorterDuffXfermodeView(MainActivity.this));
		//setContentView(new PoterDuffLoadingView(MainActivity.this));
		setContentView(R.layout.guaguaka_layout);
	}

	public void drawTest() {
		Paint p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		p1.setTextSize(30);
		p1.setColor(Color.WHITE);

		FontMetrics fontMetrics = p1.getFontMetrics();
		String text = "abcdefghijk";

		float baseX = 0;
		float baseY = 100;
		float topY = baseY + fontMetrics.top;
		float ascentY = baseY + fontMetrics.ascent;
		float descentY = baseY + fontMetrics.descent;
		float bottomY = baseY + fontMetrics.bottom;
		
		Canvas canvas = new Canvas();
		canvas.drawText(text, baseX, bottomY, p1);
		
	}

}
