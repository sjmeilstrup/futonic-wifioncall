package com.futonredemption.jasper;

import com.futonredemption.jasper.services.TogglerService;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;

public class Utility {

	public static NotificationManager getNotificationManager(final Context context) {
		return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	public static WifiManager getWifiManager(final Context context) {
		return (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	}
	
	public static TelephonyManager getTelephonyManager(final Context context) {
		return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	}
	
	public static boolean isUserOnPhoneCall(final Context context) {
		final TelephonyManager telephone = getTelephonyManager(context);
		return telephone.getCallState() != TelephonyManager.CALL_STATE_IDLE;
	}
	
	public static Intent getBatteryState(final Context context) {
		return context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	}
	public static boolean isBatteryCharging(final Context context) {
		final Intent intent = getBatteryState(context);
		final int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
		return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
	}
	
	public static void startTogglerService(final Context context, final Intent intent) {
		final Intent serviceStart = new Intent(context, TogglerService.class);
		serviceStart.putExtra(TogglerService.EXTRA_COMMAND, TogglerService.EXTRA_COMMAND_USEINTENT);
		serviceStart.putExtra(TogglerService.EXTRA_INTENT, intent);
		
		context.startService(serviceStart);
	}
	public static Intent getStartTogglerServiceIntent(final Context context) {
		final Intent serviceStart = new Intent(context, TogglerService.class);
		serviceStart.putExtra(TogglerService.EXTRA_COMMAND, TogglerService.EXTRA_COMMAND_FORCESTARTWIFI);
		return serviceStart;
	}
}
