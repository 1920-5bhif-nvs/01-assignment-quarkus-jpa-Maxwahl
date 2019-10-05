package at.htl.jpademo.rest;

import at.htl.jpademo.business.LoanFacade;
import at.htl.jpademo.model.Loan;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/loans")
public class LoanResource {

    @Inject
    LoanFacade loanFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoans(){
        List<Loan> loans = loanFacade.get();
        return Response.ok().entity(loans).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoans(@PathParam("id")long id){
        Loan loan = loanFacade.get(id);
        return Response.ok().entity(loan).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        Loan entity = loanFacade.get(id);
        if(entity != null){
            loanFacade.remove(entity);
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Loan entity){
        try {
            entity = loanFacade.save(entity);
        }catch(PersistenceException e){
            return Response.status(400).build();
        }
        return Response.ok().entity(entity).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(Loan entity){
        entity = loanFacade.update(entity);
        return Response.ok().entity(entity).build();
    }
}   