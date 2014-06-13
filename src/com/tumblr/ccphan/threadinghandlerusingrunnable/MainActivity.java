package com.tumblr.ccphan.threadinghandlerusingrunnable;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView txt;
	// Our handler.
	// Processes Messages and Runnable objects associated with the current
	// thread MessageQueue. the message queue holds the tasks to be executed in
	// FIFO (First In First Out) manner. You will need only one Handler per
	// activity where the background thread will communicate with to update the
	// UI.
	// For Messages (as oppose to Runnables) the handler is responsible
	// implementing the response via the callback method.
	// For Runnables the 'sender' is responsible for the implementing the
	// response.
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt = (TextView) findViewById(R.id.txt);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// create a new thread that runs in the background
		Thread background = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(1000);

						Runnable r = new Runnable() {

							@Override
							public void run() {
								txt.setText("Runnable");

							}
						};

						handler.post(r);

					} catch (Exception e) {
						Log.v("Error", e.toString());
					}
				}
			}
		});

		background.start();
	}

}