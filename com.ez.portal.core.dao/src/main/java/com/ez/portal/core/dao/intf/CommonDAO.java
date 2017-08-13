package com.ez.portal.core.dao.intf;

import java.io.Serializable;
import java.util.List;

import com.ez.portal.core.entity.AbstractEntity;

public interface CommonDAO<E extends AbstractEntity, ID extends Serializable> {
    E add(E entity) throws Exception;
    E update(E entity) throws Exception;
    E addOrUpdate(E entity) throws Exception;
    E get(ID entityId) throws Exception;
    List<E> getAll() throws Exception;
    E delete(E entity) throws Exception;
}
