package com.futonredemption.jasper.services;

import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

import com.futonredemption.jasper.R;
import com.futonredemption.jasper.Utility;
import com.futonredemption.jasper.togglers.BluetoothToggler;
import com.futonredemption.jasper.togglers.IPassiveToggler;
import com.futonredemption.jasper.togglers.WifiToggler;

public class TogglerServiceHandler {
	
	private static final int NOTIFYID_SUGGESTWIFI = 1;
	
	final Service service;
	final TogglerBundle togglers;
	
	static class TogglerBundle {
		public final WifiToggler Wifi;
		public final BluetoothToggler Bluetooth;
		
		private final Context context;
		private final ArrayList<IPassiveToggler> Togglers = new ArrayList<IPassiveToggler>();
		
		TogglerBundle(final Context context) {
			this.context = context;
			Wifi = new WifiToggler(context);
			Bluetooth = new BluetoothToggler(context);
			
			Togglers.add(Wifi);
			Togglers.add(Bluetooth);
		}
		
		public void passiveEnableOnCall() {
			for(IPassiveToggler toggler : Togglers) {
				if(toggler.allowPassiveToggleOnPhoneCall()) {
					toggler.enable();
				}
			}
		}
		
		public void passiveDisableOnCall() {
			final boolean isBatteryCharging = Utility.isBatteryCharging(context);

			for(IPassiveToggler toggler : Togglers) {
				if(toggler.allowPassiveToggleOnPhoneCall()) {
					if(isBatteryCharging && toggler.allowPassiveToggleOnCharging()) {
						/* Do nothing, hold open for charging. */
					} else {
						toggler.disable();
					}
				}
			}
		}
		
		public void passiveEnableOnCharging() {
			for(IPassiveToggler toggler : Togglers) {
				if(toggler.allowPassiveToggleOnCharging()) {
					toggler.enable();
				}
			}
		}
		
		public void passiveDisableOnCharging() {
			final boolean isUserOnPhoneCall = Utility.isUserOnPhoneCall(context);
			for(IPassiveToggler toggler : Togglers) {
				if(toggler.allowPassiveToggleOnCharging()) {
					if(isUserOnPhoneCall && toggler.allowPassiveToggleOnPhoneCall()) {
						/* Do nothing, hold open for phone call. */
					} else {
						toggler.disable();
					}
				}
			}
		}
	}

	public TogglerServiceHandler(final Service service) {
		this.service = service;
		this.togglers = new TogglerBundle(service);
	}
	
	public void forceStartWifi() {
		togglers.Wifi.enable();
		cancelSuggestWifiNotification();
	}
	
	private void cancelSuggestWifiNotification() {
		final NotificationManager notifier = Utility.getNotificationManager(service);
		notifier.cancel(NOTIFYID_SUGGESTWIFI);
	}
	
	public void handleIntent(final Intent intent) {
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
		togglers.passiveDisableOnCharging();
	}
	
	private void handlePowerConnected() {
		togglers.passiveEnableOnCharging();
	}
	
	private void handleConnectivityStateChanged(final Intent intent) {
		final boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
		
		if(noConnectivity) {
			if(Utility.isUserOnPhoneCall(service)) {
				
				if(togglers.Wifi.allowPassiveToggleOnPhoneCall()) {
					togglers.Wifi.enable();
				}
			}
			if(togglers.Wifi.allowSuggestOnDisconnect()) {
				displaySuggestWifiNotification();
			}
		}
		else {
			cancelSuggestWifiNotification();
		}
	}

	private void displaySuggestWifiNotification() {
		final NotificationManager notifier = Utility.getNotificationManager(service);
		
		final Notification suggestWifiNotification = new Notification();
		
		final Intent forceStartWifi = Utility.getStartTogglerServiceIntent(service);
		final CharSequence title = service.getString(R.string.notify_suggestwifi_title);
		final CharSequence description = service.getString(R.string.notify_suggestwifi_description);
		final CharSequence tickerText = service.getString(R.string.notify_suggestwifi_tickertext);
		
		suggestWifiNotification.icon = R.drawable.stat_notify_wifi_in_range;
		suggestWifiNotification.tickerText = tickerText;
		suggestWifiNotification.flags = Notification.FLAG_AUTO_CANCEL;
		
		final PendingIntent pendingStartWifiIntent = PendingIntent.getService(service, 0, forceStartWifi, PendingIntent.FLAG_UPDATE_CURRENT);
		suggestWifiNotification.setLatestEventInfo(service, title, description, pendingStartWifiIntent);

		notifier.notify(NOTIFYID_SUGGESTWIFI, suggestWifiNotification);
	}
	
	private void handlePhoneStateChanged(final Intent intent) {
		final String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		final boolean isPhoneOffHook = phoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK);
		final boolean isPhoneIdle = phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE);
		
		if(isPhoneIdle) {
			togglers.passiveDisableOnCall();
		}
		else if(isPhoneOffHook) {
			togglers.passiveEnableOnCall();
		}
	}
}
