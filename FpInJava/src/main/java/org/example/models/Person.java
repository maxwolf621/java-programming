package org.example.models;

public class Person {

    private Address address;

    public Person() {

    }

    public Person(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}