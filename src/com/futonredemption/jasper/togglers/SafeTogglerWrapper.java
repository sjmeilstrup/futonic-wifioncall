package com.futonredemption.jasper.togglers;

/** Applies safety business logic to prevent NullPointerExceptions for togglers that fail to load. */
class SafeTogglerWrapper extends AbstractResourceTogglerWrapper {

	public SafeTogglerWrapper(final IResourceToggler toggler) {
		super(toggler);
	}

	public boolean isSupported() {
		return toggler.isSupported();
	}

	public boolean isEnabled() {
		if(toggler.isSupported()) {
			return toggler.isEnabled();
		} else {
			return false;
		}
	}

	public boolean isDisabled() {
		if(toggler.isSupported()) {
			return toggler.isDisabled();
		} else {
			return true;
		}
	}

	public boolean enable() {
		if(toggler.isSupported() && toggler.isDisabled()) {
			return toggler.enable();
		} if(toggler.isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean disable() {
		if(toggler.isSupported() && toggler.isEnabled()) {
			return toggler.disable();
		} if(toggler.isDisabled()) {
			return true;
		} else {
			return true;
		}
	}


}
