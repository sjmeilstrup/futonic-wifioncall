package com.futonredemption.jasper.togglers;

public interface IResourceToggler {

	public void enable();
	public void disable();
	public boolean isEnabled();
	public boolean isSupported();
	
	/*
	private static boolean isWifiEnabled(final Context context) {
		final WifiManager wman = Utility.getWifiManager(context);
		return wman.isWifiEnabled();
	}

	private static boolean wasLastToSetWifiState(final Context context) {
		boolean currentWifiState = isWifiEnabled(context);
		boolean lastSetWifiState = Preferences.AutoToggleWifi.getLastSetWifiState(context);
		return currentWifiState == lastSetWifiState;
	}
	
	public static boolean allowPassiveToggleOnPhoneCall(final Context context) {
		return Preferences.AutoToggleWifi.shouldToggleOnPhoneCall(context) && WifiManagement.wasLastToSetWifiState(context);
	}
	
	public static boolean allowPassiveToggleOnCharging(final Context context) {
		return Preferences.AutoToggleWifi.shouldToggleOnPhoneCharging(context) && WifiManagement.wasLastToSetWifiState(context);
	}
	
	public static boolean allowSuggestWifiOnDisconnect(final Context context) {
		return Preferences.AutoToggleWifi.shouldSuggestWifiOnDisconnect(context) && ! WifiManagement.isWifiEnabled(context);
	}

	public static void turnOnWifi(final Context context) {
		setWifiState(context, true);
	}
	
	public static void turnOffWifi(final Context context) {
		setWifiState(context, false);
	}
	
	private static void setWifiState(final Context context, final boolean state) {
		final WifiManager wman = Utility.getWifiManager(context);
		wman.setWifiEnabled(state);
		Preferences.AutoToggleWifi.setLastSetWifiState(context, state);
	}
	*/
}
