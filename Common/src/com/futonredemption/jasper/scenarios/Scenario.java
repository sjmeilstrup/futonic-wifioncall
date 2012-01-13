package com.futonredemption.jasper.scenarios;

import org.beryl.util.Lazy;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;

public class Scenario {

	private final Lazy<Boolean> phoneOffHook = new Lazy<Boolean>() {
		@Override
		public Boolean onSet() {
			TelephonyManager manager = getSystemService(Context.TELEPHONY_SERVICE);
			return manager.getCallState() != TelephonyManager.CALL_STATE_IDLE;
		}
	};
	
	private final Lazy<Boolean> batteryCharging = new Lazy<Boolean>() {
		
		@Override
		public Boolean onSet() {
			final Intent intent = getBatteryState();
			final int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
			return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
		}
	};
	
	private final Lazy<Boolean> connectivity = new Lazy<Boolean>() {
		
		@Override
		public Boolean onSet() {
			ConnectivityManager conmgr = getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = conmgr.getActiveNetworkInfo();
			if(info == null) {
				return false;
			} else {
				return info.isConnectedOrConnecting();
			}
		}
	};
	
	private final Context context;
	
	public Scenario(final Context context) {
		this.context = context;
	}
	
	public boolean isPhoneOffHook() {
		return this.phoneOffHook.get();
	}
	
	public boolean hasInternetConnection() {
		return this.connectivity.get();
	}
	
	public boolean isCharging() {
		return batteryCharging.get();
	}
	
	public boolean isPhoneOffHookWithNoConnection() {
		return isPhoneOffHook() && ! hasInternetConnection();
	}
	
	protected Intent getBatteryState() {
		return context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getSystemService(String serviceName) {
		return (T)context.getSystemService(serviceName);
	}
}
