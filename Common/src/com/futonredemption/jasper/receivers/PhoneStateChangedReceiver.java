package com.futonredemption.jasper.receivers;

import com.futonredemption.jasper.Utility;

import android.content.Context;
import android.content.Intent;

public class PhoneStateChangedReceiver extends AbstractTogglerReceiver {

	@Override
	protected boolean shouldToggleService(Context context, Intent intent) {
		return true;
	}
	
	@Override
	protected void startService(final Context context, final Intent intent) {
		super.startService(context, intent);
		Utility.startUpsidedownAutoSpeakerService(context, intent);
	}
}
