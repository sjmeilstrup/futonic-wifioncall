package com.futonredemption.jasper.scenarios;

import com.futonredemption.jasper.togglers.IResourceToggler;

public class WifiScenarioListener implements IScenarioListener {

	private final IResourceToggler wifiToggler;
	
	public WifiScenarioListener(IResourceToggler wifiToggler) {
		this.wifiToggler = wifiToggler;
	}
	
	public void onScenarioChanged(final Scenario scenario) {
		// TODO Auto-generated method stub
	}
}
