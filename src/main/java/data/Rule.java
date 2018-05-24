package data;

//JAXB importavimas
import javax.xml.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

//Nurodoma, jog galima dirbti su XML dokumentais
@XmlRootElement(name = "result")
//Reikalinga sąrašo reikšmėms XML dokumente ir konvertuojant atvaizduoti
@XmlAccessorType(XmlAccessType.FIELD)
//Taisyklės
public class Rule {

    //Taisyklės rezultatas - konsekventas
    private String consequent;

    //Tėvinė sąrašo XML žymė
    @XmlElementWrapper(name = "antecedents")
    //Vaikinė sąrašo XML žymė
    @XmlElement(name = "antecedent")
    //Taisyklės antecedentų (sąlygų) sąrašas
    private List<Antecedent> antecedents;

    //Taisyklės serveris
    private String server;

    //ID
    private int id;

    //Ar panaudota taisyklė
    private Boolean flag1;

    //Ar taisyklės rezultatas egzistuoja faktuose
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

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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
        sb.append(" from ").append(server);
        return sb.toString();
    }

    //Sąlygų atvaizdavimas tekstu
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
