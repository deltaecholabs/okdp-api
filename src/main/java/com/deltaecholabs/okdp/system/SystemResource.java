package com.deltaecholabs.okdp.system;

import com.deltaecholabs.okdp.configuration.AuthRoles;
import com.deltaecholabs.okdp.exception.ServiceException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.net.URI;
import java.util.Objects;

@Path("/systems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "system", description = "System Operations")
@AllArgsConstructor
@Slf4j
@RolesAllowed(AuthRoles.USER)
public class SystemResource {

    private final SystemService systemService;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get All Systems",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = System.class)
            )
    )
    @RolesAllowed({AuthRoles.SYSTEM_VIEW})
    public Response get() {
        return Response.ok(systemService.findAll()).build();
    }

    @GET
    @Path("/{systemId}")
    @APIResponse(
            responseCode = "200",
            description = "Get System by systemId",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = System.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "System does not exist for systemId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @RolesAllowed({AuthRoles.SYSTEM_VIEW})
    public Response getById(@Parameter(name = "systemId", required = true) @PathParam("systemId") Integer systemId) {
        return systemService.findById(systemId)
                .map(system -> Response.ok(system).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @APIResponse(
            responseCode = "201",
            description = "System Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = System.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid System",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "System already exists for systemId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @RolesAllowed({AuthRoles.SYSTEM_EDIT})
    public Response post(@NotNull @Valid System system, @Context UriInfo uriInfo) {
        systemService.save(system);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(system.getSystemId())).build();
        return Response.created(uri).entity(system).build();
    }

    @PUT
    @Path("/{systemId}")
    @APIResponse(
            responseCode = "204",
            description = "System updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = System.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid System",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "System object does not have systemId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Path variable systemId does not match System.systemId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "No System found for systemId provided",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @RolesAllowed({AuthRoles.SYSTEM_EDIT})
    public Response put(@Parameter(name = "systemId", required = true) @PathParam("systemId") Integer systemId, @NotNull @Valid System system) {
        if (!Objects.equals(systemId, system.getSystemId())) {
            throw new ServiceException("Path variable systemId does not match System.systemId");
        }
        systemService.update(system);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}