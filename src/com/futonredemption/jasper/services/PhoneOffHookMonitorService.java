package com.futonredemption.jasper.services;

import org.beryl.app.ServiceBase;

import com.futonredemption.jasper.Debugging;
import com.futonredemption.jasper.scenarios.Scenario;

import android.content.Intent;
import android.os.IBinder;

public class PhoneOffHookMonitorService extends ServiceBase {

	@Override
	protected String getTag() {
		return "PhoneOffHookMonitor";
	}

	@Override
	protected int handleOnStartCommand(Intent intent, int flags, int startId) {
		final Scenario scenario = new Scenario(this);
		handleScenario(scenario);
		return START_REDELIVER_INTENT;
	}

	private void handleScenario(Scenario scenario) {
		if(scenario.isPhoneOffHook()) {
			beginMonitoring();
		} else { 
			stopMonitoring();
		}
	}

	private void stopMonitoring() {
		Debugging.log("Stopping, PhoneOffHookMonitorService.");
		// TODO Auto-generated method stub
		
		this.stopSelf();
	}

	private void beginMonitoring() {
		Debugging.log("STARTING, PhoneOffHookMonitorService.");
		// TODO Need some code here.
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
