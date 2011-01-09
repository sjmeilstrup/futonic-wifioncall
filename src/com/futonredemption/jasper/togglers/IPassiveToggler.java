package com.futonredemption.jasper.togglers;

public interface IPassiveToggler extends IResourceToggler {
	boolean allowPassiveToggleOnCharging();
	boolean allowPassiveToggleOnPhoneCall();
}
