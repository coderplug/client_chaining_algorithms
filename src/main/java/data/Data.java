package data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.*;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@RequestScoped
public class Data implements Serializable {

    private String chainingType;
    private String goal;
    private Boolean factsFormed;
    private String factsString;

    @XmlElementWrapper(name = "facts")
    @XmlElement(name = "fact")
    private List<String> facts;

    public Data(){
        facts = new LinkedList<>();
        factsFormed = false;
    }

    public Data(String goal, List<String> facts, String chainingType) {
        this.goal = goal;
        this.facts = facts;
        this.chainingType = chainingType;
        factsFormed = false;
    }

    public String getFactsString() {
        return factsString;
    }

    public void setFactsString(String factsString) {
        this.factsString = factsString;
    }

    public Boolean getFactsFormed() {
        return factsFormed;
    }

    public void setFactsFormed(Boolean factsFormed) {
        this.factsFormed = factsFormed;
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
        String NL = System.getProperty("line.separator");
        return "  1) Facts" + NL +
                "     " + listFacts() + NL + NL +
                "  2) Goal" + NL +
                "     " + goal;
    }

    //Used for listing facts in result string
    private String listFacts() {
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
        //String factsString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("dataForm:facts");
        if (factsString != null)
        {
            String[] factsArray = factsString.split("[\\s]*,[\\s]*");

            Set<String> factsSet = new LinkedHashSet<>(Arrays.asList(factsArray));

            factsFormed = true;
            LinkedList factsList = new LinkedList(factsSet);
            setFacts(factsList);
        }
    }
}
