package com.futonredemption.jasper.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

public class TogglerService extends IntentService {
	public static final String EXTRA_INTENT = "EXTRA_INTENT";
	private static final String TAG = "WifiToggleService";
	
	public static final String EXTRA_COMMAND = "EXTRA_COMMAND";
	public static final String EXTRA_COMMAND_USEINTENT = "EXTRA_COMMAND_USEINTENT";
	public static final String EXTRA_COMMAND_FORCESTARTWIFI = "EXTRA_COMMAND_FORCESTARTWIFI";

	public TogglerService() {
		super(TAG);
	}
	public TogglerService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		final Bundle extras = intent.getExtras();
		final TogglerServiceHandler serviceHandler = new TogglerServiceHandler(this);

		if(extras != null) {
			final String command = extras.getString(EXTRA_COMMAND);
			
			if(command != null) {
				if(command.equals(EXTRA_COMMAND_USEINTENT)) {
					serviceHandler.handleIntent(); //(Intent)extras.getParcelable(EXTRA_INTENT));
				} else if(command.equals(EXTRA_COMMAND_FORCESTARTWIFI)) {
					serviceHandler.forceStartWifi();
				}
			}
		}
	}
}
