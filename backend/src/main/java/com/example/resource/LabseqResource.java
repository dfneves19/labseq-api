package com.example.resource;

import com.example.service.LabseqService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.math.BigInteger;

@Path("/labseq")
@Produces(MediaType.APPLICATION_JSON)
public class LabseqResource {

    @Inject
    LabseqService labseqService;

    public record LabseqResponse (int n, String value){}

    @GET
    @Path("/{n}")
    @Operation(summary = "Get the labseq sequence of the value n",
            description = "Returns the value of the labseq sequence at a given positive integer index n")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Sequence value returned"),
            @APIResponse(responseCode = "400", description = "Invalid number, input was either a string or a negative number")
    })
    public Response getSequence(
            @Parameter(description = "Index of the sequence to return, must be grater than 0")
            @PathParam("n") String nString){

        int n;

        // Check if input is an integer
        try {
            n = Integer.parseInt(nString);
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("n must be a positive integer").build();
        }

        if  (n < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("n must be a positive integer").build();
        }

        BigInteger value = labseqService.getSequence(n);
        LabseqResponse response = new LabseqResponse(n,value.toString());
        return Response.ok(response).build();

    }
}
