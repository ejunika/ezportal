package com.ez.portal.core.util.dao.intf;

import java.io.Serializable;
import java.util.List;

import com.ez.portal.core.entity.AbstractEntity;

public interface CommonDAO<E extends AbstractEntity, ID extends Serializable> {
    E add(E entity);
    E update(E entity);
    E addOrUpdate(E entity);
    E get(ID entityId);
    List<E> getAll();
    E delete(E entity);
}
