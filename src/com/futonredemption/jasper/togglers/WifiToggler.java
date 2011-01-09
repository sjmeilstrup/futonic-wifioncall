package com.futonredemption.jasper.togglers;

import com.futonredemption.jasper.Preferences;
import com.futonredemption.jasper.Utility;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiToggler extends AbstractResourceToggler {

	private final WifiManager wifiManager;
	
	public WifiToggler(final Context context) {
		super(context, new AbstractResourceToggler.TogglePreferenceKeys(Preferences.Wifi.InternalSetLastState,
				Preferences.Wifi.ToggleWhilePhoneOffHook,
				Preferences.Wifi.ToggleWhilePhoneCharging));
		wifiManager = Utility.getWifiManager(context);
	}
	
	public void enable() {
		setTogglePreferenceValue(true);
		
		if(! isEnabled()) {
			wifiManager.setWifiEnabled(true);
		}
	}

	public void disable() {
		setTogglePreferenceValue(false);
		
		if(isEnabled()) {
			wifiManager.setWifiEnabled(false);
		}
	}

	public boolean isEnabled() {
		return wifiManager.isWifiEnabled();
	}

	public boolean isSupported() {
		return true;
	}

	public boolean allowSuggestOnDisconnect() {
		return getPrefBool(Preferences.Wifi.SuggestWifiOnDisconnect, false) && ! isEnabled();
	}
}
