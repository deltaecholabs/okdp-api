package com.deltaecholabs.okdp;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<D, E> {

    List<D> toDomainList(List<E> entities);

    D toDomain(E entity);

    @InheritInverseConfiguration(name = "toDomain")
    E toEntity(D domain);

    void updateEntity(D domain, @MappingTarget E entity);

    void updateDomain(E entity, @MappingTarget D domain);

}
