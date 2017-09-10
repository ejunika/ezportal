package com.ez.portal.core.rest.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.entity.DBServer;
import com.ez.portal.core.entity.HibernateProperty;
import com.ez.portal.core.entity.UserSpace;
import com.ez.portal.core.response.PortalInstallResponse;
import com.ez.portal.core.rest.manager.AbstractServiceManager;
import com.ez.portal.core.rest.manager.intf.PortalInstallServiceManager;
import com.ez.portal.core.util.EntryStatus;

public class PortalInstallServiceManagerImpl extends AbstractServiceManager implements PortalInstallServiceManager {

	@Override
	public PortalInstallResponse install() {
		List<UserSpace> userSpaces = null;
		try {
			userSpaces = daoManager.getUserSpaceDAO().getAllUserSpaces();
			if (userSpaces != null && !userSpaces.isEmpty()) {

			} else {
				installDefaultTenant();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void initInstall() {

	}

	private Boolean installDefaultTenant() {
		UserSpace userSpace = new UserSpace();
		DBServer dbServer = null;
		Long dbServerId = 1l;
		try {
			dbServer = daoManager.getDbServerDAO().get(dbServerId);
			if (dbServer != null) {
				userSpace = daoManager.getUserSpaceDAO().createUserSpace(userSpace, dbServer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PortalInstallResponse install(String tenantName) {
		return null;
	}
	
	/**
     * @param userSpace
     * @return
     */
    public List<HibernateProperty> getHibernateProperties(UserSpace userSpace) {
        List<HibernateProperty> hibernateProperties = new ArrayList<>();
        hibernateProperties.add(new HibernateProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect", EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.session_factory_name", userSpace.getUserSpaceName() + "_HibernateSessionFactory", EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver", EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.url", getTenantJdbcUrl(userSpace), EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.username", "root", EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.password", "Admin", EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.shard_id", userSpace.getUserSpaceId().toString(), EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.hbm2ddl.auto", "update", EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.shard.enable_cross_shard_relationship_checks", "true", EntryStatus.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.show_sql", "true", EntryStatus.ACTIVE_ENTRY));
        return hibernateProperties;
    };
    
	public String getTenantJdbcUrl(UserSpace userSpace) {
		DBServer dbServer = null;
		String jdbcUrl = "jdbc:";
		if (userSpace != null) {
			dbServer = userSpace.getDbServer();
			switch (dbServer.getDbServerType()) {
			case 1:
				jdbcUrl += "mysql";
				break;
			default:
				jdbcUrl += "mysql";
				break;
			}
			jdbcUrl += "://" + dbServer.getDbServerIP() + ":" + dbServer.getDbServerPort() + "/"
					+ userSpace.getUserSpaceDisplayName() + "_PORTAL?";
		}
		return jdbcUrl;
	}

}
