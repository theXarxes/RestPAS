import org.junit.jupiter.api.Test;
import rest.pas.restpas.Model.DTO.ResAllMarkerDTO;
import rest.pas.restpas.Model.DTO.ResourceDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AllocationTest {

    @Test
    public void allocateTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        ResAllMarkerDTO resAllMarkerDTO = new ResAllMarkerDTO();
        resAllMarkerDTO.setDelay(0);
        resAllMarkerDTO.setUser("user2");
        ArrayList r1 = target.path("/allocate").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        target.path("/allocate/add").path("/68a577ae-6863-4510-82ce-aadecadb736c").request(MediaType.APPLICATION_JSON).post(Entity.json(resAllMarkerDTO));
        ArrayList r = target.path("/allocate").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        assertNotNull(r);
        assertEquals(r1.size() + 1, r.size());
    }

    @Test
    public void allocateAllocatedTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        ResAllMarkerDTO resAllMarkerDTO = new ResAllMarkerDTO();
        resAllMarkerDTO.setDelay(0);
        resAllMarkerDTO.setUser("user2");
        ArrayList r2 = target.path("/allocate").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        target.path("/allocate/add").path("/71900926-0ab8-454f-abba-cf49a73ef503").request(MediaType.APPLICATION_JSON).post(Entity.json(resAllMarkerDTO));
        ArrayList r1 = target.path("/allocate").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        assertEquals(r2.size() + 1 , r1.size());
        target.path("/allocate/add").path("/71900926-0ab8-454f-abba-cf49a73ef503").request(MediaType.APPLICATION_JSON).post(Entity.json(resAllMarkerDTO));
        ArrayList r = target.path("/allocate").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        assertNotNull(r);
        assertEquals(r1.size() , r.size());
    }

    @Test
    public void changeAllocateResourceTest() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api");
        ResAllMarkerDTO resAllMarkerDTO = new ResAllMarkerDTO();
        resAllMarkerDTO.setDelay(0);
        resAllMarkerDTO.setUser("user2");
        ArrayList r1 = target.path("/allocate").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        target.path("/allocate/add").path("/185fa974-fb7c-4d57-bf53-51ef5fd9563e").request(MediaType.APPLICATION_JSON).post(Entity.json(resAllMarkerDTO));
        ArrayList r = target.path("/allocate").request(MediaType.APPLICATION_JSON).get(ArrayList.class);
        assertNotNull(r);
        assertEquals(r1.size() + 1, r.size());
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(UUID.fromString("185fa974-fb7c-4d57-bf53-51ef5fd9563e"));
        resourceDTO.setValueOfResource(4);
        target.path("/resource").request(MediaType.APPLICATION_JSON).put(Entity.json(resourceDTO));
        ResourceDTO r3 = target.path("/resource").path("/185fa974-fb7c-4d57-bf53-51ef5fd9563e").request(MediaType.APPLICATION_JSON).get(ResourceDTO.class);
        assertNotNull(r3);
        assertEquals(3, r3.getValueOfResource());
    }
}
