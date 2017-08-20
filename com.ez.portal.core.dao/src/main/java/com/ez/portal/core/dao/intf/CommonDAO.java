package com.ez.portal.core.dao.intf;

import java.io.Serializable;
import java.util.List;

import com.ez.portal.core.entity.AbstractEntity;

/**
 * @author azaz.akhtar
 *
 * @param <E>
 * @param <ID>
 */
public interface CommonDAO<E extends AbstractEntity, ID extends Serializable> {
    
	/**
     * @param entity
     * @return
     * @throws Exception
     */
    E add(E entity) throws Exception;
    
    /**
     * @param entity
     * @return
     * @throws Exception
     */
    E update(E entity) throws Exception;
    
    /**
     * @param entity
     * @return
     * @throws Exception
     */
    E addOrUpdate(E entity) throws Exception;
    
    /**
     * @param entityId
     * @return
     * @throws Exception
     */
    E get(ID entityId) throws Exception;
    
    /**
     * @return
     * @throws Exception
     */
    List<E> getAll() throws Exception;
    
    /**
     * @param entity
     * @return
     * @throws Exception
     */
    E delete(E entity) throws Exception;
    
}
