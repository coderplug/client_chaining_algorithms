package data;

//JAXB importavimas
import javax.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

//Nurodoma, jog galima dirbti su XML dokumentais
@XmlRootElement(name = "result")
//Reikalinga sąrašo reikšmėms XML dokumente ir konvertuojant atvaizduoti
@XmlAccessorType(XmlAccessType.FIELD)
//Žingsnių atvaizdavimas
public class Trace implements Serializable {

    //Tėvinė sąrašo XML žymė
    @XmlElementWrapper(name = "trace_list")
    //Vaikinė sąrašo XML žymė
    @XmlElement(name = "line")
    //Žingsnių sąrašas
    private List<String> traceList;

    public Trace(){
        traceList = new LinkedList<>();
    }

    public void addToTrace(String line){
        if (traceList == null)
        {
            traceList = new LinkedList<>();
        }
        traceList.add(line);
    }

    public Trace(List<String> traceList) {
        this.traceList = traceList;
    }

    public List<String> getTraceList() {
        return traceList;
    }

    public void setTraceList(List<String> traceList) {
        this.traceList = traceList;
    }

    @Override
    public String toString() {
        String NL = System.getProperty("line.separator");
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("PART 2. Trace").append(NL).append(NL);
        for(String line: traceList)
        {
            if (count != 0)
            {
                stringBuilder.append(NL);
            }
            stringBuilder.append(line);
            count++;
        }
        return stringBuilder.toString();
    }
}
