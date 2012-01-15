package com.futonredemption.jasper.scenarios;

import android.content.Context;

import com.futonredemption.jasper.Preferences;
import com.futonredemption.jasper.togglers.IResourceToggler;

public class PhoneStateChangedReceiverScenarioListener implements IScenarioListener {
	private final IResourceToggler toggler;
	private final BehaviorHints wifiBehavior;
	private final BehaviorHints bluetoothBehavior;
	
	public PhoneStateChangedReceiverScenarioListener(final Context context, final IResourceToggler toggler) {
		this.toggler = toggler;
		this.wifiBehavior = new BehaviorHints(context, Preferences.Wifi.ToggleWhilePhoneOffHook, Preferences.Wifi.ToggleWhilePhoneCharging);
		this.bluetoothBehavior = new BehaviorHints(context, Preferences.Bluetooth.ToggleWhilePhoneOffHook, Preferences.Bluetooth.ToggleWhilePhoneCharging);
	}
	
	public void onScenarioChanged(final Scenario scenario) {
		if((wifiBehavior.allowToggleOnPhoneCall() || bluetoothBehavior.allowToggleOnPhoneCall()) || scenario.isPhoneOffHook()) {
			toggler.enable();
		} else {
			toggler.disable();
		}
	}
}
