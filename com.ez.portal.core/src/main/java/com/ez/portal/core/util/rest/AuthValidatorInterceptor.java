package com.ez.portal.core.util.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.ez.portal.core.dao.manager.CoreDAOManager;
import com.ez.portal.shard.util.EZShardUtil;

public class AuthValidatorInterceptor extends AbstractPhaseInterceptor<Message> {

    Map<String, Object> headers = null;

    private EZShardUtil ezShardUtil;
    
    private CoreDAOManager coreDAOManager;

    public AuthValidatorInterceptor() {
        super(Phase.RECEIVE);
    }
    
    public CoreDAOManager getCoreDAOManager() {
        return coreDAOManager;
    }

    public void setCoreDAOManager(CoreDAOManager coreDAOManager) {
        this.coreDAOManager = coreDAOManager;
    }

    public EZShardUtil getEzShardUtil() {
        return ezShardUtil;
    }

    public void setEzShardUtil(EZShardUtil ezShardUtil) {
        this.ezShardUtil = ezShardUtil;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleMessage(Message message) throws Fault {
        List<String> authTokens = null;
        headers = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
        Boolean isValidPortalSession = false;
        if (headers != null) {
            try {
                authTokens = (List<String>) headers.get("AUTH-TOKEN");
                if (authTokens != null) {
                    isValidPortalSession = coreDAOManager.getPortalSessionDAOManager().isValidPortalSession(authTokens.get(0));
                    if (!isValidPortalSession) {
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

}