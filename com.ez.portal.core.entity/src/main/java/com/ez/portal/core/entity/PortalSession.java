package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PORTAL_SESSION")
@XmlRootElement(name = "portalSession")
@XmlAccessorType(XmlAccessType.FIELD)
public class PortalSession extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "PortalSessionIdGenerator")
    @GenericGenerator(name = "PortalSessionIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "PORTAL_SESSION_ID")
    private Long portalSessionId;

    @Column(name = "PORTAL_SESSION_STATUS")
    private Byte portalSessionStatus;
    
    @Column(name = "PORTAL_SESSION_DURATION")
    private Long portalSessionDuration;
    
    @Column(name = "PORTAL_SESSION_TOKEN")
    private String portalSessionToken;

    public Long getPortalSessionId() {
        return portalSessionId;
    }

    public void setPortalSessionId(Long portalSessionId) {
        this.portalSessionId = portalSessionId;
    }

    public Byte getPortalSessionStatus() {
        return portalSessionStatus;
    }

    public void setPortalSessionStatus(Byte portalSessionStatus) {
        this.portalSessionStatus = portalSessionStatus;
    }

    public Long getPortalSessionDuration() {
        return portalSessionDuration;
    }

    public void setPortalSessionDuration(Long portalSessionDuration) {
        this.portalSessionDuration = portalSessionDuration;
    }

    public String getPortalSessionToken() {
        return portalSessionToken;
    }

    public void setPortalSessionToken(String portalSessionToken) {
        this.portalSessionToken = portalSessionToken;
    }
    
}
