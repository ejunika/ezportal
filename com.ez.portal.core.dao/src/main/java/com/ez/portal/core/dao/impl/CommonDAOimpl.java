package com.ez.portal.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ez.portal.core.dao.intf.CommonDAO;
import com.ez.portal.core.entity.AbstractEntity;
import com.ez.portal.shard.util.PortalHibernateUtil;

public abstract class CommonDAOimpl<E extends AbstractEntity, ID extends Serializable> extends PortalHibernateUtil
        implements CommonDAO<E, ID> {

    private Class<E> entityClass;
    
    private Boolean nonShard;

    /**
	 * @return the nonShard
	 */
	public Boolean getNonShard() {
		return nonShard;
	}

	/**
	 * @param nonShard the nonShard to set
	 */
	public void setNonShard(Boolean nonShard) {
		this.nonShard = nonShard;
	}

	@SuppressWarnings("unchecked")
    public CommonDAOimpl() {
		this.nonShard = false;
		this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
    
	public CommonDAOimpl(Boolean nonShard) {
    	this();
    	this.nonShard = nonShard;
    }

    @Override
    public E add(E entity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory(nonShard).openSession();
            transaction = session.beginTransaction();
            entity.setCreatedAt(new Date());
            entity.setUpdatedAt(new Date());
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public E update(E entity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory(nonShard).openSession();
            transaction = session.beginTransaction();
            entity.setUpdatedAt(new Date());
            entity.setUpdatedBy(getEzShardUtil().getActiveUser());
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public E addOrUpdate(E entity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory(nonShard).openSession();
            transaction = session.beginTransaction();
            entity.setUpdatedAt(new Date());
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(ID entityId) throws Exception {
        Session session = null;
        E entity = null;
        try {
            session = getSessionFactory(nonShard).openSession();
            entity = (E) session.get(entityClass, entityId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAll() throws Exception {
        Session session = null;
        List<E> entities = null;
        Criteria criteria = null;
        try {
            session = getSessionFactory(nonShard).openSession();
            criteria = session.createCriteria(entityClass);
            entities = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entities;
    }

    @Override
    public E delete(E entity) throws Exception {
        Session session = null;
        try {
            session = getSessionFactory(nonShard).openSession();
            session.delete(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

}
