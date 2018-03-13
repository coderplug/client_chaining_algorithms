package main;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@SessionScoped
public class Data implements Serializable {

    private String chainingType;
    private String goal;

    @XmlElementWrapper(name = "facts")
    @XmlElement(name = "fact")
    private List<String> facts;

    public Data(){
        facts = new LinkedList<>();
    }

    public Data(String goal, List<String> facts, String chainingType) {
        this.goal = goal;
        this.facts = facts;
        this.chainingType = chainingType;
    }

    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(List<String> facts) {
        this.facts = facts;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getChainingType() {
        return chainingType;
    }

    public void setChainingType(String chainingType) {
        this.chainingType = chainingType;
    }

    @Override
    public String toString()
    {
        /*StringWriter sw = new StringWriter();
        JAXB.marshal(this, sw);
        return sw.toString();*/
        /*JAXBContext jaxbContext = null;
        Marshaller jaxbMarshaller = null;
        StringWriter sw = new StringWriter();
        try {
            jaxbContext = JAXBContext.newInstance(Rule.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(this, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();*/

        String NL = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  1) Facts").append(NL)
                     .append("     ").append(listFacts()).append(NL).append(NL)
                     .append("  2) Goal").append(NL)
                     .append("     ").append(goal);
        return stringBuilder.toString();
    }

    //Used for listing facts in result string
    public String listFacts() {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<facts.size(); i++)
        {
            if (i != facts.size() - 1)
            {
                result.append(facts.get(i) + ", ");
            }
            else
            {
                result.append(facts.get(i));
            }
        }
        return result.toString();
    }

    public void formFacts() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String factsString = request.getParameter("f1:facts");
        System.out.println(factsString);
        String[] facts = factsString.split("[\\s]*,[\\s]*");

        setFacts(Arrays.asList(facts));
    }
}
