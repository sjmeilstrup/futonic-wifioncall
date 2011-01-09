package com.futonredemption.jasper.togglers;

import org.beryl.app.AndroidVersion;

import com.futonredemption.jasper.Preferences;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

public class BluetoothToggler extends AbstractResourceToggler {

	private final IResourceToggler toggler;
	public BluetoothToggler(final Context context) {
		super(context, new AbstractResourceToggler.TogglePreferenceKeys(Preferences.Bluetooth.InternalSetLastState,
				Preferences.Bluetooth.ToggleWhilePhoneOffHook,
				Preferences.Bluetooth.ToggleWhilePhoneCharging));
		if(AndroidVersion.isEclairOrHigher()) {
			toggler = new EclairOrHigherBluetoothToggler();
		}
		else {
			toggler = new DonutOrLowerBluetoothToggler();
		}
	}
	
	public void enable() {
		setTogglePreferenceValue(true);
		
		if(! isEnabled()) {
			toggler.enable();
		}
	}

	public void disable() {
		setTogglePreferenceValue(false);
		
		if(isEnabled()) {
			toggler.disable();
		}
	}

	public boolean isEnabled() {
		return toggler.isEnabled();
	}
	
	public boolean isSupported() {
		return toggler.isSupported();
	}

	static class EclairOrHigherBluetoothToggler implements IResourceToggler {

		BluetoothAdapter adapter;
		
		EclairOrHigherBluetoothToggler() {
			adapter = BluetoothAdapter.getDefaultAdapter();
		}
		
		public void enable() {
			if(isSupported()) {
				adapter.enable();
			}
		}

		public void disable() {
			if(isSupported()) {
				adapter.disable();
			}
		}

		public boolean isEnabled() {
			if(isSupported()) {
				final int state = adapter.getState();
				if(state == BluetoothAdapter.STATE_ON || state == BluetoothAdapter.STATE_TURNING_ON) {
					return true;
				} else {
					return false;
				}
			}
			
			return false;
		}

		public boolean isSupported() {
			return adapter != null;
		}
	}
	
	static class DonutOrLowerBluetoothToggler implements IResourceToggler {

		public void enable() {
			// Not supported.
		}

		public void disable() {
			// Not supported.
		}

		public boolean isEnabled() {
			return false;
		}

		public boolean isSupported() {
			return false;
		}
	}
}
