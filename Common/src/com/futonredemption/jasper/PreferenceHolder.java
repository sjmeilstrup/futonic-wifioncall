package com.futonredemption.jasper;

import org.beryl.util.Lazy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHolder extends Lazy<SharedPreferences> {

	private final Context context;
	
	public PreferenceHolder(final Context context) {
		this.context = context;
	}
	
	@Override
	public SharedPreferences onSet() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
