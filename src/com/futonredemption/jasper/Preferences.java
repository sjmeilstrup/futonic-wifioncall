package com.futonredemption.jasper;

public class Preferences {
	
	public static class Categories {
		public static final String ToggleWifi = "PrefCategory_ToggleWifi";
		public static final String ToggleBluetooth = "PrefCategory_ToggleBluetooth";
		public static final String SuggestWifi = "PrefCategory_SuggestWifi";
	}
	
	public static class Bluetooth {
		public static final String ToggleWhilePhoneOffHook = "PrefBluetooth_ToggleWhilePhoneOffHook";
		public static final String ToggleWhilePhoneCharging = "PrefBluetooth_ToggleWhilePhoneCharging";
		public static final String InternalSetLastState = "PrefBluetooth_InternalSetLastBtState";
	}
	
	public static class Wifi {
		public static final String ToggleWhilePhoneOffHook = "PrefAutoToggleWifi_WhilePhoneOffHook";
		public static final String ToggleWhilePhoneCharging = "PrefWifi_ToggleWhilePhoneCharging";
		public static final String SuggestWifiOnDisconnect = "PrefWifi_SuggestOnDisconnect";
		
		public static final String InternalSetLastState = "PrefAutoToggleWifi_InternalSetLastWifiState";
	}
}
