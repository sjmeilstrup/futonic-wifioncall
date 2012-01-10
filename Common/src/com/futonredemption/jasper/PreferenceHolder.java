package com.futonredemption.jasper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHolder extends SetOnceVariable<SharedPreferences> {

	private final Context context;
	
	public PreferenceHolder(final Context context) {
		this.context = context;
	}
	@Override
	public SharedPreferences onSetVariable() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
