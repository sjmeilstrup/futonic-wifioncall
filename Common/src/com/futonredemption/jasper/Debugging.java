package com.futonredemption.jasper;

import org.beryl.diagnostics.Logger;

public class Debugging {

	public static void log(String message) {
		Logger.d("JasperTogglerService", message);
	}
}
