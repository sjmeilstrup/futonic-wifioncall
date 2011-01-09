package com.futonredemption.jasper.togglers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

abstract class AbstractResourceToggler implements IResourceToggler, IPassiveToggler {

	private final Context context;
	
	private final TogglePreferenceKeys preferenceKeys;
	
	static class TogglePreferenceKeys {
		public final String Toggle;
		public final String ToggleOnPhoneCall;
		public final String ToggleOnCharging;
		
		TogglePreferenceKeys(String toggle, String toggleOnPhoneCall, String toggleOnCharging) {
			Toggle = toggle;
			ToggleOnPhoneCall = toggleOnPhoneCall;
			ToggleOnCharging = toggleOnCharging;
		}
	}
	
	AbstractResourceToggler(Context context, TogglePreferenceKeys preferenceKeys) {
		this.context = context;
		this.preferenceKeys = preferenceKeys;
	}
	
	public boolean allowPassiveToggle() {
		if(this.isSupported()) {
			final boolean currentState = this.isEnabled();
			final boolean lastSetState = this.getTogglePreferenceValue();
			return currentState == lastSetState;
		}
		return false;
	}

	protected boolean getTogglePreferenceValue() {
		return getPrefBool(preferenceKeys.Toggle, true);
	}
	
	protected boolean getPrefBool(final String key, final boolean defaultValue) {
		final SharedPreferences prefs = getPreferences();
		return prefs.getBoolean(key, defaultValue);
	}
	
	protected void setTogglePreferenceValue(final boolean state) {
		final Editor editPref = getPreferenceEditor();
		editPref.putBoolean(preferenceKeys.Toggle, state);
		editPref.commit();
	}
	
	protected Context getContext() {
		return context;
	}

	private final SharedPreferences getPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public final Editor getPreferenceEditor() {
		return getPreferences().edit();
	}

	public boolean allowPassiveToggleOnPhoneCall() {
		return getAllowPassiveOn(preferenceKeys.ToggleOnPhoneCall);
	}
	
	public boolean allowPassiveToggleOnCharging() {
		return getAllowPassiveOn(preferenceKeys.ToggleOnCharging);
	}
	
	protected boolean getAllowPassiveOn(String key) {
		return getPrefBool(key, false) && allowPassiveToggle();
	}
}
