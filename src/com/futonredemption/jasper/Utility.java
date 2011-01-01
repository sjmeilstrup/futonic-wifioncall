package com.futonredemption.jasper;

import com.futonredemption.jasper.services.WifiToggleService;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class Utility {

	public static NotificationManager getNotificationManager(final Context context) {
		return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	public static TelephonyManager getTelephonyManager(final Context context) {
		return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	}
	
	public static WifiManager getWifiManager(final Context context) {
		return (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	}
	
	public static ConnectivityManager getConnectivityManager(final Context context) {
		return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
	
	public static boolean isUserOnPhoneCall(final Context context) {
		final TelephonyManager telephone = getTelephonyManager(context);
		return telephone.getCallState() != TelephonyManager.CALL_STATE_IDLE;
	}
	
	public static void startWifiToggleService(final Context context, final Intent intent) {
		final Intent serviceStart = new Intent(context, WifiToggleService.class);
		serviceStart.putExtra(WifiToggleService.EXTRA_COMMAND, WifiToggleService.EXTRA_COMMAND_USEINTENT);
		serviceStart.putExtra(WifiToggleService.EXTRA_INTENT, intent);
		
		context.startService(serviceStart);
	}
	public static Intent getStartWifiIntent(final Context context) {
		final Intent serviceStart = new Intent(context, WifiToggleService.class);
		serviceStart.putExtra(WifiToggleService.EXTRA_COMMAND, WifiToggleService.EXTRA_COMMAND_FORCESTARTWIFI);
		return serviceStart;
	}
}
