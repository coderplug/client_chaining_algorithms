package xml_service;

import data.Data;

//JAXB importavimai
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

//Konvertavimas iš XML į objektą ir atvirkščiai
public class XMLMarshallingService {

    //Įeitis į JAXB API
    //Saugus gijoms, gali būti sukuriamas vieną kartą
    private JAXBContext jaxbContext;

    public XMLMarshallingService() {
        try {
            jaxbContext = JAXBContext.newInstance("main.data.Data", Data.class.getClassLoader());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public XMLMarshallingService(Class<?> objectClass) {
        try {
            jaxbContext = JAXBContext.newInstance(objectClass);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    //Vertimas iš XML į objektą
    public Object unmarshal(String xmlString) {
        try {
            // Unmarshallers nesaugūs gijoms. Sukuriamas kiekvieno konvertavimo metu.
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            //Perskaitomas XML
            StringReader reader = new StringReader(xmlString);

            //Grąžinas konvertuotas objektas
            return unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    //Vertimas iš objekto į XML
    public String marshal(JAXBElement<?> jaxbElement) {
        try {
            // Marshallers nesaugūs gijoms. Sukuriamas kiekvieno konvertavimo metu.
            Marshaller marshaller = jaxbContext.createMarshaller();

            StringWriter stringWriter = new StringWriter();

            //Konvertuojamas objektas į string (XML)
            marshaller.marshal(jaxbElement, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
