package com.futonredemption.jasper.receivers;

import com.futonredemption.jasper.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IntentRelayReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Utility.startWifiToggleService(context, intent);
	}
}
