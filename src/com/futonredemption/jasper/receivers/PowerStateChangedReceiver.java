package com.futonredemption.jasper.receivers;

import android.content.Context;
import android.content.Intent;

public class PowerStateChangedReceiver extends AbstractTogglerReceiver {

	@Override
	protected boolean shouldToggleService(Context context, Intent intent) {
		return true;
	}

}
