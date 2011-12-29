package com.futonredemption.jasper.togglers;

import com.futonredemption.jasper.SetOnceVariable;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;

class BluetoothToggler extends AbstractResourceToggler {

	private final SetOnceVariable<Boolean> isBluetoothSupported = new IsFeatureAvailableChecker(PackageManager.FEATURE_BLUETOOTH);
	private final BluetoothAdapter adapter;
	
	public BluetoothToggler(final Context context) {
		super(context);
		this.adapter = BluetoothAdapter.getDefaultAdapter();
	}

	public boolean isSupported() {
		return adapter != null && isBluetoothSupported.getValue();
	}

	public boolean isEnabled() {
		final int state = adapter.getState();
		if(state == BluetoothAdapter.STATE_ON || state == BluetoothAdapter.STATE_TURNING_ON) {
			return true;
		} else {
			return false;
		}
	}

	public boolean enable() {
		return this.adapter.enable();
	}

	public boolean disable() {
		return this.adapter.disable();
	}
}
