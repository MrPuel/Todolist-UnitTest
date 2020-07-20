package fr.esgi.todolist.core.component;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserComponent {

    public boolean checkEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean checkPassword(String password) {
        return (password.length() >= 8 && password.length() <= 40);
    }

    public boolean checkFirstname(String firstname) {
        return StringUtils.isNotBlank(firstname);
    }

    public boolean checkLastname(String lastname) {
        return StringUtils.isNotBlank(lastname);
    }

    public boolean checkBirthday(LocalDate birthday) {
        return birthday != null;
    }

    public boolean checkAge(LocalDate date ) {
        return LocalDate.now().minusYears(13).isAfter(date);
    }

}
