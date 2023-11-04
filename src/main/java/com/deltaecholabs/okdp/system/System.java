package com.deltaecholabs.okdp.system;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class System {

    private Integer systemId;

    @NotEmpty
    private String name;

}
