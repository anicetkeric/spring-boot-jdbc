package com.sample.bootlabs.model;

import lombok.Builder;

/**
 * Data model class for "Author"
 *
 * @author @bootteam
 */
@Builder
public record Author(
        Long id,
        String lastname,
        String firstname) {
}
