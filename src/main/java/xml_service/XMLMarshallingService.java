package xml_service;

import data.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLMarshallingService {

    // JAXBContext is thread safe and can be created once
    private JAXBContext jaxbContext;

    public XMLMarshallingService() {
        try {
            // create context with ":" separated list of packages that
            // contain your JAXB ObjectFactory classes
            jaxbContext = JAXBContext.newInstance("main.data.Data", Data.class.getClassLoader());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public XMLMarshallingService(Class<?> objectClass) {
        try {
            // create context with ":" separated list of packages that
            // contain your JAXB ObjectFactory classes
            jaxbContext = JAXBContext.newInstance(objectClass);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Object unmarshal(String xmlString) {
        try {
            // Unmarshallers are not thread-safe.  Create a new one every time.
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            return unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    public String marshal(JAXBElement<?> jaxbElement) {
        try {
            // Marshallers are not thread-safe.  Create a new one every time.
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(jaxbElement, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
