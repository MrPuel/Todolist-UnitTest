package fr.esgi.todolist.core.component;

import fr.esgi.todolist.core.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;

@Component
public class TodolistService {

    public boolean checkLengthListValid(ArrayList<Item> list) {
        return (list.size() <= 10);
    }

    public boolean checkItemNameNotBlank(String name) {
        return StringUtils.isNotBlank(name);
    }

    public boolean checkItemNameValid(ArrayList<Item> list, String name) {
        for (Item item: list) {
            if (item.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkItemContentNotBlank(String content) {
        return StringUtils.isNotBlank(content);
    }

    public boolean checkItemContentSize(String content) {
        return (content.length() <= 1000);
    }

    public boolean checkTimeBetweenItemDate(ArrayList<Item> todolist, Item item){
        if (todolist.size() == 0) {
            return true;
        }
        else {
            Item lastItemCreate = todolist.get(todolist.size()-1);
            return Duration.between(lastItemCreate.getCreationDate(), item.getCreationDate()).toMinutes() > 30;
        }
    }






}
