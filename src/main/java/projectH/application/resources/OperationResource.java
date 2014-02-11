package projectH.application.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import projectH.application.responses.ExceptionDTO;
import projectH.application.responses.OperationDTO;
import projectH.domain.operation.InvalidOperationException;

@Produces("application/json")
@Consumes("application/json")
@Path("/interventions")
public class OperationResource {

    private static final String MISSING_INFORMATION = "INT001";

    @POST
    public Response createOperation(@Context UriInfo uri, OperationDTO dto) {
        try {
            // Operation operation = dto.toOperation();
            // TODO I assume there are some missing calls loll

            return Response.created(uri.getRequestUri()).build();
        } catch (InvalidOperationException e) {
            ExceptionDTO exception = new ExceptionDTO(MISSING_INFORMATION, e.getMessage());

            return Response.status(Status.BAD_REQUEST).entity(exception).build();
        }
    }
}
