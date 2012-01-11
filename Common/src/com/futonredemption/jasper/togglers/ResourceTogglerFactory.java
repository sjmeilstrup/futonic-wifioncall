package com.futonredemption.jasper.togglers;

import org.beryl.app.AndroidVersion;

import com.futonredemption.jasper.Utility;
import com.futonredemption.jasper.receivers.ConnectivityChangedReceiver;
import com.futonredemption.jasper.receivers.PhoneStateChangedReceiver;
import com.futonredemption.jasper.receivers.PowerStateChangedReceiver;

import android.Manifest;
import android.content.Context;

public class ResourceTogglerFactory {

	private static final String PREFIX_INTERNAL_SWITCH = "com.futonredemption.jasper.internal.autoswitch.";
	private static final String TYPE_WIFI = "wifi";
	private static final String TYPE_BLUETOOTH = "bluetooth";
	
	private final Context context;
	public ResourceTogglerFactory(final Context context) {
		this.context = context;
	}
	
	public IResourceToggler getWifi() {
		if(Utility.hasPermission(context, Manifest.permission.CHANGE_WIFI_STATE)) {
			return wrapToggler(new WifiToggler(context), TYPE_WIFI);
		} else {
			return new NotSupportedResourceToggler();
		}
	}
	
	public IResourceToggler getBluetooth() {
		if(AndroidVersion.isEclairOrHigher() && Utility.hasPermission(context, Manifest.permission.BLUETOOTH_ADMIN)) {
			return wrapToggler(new BluetoothToggler(context), TYPE_BLUETOOTH);
		} else {
			return new NotSupportedResourceToggler();
		}
	}
	
	public IResourceToggler getConnectivityChangedReceiver() {
		if(Utility.hasPermission(context, Manifest.permission.CHANGE_WIFI_STATE)) {
			return new ComponentToggler(context, ConnectivityChangedReceiver.class);
		} else {
			return new NotSupportedResourceToggler();
		}
	}
	
	public IResourceToggler getPhoneStateChangedReceiver() {
		// TODO: Only return when using Telephony services?
		return new ComponentToggler(context, PhoneStateChangedReceiver.class);
	}
	
	public IResourceToggler getPowerStateChangedReceiver() {
		return new ComponentToggler(context, PowerStateChangedReceiver.class);
	}
	
	/** Wraps the raw toggler with standardized business logic. */
	protected IResourceToggler wrapToggler(IResourceToggler rawToggler, String type) {
		return new PreferencedResourceTogglerWrapper(this.context, new SafeTogglerWrapper(rawToggler), PREFIX_INTERNAL_SWITCH + type);
	}
}
