package com.futonredemption.jasper.receivers;

import com.futonredemption.jasper.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

abstract class AbstractTogglerReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(shouldToggleService(context, intent)) {
			startService(context, intent);
		}
	}
	
	protected void startService(final Context context, final Intent intent) {
		Utility.startTogglerService(context, intent);
	}
	
	protected abstract boolean shouldToggleService(final Context context, final Intent intent);
}
