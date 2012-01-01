package com.futonredemption.jasper.scenarios;

import com.futonredemption.jasper.PreferenceHolder;
import com.futonredemption.jasper.SetOnceVariable;

import android.content.Context;

public class BehaviorHints {

	private final String phoneOffHookHintPreference;
	private final String deviceChargingHintPreference;
	
	private final PreferenceHolder prefMan;
	
	private final SetOnceVariable<Boolean> phoneOffHookHint = new SetOnceVariable<Boolean>() {
		@Override
		public Boolean onSetVariable() {
			return getPreferenceBoolean(phoneOffHookHintPreference);
		}
	};
	
	private final SetOnceVariable<Boolean> deviceChargingHint = new SetOnceVariable<Boolean>() {
		@Override
		public Boolean onSetVariable() {
			return getPreferenceBoolean(deviceChargingHintPreference);
		}
	};
	
	
	public BehaviorHints(final Context context, String phoneOffHookHintPreference, String deviceChargingHintPreference) {
		this.phoneOffHookHintPreference = phoneOffHookHintPreference;
		this.deviceChargingHintPreference = deviceChargingHintPreference;
		this.prefMan = new PreferenceHolder(context);
	}
	
	public boolean allowToggleOnCharging() {
		return this.deviceChargingHint.getValue();
	}
	
	public boolean allowToggleOnPhoneCall() {
		return this.phoneOffHookHint.getValue();
	}
	
	protected boolean getPreferenceBoolean(String preferenceKey) {
		return prefMan.getValue().getBoolean(preferenceKey, false);
	}
}
