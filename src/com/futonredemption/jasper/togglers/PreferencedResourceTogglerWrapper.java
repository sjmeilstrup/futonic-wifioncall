package com.futonredemption.jasper.togglers;

import android.content.Context;

import com.futonredemption.jasper.PreferenceHolder;

/** Manages the toggling a wrapped resourced based on implied ownership.
 * Auto-toggling occurs if the app believes that it has taken control ownership by flag setting as to not
 * conflict with the user's perceived.
 * 
 * IE: If the app wants to turn off wifi but the user manually turned it on, the app won't turn it off.
*/
public class PreferencedResourceTogglerWrapper extends AbstractResourceTogglerWrapper {

	private final PreferenceHolder prefs;
	private final String statePreferenceName;
	
	public PreferencedResourceTogglerWrapper(final Context context, final IResourceToggler toggler, String statePreferenceName) {
		super(toggler);
		this.statePreferenceName = statePreferenceName;
		this.prefs = new PreferenceHolder(context);
	}

	public boolean isSupported() {
		return toggler.isSupported();
	}

	public boolean isEnabled() {
		return toggler.isEnabled();
	}

	public boolean isDisabled() {
		return toggler.isDisabled();
	}

	public boolean enable() {
		
		// Only enables if the resource is currently off and the mode save preference is disabled as well.
		boolean performedEnable = false;
		if(isDisabled()) {
			performedEnable = toggler.enable();
			setPreferenceValue(performedEnable);
		}
		
		return performedEnable;
	}

	public boolean disable() {
		boolean performedDisable = false;
		
		if(isEnabled()) {
			if(getPreferenceValue()) {
				performedDisable = disable();
				setPreferenceValue(! performedDisable);
			}
		}
		
		return performedDisable;
	}
	
	protected boolean getPreferenceValue() {
		return prefs.getValue().getBoolean(statePreferenceName, false);
	}
	
	protected void setPreferenceValue(boolean newValue) {
		prefs.getValue().edit().putBoolean(statePreferenceName, newValue).commit();
	}
}
