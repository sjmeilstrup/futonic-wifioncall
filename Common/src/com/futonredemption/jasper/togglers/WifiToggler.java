package com.futonredemption.jasper.togglers;

import com.futonredemption.jasper.SetOnceVariable;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;

class WifiToggler extends AbstractResourceToggler {

	private final WifiManager wifiManager;
	private final SetOnceVariable<Boolean> isWifiSupported = new IsFeatureAvailableChecker(PackageManager.FEATURE_WIFI);
	
	public WifiToggler(final Context context) {
		super(context);
		wifiManager = getSystemService(Context.WIFI_SERVICE);
	}

	public boolean isSupported() {
		return isWifiSupported.getValue();
	}

	public boolean isEnabled() {
		return wifiManager.isWifiEnabled();
	}

	public boolean enable() {
		return wifiManager.setWifiEnabled(true);
	}

	public boolean disable() {
		return wifiManager.setWifiEnabled(false);
	}
}
