package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DB_SERVER")
@XmlRootElement(name = "dbServer")
public class DBServer extends AbstractEntity {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "DBServerIdGenerator")
    @GenericGenerator(name = "DBServerIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "DB_SERVER_ID")
	private Long dbServerId;
	
	@Column(name = "DB_SERVER_TYPE")
	private Byte dbServerType;
	
	@Column(name = "DB_SERVER_NAME")
	private String dbServerName;
	
	@Column(name = "DB_SERVER_IP")
	private String dbServerIP;
	
	@Column(name = "DB_SERVER_PORT")
	private Integer dbServerPort;
	
	@Column(name = "DB_SERVER_USERNAME")
	private String dbServerUsername;
	
	@Column(name = "DB_SERVER_PASSWORD")
	private String dbServerPassword;
	
	public DBServer() {
		super();
	}
	
	public DBServer(Byte dbServerType, String dbServerName, String dbServerIP, Integer dbServerPort,
			String dbServerUsername, String dbServerPassword) {
		super();
		this.dbServerType = dbServerType;
		this.dbServerName = dbServerName;
		this.dbServerIP = dbServerIP;
		this.dbServerPort = dbServerPort;
		this.dbServerUsername = dbServerUsername;
		this.dbServerPassword = dbServerPassword;
	}

	public Long getDbServerId() {
		return dbServerId;
	}

	public void setDbServerId(Long dbServerId) {
		this.dbServerId = dbServerId;
	}

	public Byte getDbServerType() {
		return dbServerType;
	}

	public void setDbServerType(Byte dbServerType) {
		this.dbServerType = dbServerType;
	}

	public String getDbServerName() {
		return dbServerName;
	}

	public void setDbServerName(String dbServerName) {
		this.dbServerName = dbServerName;
	}

	public String getDbServerIP() {
		return dbServerIP;
	}

	public void setDbServerIP(String dbServerIP) {
		this.dbServerIP = dbServerIP;
	}

	public Integer getDbServerPort() {
		return dbServerPort;
	}

	public void setDbServerPort(Integer dbServerPort) {
		this.dbServerPort = dbServerPort;
	}

	public String getDbServerUsername() {
		return dbServerUsername;
	}

	public void setDbServerUsername(String dbServerUsername) {
		this.dbServerUsername = dbServerUsername;
	}

	public String getDbServerPassword() {
		return dbServerPassword;
	}

	public void setDbServerPassword(String dbServerPassword) {
		this.dbServerPassword = dbServerPassword;
	}
	
}
