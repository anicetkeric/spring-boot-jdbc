package com.sample.bootlabs.controller;


import com.sample.bootlabs.model.Publisher;
import com.sample.bootlabs.service.PublisherService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller for managing {@link Publisher}.
 *
 * @author  @bootteam
 */
@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    private final Logger log = LoggerFactory.getLogger(PublisherController.class);

    private final PublisherService entityService;

 	public PublisherController(PublisherService entityService) {
		this.entityService = entityService;
	}

    /**
     * {@code POST  /publisher} : Create a new publisher.
     *
     * @param publisher the publisher to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new publisher.
     */
	@PostMapping()
	public ResponseEntity<Publisher> createPublisher(@RequestBody @Valid Publisher publisher) {
         log.debug("REST request to save Publisher : {}", publisher);
         return new ResponseEntity<>(entityService.create(publisher), HttpStatus.CREATED);
    }

   /**
     * {@code PUT  /publisher/{id}} : Updates an existing publisher.
     *
     * @param publisher the publisher to update.
     * @param id the id of the publisher to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated publisher,
     * or with status {@code 400 (Bad Request)} if the publisher is not valid,
     * or with status {@code 500 (Internal Server Error)} if the publisher couldn't be updated.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Publisher> updatePublisher(@Valid @RequestBody Publisher publisher, @PathVariable("id") Long id) {
        log.debug("REST request to update Publisher : {}", publisher);
        Publisher result = entityService.update(publisher, id);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /publisher} : get all the publishers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of publisher in body.
     */

    @GetMapping()
    public ResponseEntity<List<Publisher>> getAllPublisher() {
	    log.debug("REST request to get all publishers");
        List<Publisher> lst = entityService.getAll();

        return new ResponseEntity<>(lst,HttpStatus.OK);
    }

    /**
     * {@code GET  /publisher/:id} : get the "id" publisher.
     *
     * @param id the id of the publisher to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the publisher, or with status {@code 404 (Not Found)}.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Publisher> getOnePublisher(@PathVariable("id") Long id) {
        log.debug("REST request to get Publisher : {}", id);
        Publisher e = entityService.getOne(id);

        return new ResponseEntity<>(e, HttpStatus.OK);
    }

  /**
     * {@code DELETE  /publisher/:id} : delete the "id" publisher.
     *
     * @param id the id of the publisher to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable("id") Long id) {
        log.debug("REST request to delete Publisher : {}", id);
        entityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
