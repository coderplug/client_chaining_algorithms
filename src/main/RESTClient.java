
package main;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
@ManagedBean
@RequestScoped
public class RESTClient {


    public RESTClient (){

    }

/*    //public AbstractChaining sendPOSTRequest(main.Data data){
    public String sendPOSTRequest(main.Data data){

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/rest_chaining_algorithms_war_exploded/rest/post2");
        Response response = target.request().post(Entity.xml(data));
        XMLMarshallingService service = new XMLMarshallingService();
        AbstractChaining chaining = response.readEntity(AbstractChaining.class);
        //service.unmarshal(response.readEntity(String.class));
        return chaining.toString();//response.readEntity(AbstractChaining.class);
    }*/
    /*public Trace sendPOSTRequest(Data data) {
        if (data == null) {
            return new Trace();
        }
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/rest_chaining_algorithms_war_exploded/rest/post2");
        Invocation.Builder builder = target.request(MediaType.APPLICATION_XML);
        Response response = builder.post(Entity.xml(data));
        String responseXML = response.readEntity(String.class);
        StringReader reader = new StringReader(responseXML);

        Trace trace = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Trace.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            trace = (Trace) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return trace;*/

        public AbstractChaining sendPOSTRequest(Data data) {
        if (data == null ||
                data.getChainingType() == null ||
                data.getGoal() == null ||
                data.getFacts() == null ||
                data.getChainingType().isEmpty() ||
                data.getGoal().isEmpty()
                ) {
            return new AbstractChaining();
        }
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/rest_chaining_algorithms_war_exploded/rest/post");
        Invocation.Builder builder = target.request(MediaType.APPLICATION_XML);
        Response response = builder.post(Entity.xml(data));
        String responseXML = response.readEntity(String.class);
        StringReader reader = new StringReader(responseXML);

        AbstractChaining chaining = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AbstractChaining.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            chaining = (AbstractChaining) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        chaining.getResult().setData(data);
        return chaining;
    }
}
