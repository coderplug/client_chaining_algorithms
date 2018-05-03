
package client;

import chaining.AbstractChaining;
import data.Data;
import xml_service.XMLMarshallingService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.Map;

/*
 RESTClient,
 Input: Data object as XML
 Action: Sends and accepts information from REST server
 Result: Received information sent by REST server
 */

@ManagedBean //CDI takes care of this class lifetime
@RequestScoped //Lives only during request
public class RESTClient implements Serializable {

    private String responseXML;

    public RESTClient (){

    }

    public String getResponseXML() {
        return responseXML;
    }

    public void setResponseXML(String responseXML) {
        this.responseXML = responseXML;
    }

    public String send(Data data){

        //REST client, JAX-RS takes care of object creation
        Client client = ClientBuilder.newClient();

        //Sets REST server
        WebTarget target = client.target("http://185.80.130.228:8080/rest/post/chaining");
        //WebTarget target = client.target("http://localhost:8080/rest/post/chaining");

        //Creates request builder, which uses XML to communicate
        Invocation.Builder builder = target.request(MediaType.APPLICATION_XML);

        //Sends and receives data from REST server
        Response response = builder.post(Entity.xml(data));
        responseXML = response.readEntity(String.class);
        return responseXML;
    }
    public AbstractChaining sendPOSTRequest(Data data) {
        //Validation used to avoid empty mandatory field values
        if (data == null ||
                data.getChainingType() == null ||
                data.getGoal() == null ||
                data.getFacts() == null ||
                data.getChainingType().isEmpty() ||
                data.getGoal().isEmpty()
                ) {
            return null;
        }

        //REST client, JAX-RS takes care of object creation
        Client client = ClientBuilder.newClient();

        //Sets REST server location
        WebTarget target = client.target("http://185.80.130.228:8080/rest/post/chaining");
        //WebTarget target = client.target("http://localhost:8080/rest/post/chaining");

        //Creates request builder, which uses XML to communicate
        Invocation.Builder builder = target.request(MediaType.APPLICATION_XML);

        //Sends and receives data from REST server
        Response response = builder.post(Entity.xml(data));
        responseXML = response.readEntity(String.class);

        //Creates XML marshalling service which works with AbstractChaining class
        XMLMarshallingService service = new XMLMarshallingService(AbstractChaining.class);
        AbstractChaining chaining = (AbstractChaining) service.unmarshal(responseXML);

        //Data object is not sent by REST server so it is set here
        chaining.getResult().setData(data);

        return chaining;
    }
}
