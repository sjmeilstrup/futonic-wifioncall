package com.futonredemption.jasper.togglers;

abstract class AbstractResourceTogglerWrapper implements IResourceToggler {

	protected final IResourceToggler toggler;
	
	public AbstractResourceTogglerWrapper(final IResourceToggler toggler) {
		this.toggler = toggler;
	}
}
