import fr.esgi.todolist.core.Item;
import fr.esgi.todolist.core.User;
import fr.esgi.todolist.core.component.TodolistService;
import fr.esgi.todolist.core.component.UserComponent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodolistTest {

    private User user;
    private Item item;

    private UserComponent userComponent;
    private TodolistService todolistService;

    @Before
    public void beforeTest() {
        // USER
        this.user = new User("test@test.com", "123456789", "teo", "gouro", LocalDate.of(1997, 9, 9));

        this.userComponent = Mockito.mock(UserComponent.class);
        when(this.userComponent.checkEmail(Mockito.anyString())).thenReturn(true);
        when(this.userComponent.checkPassword(Mockito.anyString())).thenReturn(true);
        when(this.userComponent.checkFirstname(Mockito.anyString())).thenReturn(true);
        when(this.userComponent.checkLastname(Mockito.anyString())).thenReturn(true);
        when(this.userComponent.checkBirthday(Mockito.any())).thenReturn(true);
        when(this.userComponent.checkAge(Mockito.any())).thenReturn(true);
        this.user.setUserComponent(this.userComponent);

        // TODOLIST
        this.todolistService = Mockito.mock(TodolistService.class);
        when(this.todolistService.checkLengthListValid(Mockito.any())).thenReturn(true);
        when(this.todolistService.checkItemContentNotBlank(Mockito.anyString())).thenReturn(true);
        when(this.todolistService.checkItemNameNotBlank(Mockito.anyString())).thenReturn(true);
        when(this.todolistService.checkItemContentSize(Mockito.anyString())).thenReturn(true);
        when(this.todolistService.checkItemNameValid(Mockito.any(),Mockito.anyString())).thenReturn(true);
        when(this.todolistService.checkTimeBetweenItemDate(Mockito.any(), Mockito.any())).thenReturn(true);
        this.user.setTodolistService(this.todolistService);

        // ITEM
        this.item = new Item("test","content");

    }

    @Test
    public void testCanAddItem() {
        assertNotNull(this.user.canAddItem(this.item));
    }

    @Test
    public void testIsNotValidNameInvalid() {
        this.item.setName(null);
        when(this.todolistService.checkItemNameNotBlank(Mockito.anyString())).thenReturn(false);
        assertNull(this.user.canAddItem(item));
    }

    @Test
    public void testIsNotValidNameNotUnique() {
        Item item2 = new Item("test","content");
        this.user.addItem(this.item);
        when(this.todolistService.checkItemNameValid(Mockito.any(), Mockito.anyString())).thenReturn(false);
        assertNull(this.user.canAddItem(item2));
    }

    @Test
    public void testIsNotValidContentInvalid() {
        this.item.setContent(null);
        when(this.todolistService.checkItemContentNotBlank(Mockito.anyString())).thenReturn(false);
        assertNull(this.user.canAddItem(item));
    }

    @Test
    public void testIsNotValidCreationDateUnder30mins() {
        Item item2 = new Item("test2","content");
        this.user.addItem(this.item);
        when(this.todolistService.checkTimeBetweenItemDate(Mockito.any(), Mockito.any())).thenReturn(false);
        assertNull(this.user.canAddItem(item2));
    }

    @Test
    public void testIsValidCreationDateMoreThan30mins() {
        Item item2 = new Item("test2","content");
        this.item.setCreationDate(LocalDateTime.of(2014, 9, 9, 19, 46, 45));
        this.user.addItem(this.item);
        when(this.todolistService.checkTimeBetweenItemDate(Mockito.any(), Mockito.any())).thenReturn(true);
        assertNotNull(this.user.canAddItem(item2));
    }
}
