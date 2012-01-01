package com.futonredemption.jasper.scenarios;

import com.futonredemption.jasper.SetOnceVariable;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;

public class Scenario {

	private final SetOnceVariable<Boolean> phoneOffHook = new SetOnceVariable<Boolean>() {
		@Override
		public Boolean onSetVariable() {
			TelephonyManager manager = getSystemService(Context.TELEPHONY_SERVICE);
			return manager.getCallState() != TelephonyManager.CALL_STATE_IDLE;
		}
	};
	
	private final SetOnceVariable<Boolean> batteryCharging = new SetOnceVariable<Boolean>() {
		
		@Override
		public Boolean onSetVariable() {
			final Intent intent = getBatteryState();
			final int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
			return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
		}
	};
	
	private final SetOnceVariable<Boolean> connectivity = new SetOnceVariable<Boolean>() {
		
		@Override
		public Boolean onSetVariable() {
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
		return this.phoneOffHook.getValue();
	}
	
	public boolean hasInternetConnection() {
		return this.connectivity.getValue();
	}
	
	public boolean isCharging() {
		return batteryCharging.getValue();
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
