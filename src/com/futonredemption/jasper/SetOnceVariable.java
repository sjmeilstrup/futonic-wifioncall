package com.futonredemption.jasper;

public abstract class SetOnceVariable<T> {

	private boolean isSet = false;
	private T data;
	
	public synchronized T getValue() {
		
		if(! isSet) {
			data = onSetVariable();
			isSet = true;
		}
		
		return data;
	}
	
	public abstract T onSetVariable();
}
