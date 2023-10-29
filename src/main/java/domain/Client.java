package domain;

public class Client {
    
    private int idClient;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private double balance;
    
    public Client() {
    }
    public Client(int idCliente) {
        this.idClient = idCliente;
    }
    public Client(String name, String lastname, String emil, String phone, double balance) {
        this.name = name;
        this.lastname = lastname;
        this.email = emil;
        this.phone = phone;
        this.balance = balance;
    }
    public Client(int idCliente, String name, String lastname, String emil, String phone, double balance) {
        this.idClient = idCliente;
        this.name = name;
        this.lastname = lastname;
        this.email = emil;
        this.phone = phone;
        this.balance = balance;
    }
    
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idCliente) {
        this.idClient = idCliente;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String emil) {
        this.email = emil;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Client [idClient=" + idClient + ", name=" + name + ", lastname=" + lastname + ", email=" + email
                + ", phone=" + phone + ", balance=" + balance + "]";
    }
}
