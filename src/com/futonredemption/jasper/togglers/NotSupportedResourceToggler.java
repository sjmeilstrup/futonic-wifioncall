package com.futonredemption.jasper.togglers;

/** Stub toggler for resources that are not supported or don't exist on the system. */
class NotSupportedResourceToggler implements IResourceToggler {

	public boolean isSupported() {
		return false;
	}

	public boolean isEnabled() {
		return false;
	}

	public boolean isDisabled() {
		return true;
	}

	public boolean enable() {
		return false;
	}

	public boolean disable() {
		return true;
	}
}
