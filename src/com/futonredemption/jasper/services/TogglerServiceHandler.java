package com.futonredemption.jasper.services;

import java.util.List;

import com.futonredemption.jasper.TogglerLogicFactory;
import com.futonredemption.jasper.scenarios.IScenarioListener;
import com.futonredemption.jasper.scenarios.Scenario;
import com.futonredemption.jasper.togglers.IResourceToggler;
import com.futonredemption.jasper.togglers.ResourceTogglerFactory;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

public class TogglerServiceHandler {

	private final Context context;
	public TogglerServiceHandler(final Context context) {
		this.context = context;
	}

	public void handleIntent(final Intent intent) {
		final String action = intent.getAction();
		
		if(action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
			handlePhoneStateChanged(intent);
		}
		else if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			handleConnectivityStateChanged(intent);
		}
		else if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
			handlePowerConnected();
		}
		else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
			handlePowerDisconnected();
		}
	}
	
	private void handlePowerDisconnected() {
		handleScenarioChanged();
	}

	private void handlePowerConnected() {
		handleScenarioChanged();
	}

	private void handleConnectivityStateChanged(Intent intent) {
		handleScenarioChanged();
	}

	private void handlePhoneStateChanged(Intent intent) {
		handleScenarioChanged();
	}

	private void handleScenarioChanged() {
		Scenario scenario = new Scenario(context);
		List<IScenarioListener> listeners = new TogglerLogicFactory(context).createList();
		for(IScenarioListener listener : listeners) {
			listener.onScenarioChanged(scenario);
		}
	}

	public void forceStartWifi() {
		ResourceTogglerFactory factory = new ResourceTogglerFactory(context);
		IResourceToggler wifiToggler = factory.getWifi();
		wifiToggler.enable();
	}
	
}
