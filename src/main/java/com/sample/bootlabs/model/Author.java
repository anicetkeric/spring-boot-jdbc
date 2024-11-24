package com.sample.bootlabs.model;

/**
 * Data model class for "Author"
 *
 * @author @bootteam
 */
public record Author(
        Long id,
        String lastname,
        String firstname) {
}
