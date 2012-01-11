package com.futonredemption.jasper;

import java.util.ArrayList;
import java.util.List;

import com.futonredemption.jasper.scenarios.BluetoothScenarioListener;
import com.futonredemption.jasper.scenarios.ConnectivityChangedReceiverScenarioListener;
import com.futonredemption.jasper.scenarios.IScenarioListener;
import com.futonredemption.jasper.scenarios.PhoneStateChangedReceiverScenarioListener;
import com.futonredemption.jasper.scenarios.PowerStateChangedReceiverScenarioListener;
import com.futonredemption.jasper.scenarios.WifiScenarioListener;
import com.futonredemption.jasper.togglers.IResourceToggler;
import com.futonredemption.jasper.togglers.ResourceTogglerFactory;

import android.content.Context;

public class TogglerLogicFactory {

	private final Context context;
	private final ResourceTogglerFactory togglerFactory;
	
	public TogglerLogicFactory(final Context context) {
		this.context = context;
		this.togglerFactory = new ResourceTogglerFactory(context);
	}
	
	public List<IScenarioListener> createList() {
		List<IScenarioListener> result = new ArrayList<IScenarioListener>();
		
		result.add(createConnectivityChangedReceiverListener());
		result.add(createPhoneStateReceiverListener());
		result.add(createPowerStateReceiverListener());
		result.add(createWifiListener());
		result.add(createBluetoothListener());
		return result;
	}

	private IScenarioListener createBluetoothListener() {
		IResourceToggler toggler = togglerFactory.getBluetooth();
		return new BluetoothScenarioListener(context, toggler);
	}

	private IScenarioListener createWifiListener() {
		IResourceToggler toggler = togglerFactory.getWifi();
		return new WifiScenarioListener(context, toggler);
	}
	
	private IScenarioListener createConnectivityChangedReceiverListener() {
		IResourceToggler toggler = togglerFactory.getConnectivityChangedReceiver();
		return new ConnectivityChangedReceiverScenarioListener(context, toggler);
	}
	
	private IScenarioListener createPhoneStateReceiverListener() {
		IResourceToggler toggler = togglerFactory.getPhoneStateChangedReceiver();
		return new PhoneStateChangedReceiverScenarioListener(context, toggler);
	}

	private IScenarioListener createPowerStateReceiverListener() {
		IResourceToggler toggler = togglerFactory.getPowerStateChangedReceiver();
		return new PowerStateChangedReceiverScenarioListener(context, toggler);
	}

}
