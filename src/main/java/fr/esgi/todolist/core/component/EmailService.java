package fr.esgi.todolist.core.component;

import fr.esgi.todolist.core.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmailService {

    public boolean send (LocalDate date) {
        if (LocalDate.now().minusYears(18).isAfter(date)) {
            System.out.println("Email sent");
            return true;
        }
        else {
            return false;
        }
    }
}
