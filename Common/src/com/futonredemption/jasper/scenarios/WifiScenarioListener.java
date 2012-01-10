package com.futonredemption.jasper.scenarios;

import android.content.Context;

import com.futonredemption.jasper.Preferences;
import com.futonredemption.jasper.togglers.IResourceToggler;

public class WifiScenarioListener implements IScenarioListener {

	private final IResourceToggler toggler;
	private final BehaviorHints behavior;
	
	public WifiScenarioListener(final Context context, final IResourceToggler toggler) {
		this.toggler = toggler;
		this.behavior = new BehaviorHints(context, Preferences.Wifi.ToggleWhilePhoneOffHook, Preferences.Wifi.ToggleWhilePhoneCharging);
	}
	
	public void onScenarioChanged(final Scenario scenario) {
		if(behavior.allowToggleOnCharging() && scenario.isCharging()) {
			toggler.enable();
		} else if(behavior.allowToggleOnPhoneCall() && scenario.isPhoneOffHookWithNoConnection()) {
			toggler.enable();
		} else {
			toggler.disable();
		}
	}
}
