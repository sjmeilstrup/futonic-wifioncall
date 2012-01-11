package com.futonredemption.jasper.togglers;

import com.futonredemption.jasper.SetOnceVariable;

import android.content.Context;
import android.content.pm.PackageManager;

abstract class AbstractResourceToggler implements IResourceToggler {

	private final Context context;
	private final PackageManager pm;
	
	public AbstractResourceToggler(final Context context) {
		this.context = context;
		this.pm = context.getPackageManager();
	}
	
	protected Context getContext() {
		return this.context;
	}
	
	public boolean isDisabled() {
		return ! isEnabled();
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getSystemService(String serviceName) {
		return (T)context.getSystemService(serviceName);
	}
	
	protected boolean hasFeature(final String featureName) {
		return pm.hasSystemFeature(featureName);
	}
	
	public class IsFeatureAvailableChecker extends SetOnceVariable<Boolean> {
		private final String featureName;
		public IsFeatureAvailableChecker(final String featureName) {
			this.featureName = featureName;
		}
		
		@Override
		public Boolean onSetVariable() {
			return hasFeature(featureName);
		}
	}
}
