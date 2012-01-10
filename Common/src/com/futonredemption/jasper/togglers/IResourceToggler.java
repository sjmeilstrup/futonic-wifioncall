package com.futonredemption.jasper.togglers;

/** Toggles a resource. */
public interface IResourceToggler {
	/** Indicates that the resource is available on the device. */
	boolean isSupported();
	
	/** Indicates that the resource is currently turned on. */
	boolean isEnabled();
	
	/** Indicates that the resource is currently turned off. */
	boolean isDisabled();
	
	/** Attempt to enable the resource. Returns true if successful or expects success. */
	boolean enable();
	
	/** Attempt to disable the resource. Returns true if successful or expects success. */
	boolean disable();
}
