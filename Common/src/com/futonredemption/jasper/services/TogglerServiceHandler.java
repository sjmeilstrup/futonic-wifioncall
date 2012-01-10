package com.futonredemption.jasper.services;

import java.util.List;

import com.futonredemption.jasper.TogglerLogicFactory;
import com.futonredemption.jasper.scenarios.IScenarioListener;
import com.futonredemption.jasper.scenarios.Scenario;
import com.futonredemption.jasper.togglers.IResourceToggler;
import com.futonredemption.jasper.togglers.ResourceTogglerFactory;

import android.content.Context;

public class TogglerServiceHandler {

	//private static final int NOTIFYID_SUGGESTWIFI = 1;
	private final Context context;
	
	public TogglerServiceHandler(final Context context) {
		this.context = context;
	}

	public void handleIntent() {
		handleScenarioChanged();
	}
	
	private void handleScenarioChanged() {
		Scenario scenario = new Scenario(context);
		List<IScenarioListener> listeners = new TogglerLogicFactory(context).createList();
		for(IScenarioListener listener : listeners) {
			listener.onScenarioChanged(scenario);
		}
	}

	public void forceStartWifi() {
		ResourceTogglerFactory factory = new ResourceTogglerFactory(context);
		IResourceToggler wifiToggler = factory.getWifi();
		wifiToggler.enable();
	}
	/*
	public void displaySuggestWifiNotification() {
		final NotificationManager notifier = Utility.getNotificationManager(context);
		
		final Notification suggestWifiNotification = new Notification();
		
		final Intent forceStartWifi = Utility.getStartTogglerServiceIntent(context);
		final CharSequence title = context.getString(R.string.notify_suggestwifi_title);
		final CharSequence description = context.getString(R.string.notify_suggestwifi_description);
		final CharSequence tickerText = context.getString(R.string.notify_suggestwifi_tickertext);
		
		suggestWifiNotification.icon = R.drawable.stat_notify_wifi_in_range;
		suggestWifiNotification.tickerText = tickerText;
		suggestWifiNotification.flags = Notification.FLAG_AUTO_CANCEL;
		
		final PendingIntent pendingStartWifiIntent = PendingIntent.getService(context, 0, forceStartWifi, PendingIntent.FLAG_UPDATE_CURRENT);
		suggestWifiNotification.setLatestEventInfo(context, title, description, pendingStartWifiIntent);

		notifier.notify(NOTIFYID_SUGGESTWIFI, suggestWifiNotification);
	}
	*/
}
