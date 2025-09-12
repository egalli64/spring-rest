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

import com.example.swr.m3.s5.EntityV2;

/**
 * HATEOAS helper to add link to a response entity
 */
@Component
public class AssemblerVersion1 extends RepresentationModelAssemblerSupport<EntityV2, HateoasDtoResponseV1> {

    public AssemblerVersion1() {
        super(HateoasV1Controller.class, HateoasDtoResponseV1.class);
    }

    @Override
    public HateoasDtoResponseV1 toModel(EntityV2 entity) {
        HateoasDtoResponseV1 dto = new HateoasDtoResponseV1(entity);

        dto.add(linkTo(methodOn(HateoasV1Controller.class).get(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(HateoasV1Controller.class).getAll(null)).withRel("coders"));
        dto.add(linkTo(methodOn(HateoasV1Controller.class).update(entity.getId(), null)).withRel("update"));

        return dto;
    }
}
