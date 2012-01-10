package com.futonredemption.jasper.activities;

import com.futonredemption.jasper.Preferences;
import com.futonredemption.jasper.R;
import com.futonredemption.jasper.Utility;
import com.futonredemption.jasper.togglers.IResourceToggler;
import com.futonredemption.jasper.togglers.ResourceTogglerFactory;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class PreferencesActivity extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	
		addPreferencesFromResource(R.xml.pref_main);
		
		final ResourceTogglerFactory factory = new ResourceTogglerFactory(this);
		final IResourceToggler wifi = factory.getWifi();
		final IResourceToggler bt = factory.getBluetooth();

		if(! wifi.isSupported()) {
			disablePreference(Preferences.Categories.SuggestWifi);
			disablePreference(Preferences.Categories.ToggleWifi);
		}
		
		if(! bt.isSupported()) {
			disablePreference(Preferences.Categories.ToggleBluetooth);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		Utility.startTogglerService(this, getIntent());
	}
	
	private void disablePreference(String categoryKey) {
		final PreferenceScreen screen = this.getPreferenceScreen();
		final Preference preference = screen.findPreference(categoryKey);
		if(preference != null) {
			preference.setSelectable(false);
			preference.setEnabled(false);
			preference.setTitle(getText(R.string.feature_not_supported));
		}
	}
}
