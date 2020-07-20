package fr.esgi.todolist.core;

import java.time.LocalDateTime;

public class Item {

    private String name;
    private String content;
    private LocalDateTime creationDate;

    public Item(final String name, final String content) {
        this.name = name;
        this.content = content;
        this.creationDate = LocalDateTime.now();
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setCreationDate(final LocalDateTime date) {
        this.creationDate = date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
