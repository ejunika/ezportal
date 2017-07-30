package com.ez.portal.core.util.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ez.portal.core.entity.AbstractEntity;
import com.ez.portal.core.util.dao.intf.CommonDAO;
import com.ez.portal.shard.util.PortalHibernateUtil;

public abstract class CommonDAOimpl<E extends AbstractEntity, ID extends Serializable> extends PortalHibernateUtil
        implements CommonDAO<E, ID> {

    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public CommonDAOimpl() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public E add(E entity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory().openSession();
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
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            entity.setUpdatedAt(new Date());
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
            session = getSessionFactory().openSession();
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
            session = getSessionFactory().openSession();
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
            session = getSessionFactory().openSession();
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
            session = getSessionFactory().openSession();
            session.delete(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

}
