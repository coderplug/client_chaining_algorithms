
package client;

import chaining.AbstractChaining;
import data.Data;
import xml_service.XMLMarshallingService;

//CDI importavimai
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

//JAX-RS importavimai
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.Serializable;

/*
 RESTClient,
 Įeiga: Duomenų objektas XML formatu
 Veiksmas: Siųsti ir priimti informaciją iš REST serverio
 Rezultatas: Priimta ir apdorota REST serverio informacija
 */

//CDI pasirūpina klasės gyvavimo ciklu
@ManagedBean
//Gyvavimo ciklas trunka užklausą
@RequestScoped
public class RESTClient implements Serializable {

    //Atsako XML, naudojamas kliento interfeise
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

        //REST klientas, JAX-RS pasirūpina jo kūrimu
        Client client = ClientBuilder.newClient();

        //Nustatomas REST klientas
        //WebTarget target = client.target("http://185.80.130.228:8080/rest/post/chaining");
        WebTarget target = client.target("http://localhost:8080/rest/post/chaining");

        //Sukūriamas užklausų kūrimo objektas, skirtas bendravimui XML formatu
        Invocation.Builder builder = target.request(MediaType.APPLICATION_XML);

        //Siunčia ir priima informaciją REST klientu
        Response response = builder.post(Entity.xml(data));

        //Gautas XML dokumentas
        responseXML = response.readEntity(String.class);
        return responseXML;
    }
    public AbstractChaining sendPOSTRequest(Data data) {
        //Validacija naudojama patikrinti privalomus laukus
        if (data == null ||
                data.getChainingType() == null ||
                data.getGoal() == null ||
                data.getFacts() == null ||
                data.getChainingType().isEmpty() ||
                data.getGoal().isEmpty()
                ) {
            return null;
        }

        //REST klientas, JAX-RS pasirūpina jo kūrimu
        Client client = ClientBuilder.newClient();

        //Nustatomas REST klientas
        //WebTarget target = client.target("http://185.80.130.228:8080/rest/post/chaining"); //Nutolęs servisas
        WebTarget target = client.target("http://localhost:8080/rest/post/chaining"); //Lokalus servisas

        //Sukūriamas užklausų kūrimo objektas, skirtas bendravimui XML formatu
        Invocation.Builder builder = target.request(MediaType.APPLICATION_XML);

        //Siunčia ir priima informaciją REST klientu
        Response response = builder.post(Entity.xml(data));

        //Gautas XML dokumentas
        responseXML = response.readEntity(String.class);

        //Sukuria XML marshalling servisą, skirtą darbui su AbstractChaining klase
        XMLMarshallingService service = new XMLMarshallingService(AbstractChaining.class);

        //Iš XML dokuemnto sukuriamas AbstractChaining objektas
        AbstractChaining chaining = (AbstractChaining) service.unmarshal(responseXML);

        //Data objektas priskiriamas sukurtam AbstractChaining, nesiunčiamas serviso
        chaining.getResult().setData(data);

        return chaining;
    }
}
