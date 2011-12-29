package com.futonredemption.jasper.togglers;

public class PreferencedResourceTogglerWrapper extends AbstractResourceTogglerWrapper {

	public PreferencedResourceTogglerWrapper(final IResourceToggler toggler) {
		super(toggler);
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
		return toggler.enable();
	}

	public boolean disable() {
		return toggler.disable();
	}
}
