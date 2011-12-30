package com.futonredemption.jasper.togglers;

import com.futonredemption.jasper.SetOnceVariable;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class ComponentToggler extends AbstractResourceToggler {

	private final PackageManager pm;
	private final ComponentName componentName;
	private final SetOnceVariable<Boolean> defaultEnablement;
	public ComponentToggler(final Context context, Class<? extends BroadcastReceiver> clazz) {
		super(context);
		this.pm = context.getPackageManager();
		this.componentName = new ComponentName(context, clazz);
		defaultEnablement = new SetOnceVariable<Boolean>() {

			@Override
			public Boolean onSetVariable() {
				boolean result = false;
				try {
					final ComponentInfo info = pm.getActivityInfo(componentName, 0);
					result = info.enabled;
				} catch (NameNotFoundException e) {
					result = false;
				}
				return result;
			}
		};
	}
	
	public boolean enable() {
		pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		return true;
	}
	
	public boolean disable() {
		pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
		return true;
	}

	public boolean isSupported() {
		return true;
	}

	public boolean isEnabled() {
		
		final int state = pm.getComponentEnabledSetting(componentName);
		
		switch(state) {
			case PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER:
			case PackageManager.COMPONENT_ENABLED_STATE_DISABLED: {
				return false;
			}
			case PackageManager.COMPONENT_ENABLED_STATE_ENABLED: {
				return true;
			}
			case PackageManager.COMPONENT_ENABLED_STATE_DEFAULT: {
				return defaultEnablement.getValue();
			}
		}
		
		return false;
	}
}
