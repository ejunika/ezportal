package com.ez.portal.core.util.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ez.portal.core.util.dao.intf.CommonDAO;
import com.ez.portal.shard.util.PortalHibernateUtil;

public abstract class CommonDAOimpl<E, ID extends Serializable> extends PortalHibernateUtil implements CommonDAO<E, ID> {

    private Class<E> entityClass;
    
    @SuppressWarnings("unchecked")
    public CommonDAOimpl() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Override
    public E add(E entity) {
        Session s = getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        
        s.save(entity);
        tx.commit();
        return entity;
    }

    @Override
    public E update(E entity) {
        getSessionFactory().openSession().update(entity);
        return entity;
    }

    @Override
    public E addOrUpdate(E entity) {
        getSessionFactory().openSession().saveOrUpdate(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(ID entityId) {
        return (E) getSessionFactory().openSession().get(entityClass, entityId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAll() {
        return getSessionFactory().openSession().createCriteria(entityClass).list();
    }

    @Override
    public E delete(E entity) {
        getSessionFactory().openSession().delete(entity);
        return entity;
    }

}
