package edu.iis.mto.bdd.model;

public enum FrequentFlyerMember {

    Jane("janina.kowalska@acme.com","Janina","Kowalska","s3cr3t"), Joe("janusz.bloger@acme.com","Janusz","Bloger","s3cr3t2");

    private final String email;
    private final String firstName;
    private final String lastName;
    private final String password;

    private FrequentFlyerMember(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}
