package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PORTAL_CONFIGURATION")
@XmlRootElement(name = "portalConfiguration")
public class PortalConfiguration extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "PortalConfigurationIdGenerator")
    @GenericGenerator(name = "PortalConfigurationIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "PORTAL_CONFIGURATION_ID")
	private Long portalConfigurationId;
	
	@Column(name = "PORTAL_CONFIGURATION_NAME")
	private String portalConfigurationName;
	
	@Column(name = "PORTAL_CONFIGURATION_DATA", length = 1000000000)
	private String portalConfigurationData;

	public Long getPortalConfigurationId() {
		return portalConfigurationId;
	}

	public void setPortalConfigurationId(Long portalConfigurationId) {
		this.portalConfigurationId = portalConfigurationId;
	}

	public String getPortalConfigurationName() {
		return portalConfigurationName;
	}

	public void setPortalConfigurationName(String portalConfigurationName) {
		this.portalConfigurationName = portalConfigurationName;
	}

	public String getPortalConfigurationData() {
		return portalConfigurationData;
	}

	public void setPortalConfigurationData(String portalConfigurationData) {
		this.portalConfigurationData = portalConfigurationData;
	}
	
}
