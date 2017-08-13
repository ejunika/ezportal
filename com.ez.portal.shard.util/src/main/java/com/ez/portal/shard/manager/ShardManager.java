package com.ez.portal.shard.manager;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.shard.dao.intf.ShardDAO;
import com.ez.portal.shard.entity.UserSpace;
import com.ez.portal.shard.request.ShardRequest;
import com.ez.portal.shard.response.ShardResponse;
import com.ez.portal.shard.util.PortalHibernateUtil;

public class ShardManager {
    
    private PortalHibernateUtil portalHibernateUtil;
    
    private ShardDAO shardDAO;

    public ShardDAO getShardDAO() {
        return shardDAO;
    }

    public void setShardDAO(ShardDAO shardDAO) {
        this.shardDAO = shardDAO;
    }

    public PortalHibernateUtil getPortalHibernateUtil() {
        return portalHibernateUtil;
    }

    public void setPortalHibernateUtil(PortalHibernateUtil portalHibernateUtil) {
        this.portalHibernateUtil = portalHibernateUtil;
    }

    public ShardResponse createUserSpace(ShardRequest request) {
        List<UserSpace> userSpaces = new ArrayList<>();
        for (UserSpace userSpace : request.getUserSpaces()) {
            try {
                userSpaces.add(shardDAO.addUserSpace(userSpace));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public ShardResponse activateUserSpace(ShardRequest request) {
        portalHibernateUtil.initShard();
        return null;
    }
    
}
