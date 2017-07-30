package com.ez.portal.core.util.rest;

import java.util.List;
import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.ez.portal.shard.util.EZShardUtil;

public class ShardChangerInterceptor extends AbstractPhaseInterceptor<Message> {

    Map<String, Object> headers = null;

    private EZShardUtil ezShardUtil;

    public EZShardUtil getEzShardUtil() {
        return ezShardUtil;
    }

    public void setEzShardUtil(EZShardUtil ezShardUtil) {
        this.ezShardUtil = ezShardUtil;
    }

    public ShardChangerInterceptor() {
        super(Phase.RECEIVE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleMessage(Message message) throws Fault {
        List<String> shardKeys = null;
        headers = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
        if (headers != null) {
            try {
                shardKeys = (List<String>) headers.get("US-KEY");
                if (shardKeys != null) {
                    getEzShardUtil().setShardKey(shardKeys.get(0));
                    getEzShardUtil().initSessionFactory(shardKeys);
                } else {
                    getEzShardUtil().initSessionFactory();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
