import org.junit.jupiter.api.Test;
import rest.pas.restpas.Model.DTO.ResourceDTO;
import rest.pas.restpas.Model.Repositories.ResourceRepository;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceTest {

    @Inject
    ResourceRepository repository;

    @Test
    public void getTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        ResourceDTO r = target.path("/resource").path("/68a577ae-6863-4510-82ce-aadecadb736c").request(MediaType.APPLICATION_JSON).get(ResourceDTO.class);
        assertNotNull(r);
        assertEquals(1, r.getValueOfResource());
    }

    @Test
    public void getAllTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        ArrayList r = target.path("/resource").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        assertNotNull(r);
        assertEquals(5, r.size());
    }

    @Test
    public void createTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setValueOfResource(4);
        target.path("/resource").request(MediaType.APPLICATION_JSON).post(Entity.json(resourceDTO));
        ArrayList r = target.path("/resource").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        assertNotNull(r);
        assertEquals(6, r.size());
    }

    @Test
    public void updateTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(UUID.fromString("b40fe46b-ba62-498a-a1d3-5a7ca4feae1a"));
        resourceDTO.setValueOfResource(5);
        target.path("/resource").request(MediaType.APPLICATION_JSON).put(Entity.json(resourceDTO));
        ResourceDTO r1 = target.path("/resource").path("/b40fe46b-ba62-498a-a1d3-5a7ca4feae1a").request(MediaType.APPLICATION_JSON).get(ResourceDTO.class);
        assertNotNull(r1);
        assertEquals(5, r1.getValueOfResource());
    }

    @Test
    public void deleteTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        target.path("/resource").path("/f01221d3-c14b-4c40-adfb-509238ed26a4").request().delete();
        ArrayList r = target.path("/resource").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        assertNotNull(r);
        assertEquals(5, r.size());
    }
}
