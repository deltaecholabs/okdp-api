package com.deltaecholabs.okdp.system;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SystemRepository implements PanacheRepositoryBase<SystemEntity, Integer> {

}