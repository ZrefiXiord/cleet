package fr.zrefixiord.cleet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class CleetApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CleetApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("yeah");
    }
}
