package vn.hackademics.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import vn.hackademics.model.User;
import vn.hackademics.dao.UserDAO;

@Path("/users")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public class UserResource {

    @GET
    public List<User> getAllUser() {
        UserDAO dao = new UserDAO();
        List users = dao.getAllUser();
        return users;
    }

    @GET
    @Path("/{id_us}")
    public List<User> getUser(@PathParam("id_us") int id_us) {
        UserDAO dao = new UserDAO();
        List userId = dao.getUser(id_us);
        return userId;
    }

    @POST
    @Path("/create")
    public Response addUser(User emp){
        emp.setEmail_us(emp.getEmail_us());
        emp.setPassword_us(emp.getPassword_us());
        emp.setFirst_name(emp.getFirst_name());
        emp.setLast_name(emp.getLast_name());
        emp.setDate_of_birth(emp.getDate_of_birth());
        emp.setProfile_picture(emp.getProfile_picture());
        emp.setName_school(emp.getName_school());
        UserDAO dao = new UserDAO();
        dao.addUser(emp);
        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id_us}")
    public Response updateUser(@PathParam("id_us") int id_us, User emp){
        UserDAO dao = new UserDAO();
        int count = dao.updateUser(id_us, emp);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id_us}")
    public Response deleteUser(@PathParam("id_us") int id_us){
        UserDAO dao = new UserDAO();
        int count = dao.deleteUser(id_us);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

}