package BussinessLogic;


import java.io.Serializable;

public class Administrator implements Serializable {
    private String name;
    public String password;

    public Administrator(String name, String password){
        this.password=password;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}