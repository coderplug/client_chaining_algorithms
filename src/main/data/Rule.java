package main.data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) //Needed to show List values correctly
public class Rule {

    private String consequent;

    @XmlElementWrapper(name = "antecedents")
    @XmlElement(name = "antecedent")
    private List<Antecedent> antecedents;
    private int id;
    private Boolean flag1;
    private Boolean flag2;

    //Empty is needed to generate XML
    public Rule(){
        antecedents = new LinkedList<>();
    }

    public Rule(String consequent, List<Antecedent> antecedents, int id) {
        this.consequent = consequent;
        this.antecedents = new LinkedList<>(antecedents);
        this.id = id;
        flag1 = false;
        flag2 = false;
    }

    public String getConsequent() {
        return consequent;
    }

    public void setConsequent(String consequent) {
        this.consequent = consequent;
    }

    public List<Antecedent> getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(List<Antecedent> antecedents) {
        this.antecedents = antecedents;
    }

    public int getId() {
        return id;
    }

    public void setId(int number) {
        this.id = number;
    }

    public Boolean getFlag1() {
        return flag1;
    }

    public void setFlag1(Boolean flag1) {
        this.flag1 = flag1;
    }

    public Boolean getFlag2() {
        return flag2;
    }

    public void setFlag2(Boolean flag2) {
        this.flag2 = flag2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("R").append(Integer.toString(id)).append(": ");
        for(Antecedent anc : antecedents) {
            sb.append(anc.getName()).append(", ");
        }
        sb.delete(sb.lastIndexOf(", "), sb.length());
        sb.append(" -> ").append(consequent);
        return sb.toString();
    }

    //Used for listing risk conditions in result string
    public String listAntecedents() {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<antecedents.size(); i++)
        {
            if (i != antecedents.size() - 1)
            {
                result.append(antecedents.get(i).getName() + ", ");
            }
            else
            {
                result.append(antecedents.get(i).getName());
            }
        }
        return result.toString();
    }
}
