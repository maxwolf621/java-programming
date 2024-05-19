package org.example.interfaces;

import java.util.function.Consumer;

public class FluentInterfaces {

    /**
     * The class Mailer has a couple of smells
     * 1. Its noisy -> repeat the mailer so many times
     * 2.
     */
    public class Mailer {
        public void from(final String address) { /*... */ }

        public void to(final String address) { /*... */ }

        public void subject(final String line) { /*... */ }

        public void body(final String message) { /*... */ }

        public void send() {
            System.out.println("sending...");
        }
    }


    public class FluentMailer {

        private FluentMailer() {
        }

        private String sender;
        private String receiver;
        private String subject;
        private String body;

        public FluentMailer from(final String address) {
            sender = address;
            return this;
        }

        public FluentMailer to(final String address) {
            receiver = address;
            return this;
        }

        public FluentMailer subject(final String line) {
            subject = line;
            return this;
        }

        public FluentMailer body(final String message) {
            body = message;
            return this;
        }


        public static void send(final Consumer<FluentMailer> block) {
            FluentInterfaces fluentInterfaces = new FluentInterfaces();
            final FluentMailer mailer = fluentInterfaces.new FluentMailer();
            block.accept(mailer);
            System.out.println("sending...");
        }
    }

    public static void main(final String[] args) {
        //  loan pattern
        FluentMailer.send(mailer ->
                mailer.from("build@agiledeveloper.com")
                        .to("venkats@agiledeveloper.com")
                        .subject("build notification")
                        .body("...much better..."));

        /**
         * disadvantage :
         * Readability and Fluency:
         *  - The use of `new` can make the API less fluent and less readable.
         *  - The `new` keyword stands out and breaks the flow of method chaining.
         * Object Lifetime and Chaining:
         *  - When someone stores the reference from `new MailBuilder()`,
         *      they can chain methods from that reference.
         *      This can lead to unexpected behavior,
         *      especially if the object’s state is mutable.
         * Corner Cases:
         *  - Ensuring that methods like `from()` are called exactly once can be challenging.
         *  - Handling edge cases related to method order and usage becomes complex.
         */
        FluentInterfaces fluentInterfaces = new FluentInterfaces();
        Mailer mailer = fluentInterfaces.new Mailer();
        mailer.from("build@agiledeveloper.com");
        mailer.to("venkats@agiledeveloper.com");
        mailer.subject("build notification");
        mailer.body("...your code sucks...");
        mailer.send();
    }
}

