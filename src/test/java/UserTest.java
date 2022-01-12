import org.junit.jupiter.api.Test;
import rest.pas.restpas.Model.DTO.UserDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void createTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        UserDTO u = new UserDTO();
        u.setAccessLevel(1);
        u.setLogin("test");
        u.setName("test");
        target.path("/user").request().post(Entity.json(u));
        UserDTO u1 = target.path("/user").path("/test").request(MediaType.APPLICATION_JSON).get(UserDTO.class);
        assertNotNull(u1);
        assertEquals("test", u.getName());
    }

    @Test
    public void createNoUniqueTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        UserDTO u = new UserDTO();
        u.setAccessLevel(1);
        u.setLogin("test");
        u.setName("test");
        target.path("/user").request().post(Entity.json(u));
        ArrayList r1 = target.path("/user").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        target.path("/user").request().post(Entity.json(u));
        ArrayList r = target.path("/user").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        assertNotNull(r);
        assertEquals(r1.size(), r.size());
    }

    @Test
    public void getTest(){
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        UserDTO u = target.path("/user").path("/user2").request(MediaType.APPLICATION_JSON).get(UserDTO.class);
        assertNotNull(u);
        assertEquals("Lolek", u.getName());
    }

    @Test
    public void updateTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        UserDTO u = new UserDTO();
        u.setLogin("user1");
        u.setAccessLevel(1);
        u.setName("test");
        target.path("/user").request().put(Entity.json(u));
        UserDTO u1 = target.path("/user").path("/user1").request(MediaType.APPLICATION_JSON).get(UserDTO.class);
        assertNotNull(u);
        assertEquals("test", u1.getName());
    }

}
