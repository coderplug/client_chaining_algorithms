package data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD) //Needed to show List values correctly
public class Result implements Serializable {
    private Boolean goalReached;

    @XmlElementWrapper(name = "rule_sequence")
    @XmlElement(name = "rule")
    private List<Rule> ruleSequence;

    @XmlTransient  //Used to avoid when creating xml file
    private Data data;

    public Result(){
        ruleSequence = new LinkedList<>();
        data = new Data();
    }

    public Result(Boolean goalReached, List<Rule> ruleSequence, Data data){
        this.goalReached = goalReached;
        this.ruleSequence = ruleSequence;
        this.data = data;
    }

    @Override
    public String toString() {
        String NL = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        if (goalReached && ruleSequence.size() == 0)
        {
            stringBuilder.append("  Goal " + data.getGoal() + " among facts. Empty path.");
        }
        else if (!goalReached)
        {
            stringBuilder.append("  Goal " + data.getGoal() + " was not reached.");
        }
        else
        {
            stringBuilder.append("  1) Goal " + data.getGoal() + " achieved.").append(NL);
            stringBuilder.append("  2) Path: " + getRuleSequenceString() + ".");
        }
        return stringBuilder.toString();
    }
    @XmlTransient
    public Data getData(){
        return data;
    }
    public void setData(Data data){
        this.data = data;
    }

    public Boolean getGoalReached() {
        return goalReached;
    }

    public List<Rule> getRuleSequence() {
        return ruleSequence;
    }
    public String getRuleSequenceString(){
        StringBuilder stringBuilder = new StringBuilder();
        String delim = "";
        for (Rule rule : ruleSequence) {
            stringBuilder.append(delim).append("R" + rule.getId());
            delim = ", ";
        }
        if(ruleSequence.size() == 0 ){
            stringBuilder.append("empty");
        }
        return stringBuilder.toString();
    }
}
