package com.futonredemption.jasper.togglers;

import org.beryl.app.AndroidVersion;

import com.futonredemption.jasper.Utility;

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
	
	/** Wraps the raw toggler with standardized business logic. */
	protected IResourceToggler wrapToggler(IResourceToggler rawToggler, String type) {
		return new PreferencedResourceTogglerWrapper(this.context, new SafeTogglerWrapper(rawToggler), PREFIX_INTERNAL_SWITCH + type);
	}
}
