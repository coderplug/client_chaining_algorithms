
package client;

import chaining.AbstractChaining;
import data.Data;
import xml_service.XMLMarshallingService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class RESTClient implements Serializable {


    public RESTClient (){

    }
    public String send(Data data){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://185.80.130.228:8080/rest_chaining_algorithms_war_exploded/rest/post/chaining");
        //WebTarget target = client.target("http://localhost:8080/rest/post/chaining");
        Invocation.Builder builder = target.request(MediaType.APPLICATION_XML);
        Response response = builder.post(Entity.xml(data));
        String responseXML = response.readEntity(String.class);
        return responseXML;
    }
    public AbstractChaining sendPOSTRequest(Data data) {
        if (data == null ||
                data.getChainingType() == null ||
                data.getGoal() == null ||
                data.getFacts() == null ||
                data.getChainingType().isEmpty() ||
                data.getGoal().isEmpty()
                ) {
            return null;
        }
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://185.80.130.228:8080/rest_chaining_algorithms_war_exploded/rest/post/chaining");
        //WebTarget target = client.target("http://localhost:8080/rest/post/chaining");
        Invocation.Builder builder = target.request(MediaType.APPLICATION_XML);
        Response response = builder.post(Entity.xml(data));
        String responseXML = response.readEntity(String.class);

        XMLMarshallingService service = new XMLMarshallingService(AbstractChaining.class);

        AbstractChaining chaining = (AbstractChaining) service.unmarshal(responseXML);

        chaining.getResult().setData(data);
        return chaining;
    }
}
