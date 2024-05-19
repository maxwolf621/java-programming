package org.example.optional;

import org.example.models.Address;
import org.example.models.Person;

import java.util.function.Function;

public class FlatMapDemo {

    public static void main(String[] args) {
        Person max = new Person(new Address(""));

//        Optional.ofNullable(max.getAddress())
//                .flatMap(addres -> addres.getCity())
//                .orElse("");


    }


}
