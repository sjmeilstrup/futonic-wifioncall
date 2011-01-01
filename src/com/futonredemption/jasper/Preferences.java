package com.futonredemption.jasper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Preferences {
	public static class AutoToggleWifi {
		public static final String WhilePhoneOffHook = "PrefAutoToggleWifi_WhilePhoneOffHook";
		public static final String WhilePhoneCharging = "PrefAutoToggleWifi_WhilePhoneCharging";
		public static final String SuggestWifiOnDisconnect = "PrefAutoToggleWifi_SuggestWifiOnDisconnect";
		
		public static final String InternalSetLastWifiState = "PrefAutoToggleWifi_InternalSetLastWifiState";
		
		public static boolean getLastSetWifiState(final Context context) {
			return Preferences.getBoolean(context, Preferences.AutoToggleWifi.InternalSetLastWifiState, false);
		}
		
		public static void setLastSetWifiState(final Context context, boolean state) {
			final Editor editPref = Preferences.editPreferences(context);
			editPref.putBoolean(Preferences.AutoToggleWifi.InternalSetLastWifiState, state);
			editPref.commit();
		}
		
		public static boolean shouldToggleOnPhoneCall(final Context context) {
			return Preferences.getBoolean(context, Preferences.AutoToggleWifi.WhilePhoneOffHook, false);
		}
		
		public static boolean shouldToggleOnPhoneCharging(final Context context) {
			return Preferences.getBoolean(context, Preferences.AutoToggleWifi.WhilePhoneCharging, false);
		}
		
		public static boolean shouldSuggestWifiOnDisconnect(final Context context) {
			return Preferences.getBoolean(context, Preferences.AutoToggleWifi.SuggestWifiOnDisconnect, false);
		}
	}
	
	public static final Editor editPreferences(final Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).edit();
	}
	
	public static final SharedPreferences getPreferences(final Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public static boolean getBoolean(final Context context, final String name, boolean defaultValue) {
		final SharedPreferences prefs = getPreferences(context);
		return prefs.getBoolean(name, defaultValue);
	}
}
