package com.ego.apps.commonshare;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.Logger;

public class Launch {
	public static void main(String[] args) throws Exception {
		Logger logger = Logger.getLogger(Launch.class);
		String webappDirLocation = "src/main/webapp/";
		Tomcat tomcat = new Tomcat();

		// The port that we should run on can be set into an environment
		// variable
		// Look for that variable and default to 8080 if it isn't there.
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		logger.info("Application port set to: " + webPort);
		tomcat.setPort(Integer.valueOf(webPort));

		tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		logger.info("configuring app with basedir: "
				+ new File("./" + webappDirLocation).getAbsolutePath());

		tomcat.start();
		logger.info("Application started.");
		tomcat.getServer().await();
	}
}
