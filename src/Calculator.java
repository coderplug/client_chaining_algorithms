import main.Data;
import main.Result;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class Calculator implements Serializable {
    // Read/write property "number":
    private int number = 5;
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    // Read-only property "result":
    private Integer result = null;
    public Integer getResult() { return result; }
    // Method to square a number
    public void square() { result = number * number; }
    // Method to navigate to the second page
    public String bye() { return "bye"; }
}