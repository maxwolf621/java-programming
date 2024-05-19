package org.example.interfaces;

/**
 * The Java compiler follows a few simple rules to resolve default methods:
 * 1. Subtypes automatically carry over the default methods from their
 * supertypes.
 * 2. For interfaces that contribute a default method, the implementation in a
 * subtype takes precedence over the one in supertypes.
 * 3. Implementations in classes, including abstract declarations, take precedence over all interface defaults.
 * 4. If there’s a conflict between two or more default method implementations,
 * or there’s a default-abstract conflict between two interfaces, the inheriting
 * class should disambiguate
 */

public class MyInterfaces {

    public interface Fly {
        default void takeOff() {
            System.out.println("Fly::takeOff");
        }

        default void land() {
            System.out.println("Fly::land");
        }

        default void turn() {
            System.out.println("Fly::turn");
        }

        default void cruise() {
            System.out.println("Fly::cruise");
        }
    }

    public interface FastFly extends Fly {
        default void takeOff() {
            System.out.println("FastFly::takeOff");
        }
    }

    public interface Sail {
        default void cruise() {
            System.out.println("Sail::cruise");
        }

        default void turn() {
            System.out.println("Sail::turn");
        }
    }

    public class Vehicle {
        public void turn() {
            System.out.println("Vehicle::turn");
        }
    }

    public class SeaPlane extends Vehicle implements FastFly, Sail {
        private int altitude;

        public SeaPlane(int altitude) {
            this.altitude = altitude;
        }


        /**
         * the Java compiler will force us to implement the cruise() method in
         * the SeaPlane class because the default implementations in the interfaces FastFly
         * (derived from Fly) and Sail conflict (rule 4).
         */
        public void cruise() {
            System.out.print("SeaPlane::cruise currently cruise like: ");
            if (altitude > 1) {
                FastFly.super.cruise();
            } else
                Sail.super.cruise();
        }
    }

    public static void main(String[] args) {

        /**
         * @apiNote Inner Class Dependency:
         * A non-static inner class implicitly holds a reference to its enclosing class instance.
         * This is because it can access the members (including private members) of the outer class.
         * @apiNote Instantiation Requirement:
         *  To create an instance of a non-static inner class,
         *  you need an instance of the enclosing class.
         *  This is done using the syntax `enclosingClassInstance.new InnerClassConstructor()`.
         */

        MyInterfaces myInterfaces = new MyInterfaces();
        SeaPlane seaPlane = myInterfaces.new SeaPlane(3000);

        /**
         * There appears to be a conflict for the turn() method, but that’s really not the
         * case.
         * Even though the turn() method is present in the interfaces, the implementation
         * in the superclass Vehicle takes precedence here (rule 3), so there’s no conflict to resolve
         */


        seaPlane.takeOff(); // call Fastly.super.takeOff() due to rule#1 and rule#2
        seaPlane.cruise();
        seaPlane.turn(); // call Vehicle.super.turn() due to rule#3
        seaPlane.land(); // Fly.super.land() due to rule#1

        System.out.println("--- Changing altitude to 0 ---");
        seaPlane = myInterfaces.new SeaPlane(0);

        seaPlane.takeOff();
        seaPlane.cruise();
        seaPlane.turn();
        seaPlane.land();
    }

}
