package com.futonredemption.jasper.togglers;

import org.beryl.util.Lazy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;

class WifiToggler extends AbstractResourceToggler {

	private final WifiManager wifiManager;
	private final Lazy<Boolean> isWifiSupported = new IsFeatureAvailableChecker(PackageManager.FEATURE_WIFI);
	
	public WifiToggler(final Context context) {
		super(context);
		wifiManager = getSystemService(Context.WIFI_SERVICE);
	}

	public boolean isSupported() {
		return isWifiSupported.get();
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
