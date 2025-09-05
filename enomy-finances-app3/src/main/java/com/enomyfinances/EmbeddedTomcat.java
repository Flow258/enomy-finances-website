package com.enomyfinances;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbeddedTomcat {
    public static void main(String[] args) throws LifecycleException {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());

        System.out.println("Starting Tomcat on http://localhost:8080/");
        tomcat.start();
        tomcat.getServer().await();
    }
}

