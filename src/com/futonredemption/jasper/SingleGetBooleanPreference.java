package com.futonredemption.jasper;

import android.content.Context;

public class SingleGetBooleanPreference extends SetOnceVariable<Boolean> {

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
	public Boolean onSetVariable() {
		return prefs.getValue().getBoolean(key, defValue);
	}
}
