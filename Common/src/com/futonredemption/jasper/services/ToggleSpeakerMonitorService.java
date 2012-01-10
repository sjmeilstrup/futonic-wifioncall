package com.futonredemption.jasper.services;

import org.beryl.app.ServiceBase;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ToggleSpeakerMonitorService extends ServiceBase {

	@Override
	protected String getTag() {
		return "ToggleSpeakerMonitorService";
	}

	@Override
	protected int handleOnStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
