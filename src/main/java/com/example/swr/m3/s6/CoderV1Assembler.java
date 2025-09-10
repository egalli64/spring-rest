/*
 * Spring Boot Web REST tutorial 
 * 
 * https://github.com/egalli64/spring-rest
 */
package com.example.swr.m3.s6;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.swr.m3.s5.VersionedCoder;

/**
 * HATEOAS helper to add link to a response entity
 */
@Component
public class CoderV1Assembler extends RepresentationModelAssemblerSupport<VersionedCoder, CoderResponseV1H> {

    public CoderV1Assembler() {
        super(CoderV1HCtrl.class, CoderResponseV1H.class);
    }

    @Override
    public CoderResponseV1H toModel(VersionedCoder entity) {
        CoderResponseV1H dto = new CoderResponseV1H(entity);

        dto.add(linkTo(methodOn(CoderV1HCtrl.class).get(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(CoderV1HCtrl.class).getAll(null)).withRel("coders"));
        dto.add(linkTo(methodOn(CoderV1HCtrl.class).update(entity.getId(), null)).withRel("update"));

        return dto;
    }
}
