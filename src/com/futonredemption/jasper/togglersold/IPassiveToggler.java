package com.futonredemption.jasper.togglersold;

public interface IPassiveToggler extends IResourceToggler {
	boolean allowPassiveToggleOnCharging();
	boolean allowPassiveToggleOnPhoneCall();
}
