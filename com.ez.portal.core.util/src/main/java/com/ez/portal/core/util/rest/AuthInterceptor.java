package com.ez.portal.core.util.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.ez.portal.shard.util.EZShardUtil;

public class AuthInterceptor extends AbstractPhaseInterceptor<Message> {

    Map<String, Object> headers = null;

    private EZShardUtil ezShardUtil;

    public AuthInterceptor() {
        super(Phase.RECEIVE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleMessage(Message message) throws Fault {
        List<String> shardKeys = null;
        headers = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
        try {
            shardKeys = (List<String>) headers.get("US-KEY");
            if (shardKeys != null && !shardKeys.isEmpty()) {
                getEzShardUtil().initSessionFactory(shardKeys);
            } else {
                getEzShardUtil().initSessionFactory();
//                HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
//                        .get(AbstractHTTPDestination.HTTP_RESPONSE);
//                response.setStatus(500);
//                response.getOutputStream().write("{\"Error\": \"Invalid Request\"}".getBytes());
//                response.getOutputStream().flush();
//                message.getInterceptorChain().abort();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EZShardUtil getEzShardUtil() {
        return ezShardUtil;
    }

    public void setEzShardUtil(EZShardUtil ezShardUtil) {
        this.ezShardUtil = ezShardUtil;
    }

}
