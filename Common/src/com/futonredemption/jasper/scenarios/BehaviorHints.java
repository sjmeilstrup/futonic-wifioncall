package com.futonredemption.jasper.scenarios;

import org.beryl.util.Lazy;

import com.futonredemption.jasper.PreferenceHolder;

import android.content.Context;

public class BehaviorHints {

	private final String phoneOffHookHintPreference;
	private final String deviceChargingHintPreference;
	
	private final PreferenceHolder prefMan;
	
	private final Lazy<Boolean> phoneOffHookHint = new Lazy<Boolean>() {
		@Override
		public Boolean onSet() {
			return getPreferenceBoolean(phoneOffHookHintPreference);
		}
	};
	
	private final Lazy<Boolean> deviceChargingHint = new Lazy<Boolean>() {
		@Override
		public Boolean onSet() {
			return getPreferenceBoolean(deviceChargingHintPreference);
		}
	};
	
	
	public BehaviorHints(final Context context, String phoneOffHookHintPreference, String deviceChargingHintPreference) {
		this.phoneOffHookHintPreference = phoneOffHookHintPreference;
		this.deviceChargingHintPreference = deviceChargingHintPreference;
		this.prefMan = new PreferenceHolder(context);
	}
	
	public boolean allowToggleOnCharging() {
		return this.deviceChargingHint.get();
	}
	
	public boolean allowToggleOnPhoneCall() {
		return this.phoneOffHookHint.get();
	}
	
	protected boolean getPreferenceBoolean(String preferenceKey) {
		return prefMan.get().getBoolean(preferenceKey, false);
	}
}
