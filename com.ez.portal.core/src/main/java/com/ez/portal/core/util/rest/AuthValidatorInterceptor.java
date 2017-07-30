package com.ez.portal.core.util.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.status.PortalSessionStatus;
import com.ez.portal.shard.util.EZShardUtil;

public class AuthValidatorInterceptor extends AbstractPhaseInterceptor<Message> {

    Map<String, Object> headers = null;

    private EZShardUtil ezShardUtil;

    public EZShardUtil getEzShardUtil() {
        return ezShardUtil;
    }

    public void setEzShardUtil(EZShardUtil ezShardUtil) {
        this.ezShardUtil = ezShardUtil;
    }

    public AuthValidatorInterceptor() {
        super(Phase.RECEIVE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleMessage(Message message) throws Fault {
        List<String> authTokens = null;
        headers = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
        Boolean validSession = false;
        if (headers != null) {
            try {
                authTokens = (List<String>) headers.get("AUTH-TOKEN");
                if (authTokens != null) {
                    validSession = checkPortalSession(authTokens.get(0));
                    if (!validSession) {
                        HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
                                .get(AbstractHTTPDestination.HTTP_RESPONSE);
                        response.setStatus(500);
                        response.getOutputStream()
                                .write("{\"status\": false, \"message\": \"Invalid session\"}".getBytes());
                        response.getOutputStream().flush();
                        message.getInterceptorChain().abort();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean checkPortalSession(String portalSessionToken) {
        Boolean activeSession = false;
        Session session = null;
        Criteria criteria = null;
        PortalSession portalSession = null;
        try {
            session = getEzShardUtil().getSessionFactory().openSession();
            criteria = session.createCriteria(PortalSession.class);
            criteria.add(Restrictions.eq("portalSessionToken", portalSessionToken));
            criteria.add(Restrictions.eq("portalSessionStatus", PortalSessionStatus.ACTIVE_SESSION));
            portalSession = (PortalSession) criteria.uniqueResult();
            if (portalSession != null) {
                activeSession = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return activeSession;
    }

}