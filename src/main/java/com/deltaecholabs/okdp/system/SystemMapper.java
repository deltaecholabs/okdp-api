package com.deltaecholabs.okdp.system;

import com.deltaecholabs.okdp.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface SystemMapper extends BaseMapper<System, SystemEntity> {
    
}
