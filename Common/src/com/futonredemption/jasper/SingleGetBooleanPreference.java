package com.futonredemption.jasper;

import org.beryl.Lazy;

import android.content.Context;

public class SingleGetBooleanPreference extends Lazy<Boolean> {

	private final PreferenceHolder prefs;
	private final String key;
	private final boolean defValue;
	
	public SingleGetBooleanPreference(final Context context, String key, boolean defValue) {
		this(new PreferenceHolder(context), key, defValue);
	}
	
	public SingleGetBooleanPreference(final PreferenceHolder prefs, String key, boolean defValue) {
		this.prefs = prefs;
		this.key = key;
		this.defValue = defValue;
	}

	@Override
	public Boolean onSet() {
		return prefs.get().getBoolean(key, defValue);
	}
}
