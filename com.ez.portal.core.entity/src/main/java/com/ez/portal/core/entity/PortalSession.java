package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author azaz.akhtar
 *
 */
@Entity
@Table(name = "PORTAL_SESSION")
@XmlRootElement(name = "portalSession")
@XmlAccessorType(XmlAccessType.FIELD)
public class PortalSession extends AbstractEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @Id
    @GeneratedValue(generator = "PortalSessionIdGenerator")
    @GenericGenerator(name = "PortalSessionIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @Column(name = "PORTAL_SESSION_ID")
    private Long portalSessionId;

    /**
     * 
     */
    @Column(name = "PORTAL_SESSION_TOKEN")
    private String portalSessionToken;
    
    /**
     * 
     */
    @Column(name = "PORTAL_SESSION_DURATION")
    private Long portalSessionDuration;
    
    /**
     * 
     */
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    
    /**
     * 
     */
    public PortalSession() {
        super();
    }
    
    /**
     * @param portalSessionStatus
     * @param portalSessionToken
     * @param user
     */
    public PortalSession(Byte entryStatus, String portalSessionToken, User user) {
        super(user, user, entryStatus);
        this.portalSessionToken = portalSessionToken;
        this.user = user;
    }

    /**
     * @return
     */
    public Long getPortalSessionId() {
        return portalSessionId;
    }

    /**
     * @param portalSessionId
     */
    public void setPortalSessionId(Long portalSessionId) {
        this.portalSessionId = portalSessionId;
    }

    /**
     * @return
     */
    public String getPortalSessionToken() {
        return portalSessionToken;
    }

    /**
     * @param portalSessionToken
     */
    public void setPortalSessionToken(String portalSessionToken) {
        this.portalSessionToken = portalSessionToken;
    }

    /**
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return
     */
    public Long getPortalSessionDuration() {
        return portalSessionDuration;
    }

    /**
     * @param portalSessionDuration
     */
    public void setPortalSessionDuration(Long portalSessionDuration) {
        this.portalSessionDuration = portalSessionDuration;
    }
    
}
