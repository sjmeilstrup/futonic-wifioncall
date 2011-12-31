package com.futonredemption.jasper.activities;

import com.futonredemption.jasper.Preferences;
import com.futonredemption.jasper.R;
import com.futonredemption.jasper.togglersold.BluetoothToggler;
import com.futonredemption.jasper.togglersold.WifiToggler;

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
		
		final WifiToggler wifi = new WifiToggler(this);
		final BluetoothToggler bt = new BluetoothToggler(this);

		if(! wifi.isSupported()) {
			disablePreference(Preferences.Categories.SuggestWifi);
			disablePreference(Preferences.Categories.ToggleWifi);
		}
		
		if(! bt.isSupported()) {
			disablePreference(Preferences.Categories.ToggleBluetooth);
		}
	}

	private void disablePreference(String categoryKey) {
		final PreferenceScreen screen = this.getPreferenceScreen();
		final Preference preference = screen.findPreference(categoryKey);
		preference.setSelectable(false);
		preference.setEnabled(false);
	}
}
