package fr.esgi.todolist.core;

import fr.esgi.todolist.core.component.EmailService;
import fr.esgi.todolist.core.component.TodolistService;
import fr.esgi.todolist.core.component.UserComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private final ArrayList<Item> todolist;

    @Autowired
    private UserComponent userComponent;
    @Autowired
    private TodolistService todolistService;
    @Autowired
    private EmailService emailService;

    public User(final String email, final String password, final String firstname, final String lastname, final LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.todolist = new ArrayList<Item>();
        this.userComponent = new UserComponent();
        this.todolistService = new TodolistService();
        this.emailService = new EmailService();
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public ArrayList<Item> getTodolist() {
        return todolist;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setUserComponent(final UserComponent userComponent) {
        this.userComponent = userComponent;
    }

    public void setTodolistService(final TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    public void setEmailService(final EmailService emailService) {
        this.emailService = emailService;
    }

    public boolean isValid() {
        return this.userComponent.checkEmail(this.email)
                && this.userComponent.checkPassword(this.password)
                && this.userComponent.checkFirstname(this.firstname)
                && this.userComponent.checkLastname(this.lastname)
                && this.userComponent.checkBirthday(this.birthday)
                && this.userComponent.checkAge(this.birthday);
    }

    public Item canAddItem(Item item) {
        return (this.todolistService.checkLengthListValid(this.todolist)
                && this.todolistService.checkItemNameNotBlank(item.getName())
                && this.todolistService.checkItemNameValid(this.todolist, item.getName())
                && this.todolistService.checkItemContentNotBlank(item.getContent())
                && this.todolistService.checkItemContentSize(item.getContent())
                && this.todolistService.checkTimeBetweenItemDate(this.todolist, item))
        ? item : null;
    }

    public void addItem(Item item) {
        if (this.canAddItem(item) != null) {
            this.todolist.add(item);
        }
        this.emailService.send(this.birthday);
    }

}
