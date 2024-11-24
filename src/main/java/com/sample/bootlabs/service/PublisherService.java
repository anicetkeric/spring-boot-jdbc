package com.sample.bootlabs.service;


import com.sample.bootlabs.model.Publisher;

import java.util.List;

/**
 * Service Interface for managing {@link Publisher}.
*  @author @bootteam
 */
public interface PublisherService {
   
    /**
     * create new item for domain
     *
     * @param entity entity to save.
     * @return persisted entity Publisher
     */
    Publisher create(Publisher entity);

    /**
     * Update entity. Check before if existing data. If data is empty throw Exception
     *
     * @param entity domain
     * @param id entity id
     * @return Publisher
     */
    Publisher update(Publisher entity, Long id);

    /**
     * get Publisher by id. Can be return empty
     *
     * @param id the id of the entity.
     * @return Publisher
     */
    Publisher getOne(Long id) ;

    /**
     * Get all entities
     *
     * @return list of entities List<Publisher>
     */
    List<Publisher> getAll();

    /**
     * Delete record by id
     *  
     */
    void delete(Long id);

}
