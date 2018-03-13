package main;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLMarshallingService {

    // JAXBContext is thread safe and can be created once
    private JAXBContext jaxbContext;

    public XMLMarshallingService() {
        try {
            // create context with ":" separated list of packages that
            // contain your JAXB ObjectFactory classes
            jaxbContext = JAXBContext.newInstance("main.Data", main.Data.class.getClassLoader());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public JAXBElement<?> unmarshal(String xmlString) {
        try {
            // Unmarshallers are not thread-safe.  Create a new one every time.
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xmlString));
            return unmarshaller.unmarshal(reader, main.AbstractChaining.class);
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
