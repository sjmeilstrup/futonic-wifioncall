package com.futonredemption.jasper.scenarios;

import android.content.Context;

import com.futonredemption.jasper.Preferences;
import com.futonredemption.jasper.togglers.IResourceToggler;

public class BluetoothScenarioListener implements IScenarioListener {

	private final IResourceToggler toggler;
	private final BehaviorHints behavior;
	
	public BluetoothScenarioListener(final Context context, final IResourceToggler toggler) {
		this.toggler = toggler;
		this.behavior = new BehaviorHints(context, Preferences.Bluetooth.ToggleWhilePhoneOffHook, Preferences.Bluetooth.ToggleWhilePhoneCharging);
	}
	
	public void onScenarioChanged(final Scenario scenario) {
		if(behavior.allowToggleOnCharging() && scenario.isCharging()) {
			toggler.enable();
		} else if(behavior.allowToggleOnPhoneCall() && scenario.isPhoneOffHook()) {
			toggler.enable();
		} else if(scenario.isPhoneOffHook()) {
			// Do nothing
		} else {
			toggler.disable();
		}
	}
}
