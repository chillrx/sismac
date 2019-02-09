package model.bean;

public class Cliente {
    private int id;
    private String name;
    private String birth_date;
    private String email;
    private String phone_number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birth_date;
    }

    public void setBirthdate(String birthdate) {
        this.birth_date = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phone_number;
    }

    public void setPhonenumber(String phonenumber) {
        this.phone_number = phonenumber;
    }
    
}
