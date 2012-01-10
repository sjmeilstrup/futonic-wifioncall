package com.futonredemption.jasper.receivers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class ConnectivityChangedReceiver extends AbstractTogglerReceiver {

	@Override
	protected boolean shouldToggleService(Context context, Intent intent) {
		final boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
		return noConnectivity;
	}
}
