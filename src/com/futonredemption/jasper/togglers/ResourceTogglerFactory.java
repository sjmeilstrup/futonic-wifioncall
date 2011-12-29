package com.futonredemption.jasper.togglers;

import org.beryl.app.AndroidVersion;

import android.content.Context;

public class ResourceTogglerFactory {

	private final Context context;
	public ResourceTogglerFactory(final Context context) {
		this.context = context;
	}
	
	public IResourceToggler getWifi() {
		return wrapToggler(new WifiToggler(context));
	}
	
	public IResourceToggler getBluetooth() {
		if(AndroidVersion.isEclairOrHigher()) {
			return wrapToggler(new BluetoothToggler(context));
		} else {
			return new NotSupportedResourceToggler();
		}
	}
	
	/** Wraps the raw toggler with standardized business logic. */
	protected IResourceToggler wrapToggler(IResourceToggler rawToggler) {
		return new PreferencedResourceTogglerWrapper(new SafeTogglerWrapper(rawToggler));
	}
}
