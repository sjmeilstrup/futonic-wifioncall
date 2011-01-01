package com.futonredemption.jasper.services;

import com.futonredemption.jasper.R;
import com.futonredemption.jasper.Utility;
import com.futonredemption.jasper.WifiManagement;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class WifiToggleService extends IntentService {
	public static final String EXTRA_INTENT = "EXTRA_INTENT";
	private static final String TAG = "WifiToggleService";
	
	public static final String EXTRA_COMMAND = "EXTRA_COMMAND";
	public static final String EXTRA_COMMAND_USEINTENT = "EXTRA_COMMAND_USEINTENT";
	public static final String EXTRA_COMMAND_FORCESTARTWIFI = "EXTRA_COMMAND_FORCESTARTWIFI";
	
	private static final int NOTIFYID_SUGGESTWIFI = 1;
	
	
	public WifiToggleService() {
		super(TAG);
	}
	public WifiToggleService(String name) {
		super(name);
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		
		final Bundle extras = intent.getExtras();
		if(extras != null) {
			final String command = extras.getString(EXTRA_COMMAND);
			if(command != null) {
				if(command.equals(EXTRA_COMMAND_USEINTENT)) {
					handleInnerIntent((Intent)extras.getParcelable(EXTRA_INTENT));
				}
				else if(command.equals(EXTRA_COMMAND_FORCESTARTWIFI)) {
					forceStartWifi();
				}
			}
		}
	}
	
	private void forceStartWifi() {
		WifiManagement.turnOnWifi(this);
		cancelSuggestWifiNotification();
	}
	
	private void handleInnerIntent(Intent intent) {
		final String action = intent.getAction();
		
		if(action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
			handlePhoneStateChanged(intent);
		}
		else if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			handleConnectivityStateChanged(intent);
		}
		else if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
			handlePowerConnected();
		}
		else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
			handlePowerDisconnected();
		}
	}
	
	private void handlePowerDisconnected() {
		if(WifiManagement.allowPassiveToggleOnCharging(this)) {
			WifiManagement.turnOffWifi(this);
		}
	}
	
	private void handlePowerConnected() {
		if(WifiManagement.allowPassiveToggleOnCharging(this)) {
			WifiManagement.turnOnWifi(this);
		}
	}
	
	private void handleConnectivityStateChanged(final Intent intent) {
		final boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
		
		if(noConnectivity) {
			if(Utility.isUserOnPhoneCall(this)) {
				if(WifiManagement.allowPassiveToggleOnPhoneCall(this)) {
					WifiManagement.turnOnWifi(this);
				}
			}
			if(WifiManagement.allowSuggestWifiOnDisconnect(this)) {
				displaySuggestWifiNotification();
			}
		}
		else {
			cancelSuggestWifiNotification();
		}
	}
	
	private void cancelSuggestWifiNotification() {
		final NotificationManager notifier = Utility.getNotificationManager(this);
		notifier.cancel(NOTIFYID_SUGGESTWIFI);
	}

	private void displaySuggestWifiNotification() {
		final NotificationManager notifier = Utility.getNotificationManager(this);
		
		final Notification suggestWifiNotification = new Notification();
		
		final Intent forceStartWifi = Utility.getStartWifiIntent(this);
		final CharSequence title = getString(R.string.notify_suggestwifi_title);
		final CharSequence description = getString(R.string.notify_suggestwifi_description);
		final CharSequence tickerText = getString(R.string.notify_suggestwifi_tickertext);
		
		suggestWifiNotification.icon = R.drawable.stat_notify_wifi_in_range;
		suggestWifiNotification.tickerText = tickerText;
		suggestWifiNotification.flags = Notification.FLAG_AUTO_CANCEL;
		
		final PendingIntent pendingStartWifiIntent = PendingIntent.getService(this, 0, forceStartWifi, PendingIntent.FLAG_UPDATE_CURRENT);
		suggestWifiNotification.setLatestEventInfo(this, title, description, pendingStartWifiIntent);

		notifier.notify(NOTIFYID_SUGGESTWIFI, suggestWifiNotification);
	}
	
	private void handlePhoneStateChanged(final Intent intent) {
		final String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		final boolean isPhoneIdle = phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE);
		
		if(isPhoneIdle) {
			if(WifiManagement.allowPassiveToggleOnPhoneCall(this)) {
				WifiManagement.turnOffWifi(this);
			}
		}
		
		// Picking up the phone is handled in connectivity code path since we only change wifi state if the phone call killed 3g access.
	}
}
