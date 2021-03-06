package com.example.accessingdatajpa;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {
    private static final Logger log =
            LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args){
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        CommandLineRunner commandLineRunner = (args) ->
        {
            //save a few customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Jack", "Nicholson"));
            repository.save(new Customer("Maria", "Carey"));
            repository.save(new Customer("Luis", "Miguel"));
            repository.save(new Customer("Mickey", "Mouse"));

            //fetch all users
            System.out.println("Customer found with findAll():");
            System.out.println("------------------------------");
            for (Customer customer : repository.findAll()) {
                System.out.println(customer.toString());
            }
            System.out.println("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(1l);
            System.out.println("Customer found with findById(1l):");
            System.out.println("------------------------------");
            System.out.println(customer.toString());
            System.out.println("");

            // fetch customers by last name
            System.out.println("Customer found with findByLastName('Bauer'):");
            System.out.println("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                System.out.println(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
        return commandLineRunner;
    }
}
