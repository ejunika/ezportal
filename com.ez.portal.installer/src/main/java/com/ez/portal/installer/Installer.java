package com.ez.portal.installer;

import org.flywaydb.core.Flyway;

public class Installer {
    
    private Flyway flyway;
    
    public Flyway getFlyway() {
        return flyway;
    }

    public void setFlyway(Flyway flyway) {
        this.flyway = flyway;
    }

    public void install() {
        flyway.setDataSource("jdbc:mysql://localhost:3306/PORTAL", "root", "Admin");
        flyway.setBaselineOnMigrate(true);
        int affectedRowCount = flyway.migrate();
        flyway.info().toString();
        System.out.println(affectedRowCount);
        System.out.println(flyway.info().toString());
    }
    
}
