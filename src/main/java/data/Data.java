package data;

//CDI anotacijos
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

//JAXB importavimas
import javax.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.*;

//Parodoma, jog objektas gali būti konvertuojamas į XML elementą
@XmlRootElement(name = "data")
//Reikalinga XML sąrašų atvaizdavimui XML dokumente
@XmlAccessorType(XmlAccessType.FIELD)
//Gyvavimo trukmė valdoma CDI
@ManagedBean
//Gyvavimo trukmė - užklausa
@RequestScoped
public class Data implements Serializable {

    //Tėvinis sąrašo XML elementas
    @XmlElementWrapper(name = "databases")
    //Vaikinis sąrašo XML elementas
    @XmlElement(name = "database")
    //DB sąrašas
    private List<String> databases;

    //Išvedimo tipas
    private String chainingType;

    //Išvedimo tikslas
    private String goal;

    //Naudojamas tikrinti ar faktai konvertuoti iš lauko reikšmės interfeise
    private Boolean factsFormed;

    //Faktų sąrašas tekstiniu formatu
    private String factsString;

    @XmlElementWrapper(name = "rules")
    @XmlElement(name = "rule")
    private List<Rule> rules;

    @XmlElementWrapper(name = "facts") //List parent node
    @XmlElement(name = "fact") //List children node
    //Konvertuoti faktai
    private List<String> facts;

    public Data(){
        facts = new LinkedList<>();
        databases = new LinkedList<>();
        factsFormed = false;
    }

    public Data(String goal, List<String> facts, String chainingType, List<String> databases) {
        this.goal = goal;
        this.facts = facts;
        this.chainingType = chainingType;
        this.databases = databases;
        factsFormed = false;
    }

    public List<String> getDatabases() {
        return databases;
    }

    public void setDatabases(List<String> databases) {
        this.databases = databases;
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
                "     " + goal + NL + NL +
                "  3) Rules" + NL +
                "     " + listRules() + NL;
    }

    private String listRules() {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<rules.size(); i++)
        {
            if (i != rules.size() - 1)
            {
                builder.append(rules.get(i).toString() + ", ");
            }
            else
            {
                builder.append(rules.get(i).toString());
            }
        }
        return builder.toString();
    }

    //Sąrašų atvaizdavimas tekstu
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

    //Faktų formavimas
    public void formFacts() {
        if (factsString != null)
        {
            //Regex, padalinantis pagal kablelius string tekstą į maxyvą
            String[] factsArray = factsString.split("[\\s]*,[\\s]*");

            //Masyvas paverčiamas į rinkinį
            Set<String> factsSet = new LinkedHashSet<>(Arrays.asList(factsArray));

            factsFormed = true;

            //Rinkinys pavečiamas į sąrašą
            LinkedList factsList = new LinkedList(factsSet);
            setFacts(factsList);
        }
    }
}
