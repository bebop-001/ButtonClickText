package com.example.buttonclicktest;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String logTag = "MainActivity";
	private Handler mHandler;
	AudioManager audioManager;
	private View.OnClickListener onclick () {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(logTag, "onClick...");
			}
		};
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final OnClickListener ocl = onclick();
        audioManager = (AudioManager) getSystemService(
                Context.AUDIO_SERVICE);

		Button b = (Button)findViewById(R.id.button1);
		b.setOnTouchListener(new OnTouchListener() {
			@Override
				public boolean onTouch(View v, MotionEvent event) {
					int e = event.getAction();
			        if (MotionEvent.ACTION_DOWN == e) {
						Log.i(logTag, "Button touch down");	
			        	mHandler = new Handler();
			            mHandler.postDelayed(mAction, 500);
			        	// ocl.onClick(v);
			        }
			        else if (MotionEvent.ACTION_UP == e) {
						Log.i(logTag, "Button touch up");					
			            mHandler.removeCallbacks(mAction);
			        }
					return false;
				}
			}
		);
		b.setOnClickListener(ocl);
	}
    private Runnable mAction = new Runnable() {
        @Override public void run() {
        	Log.i(logTag, "repeat");
        	audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK);
		    mHandler.postDelayed(this, 500);
		}
    };
}
