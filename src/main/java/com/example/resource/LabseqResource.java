package com.example.resource;

import com.example.service.LabseqService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;

@Path("/labseq")
public class LabseqResource {

    @Inject
    LabseqService labseqService;

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSequence(@PathParam("n") String nString) {
        int n;

        try {
            n = Integer.parseInt(nString);
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if  (n < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            BigInteger value = labseqService.getSequence(n);
            return Response.ok(value).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
