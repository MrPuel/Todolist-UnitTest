import fr.esgi.todolist.core.User;
import fr.esgi.todolist.core.component.UserComponent;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    private User user;

    private UserComponent userComponent;

    @Before
    public void beforeTest() {
        this.user = new User("test@test.com", "123456789", "teo", "gouro", LocalDate.of(1997, 9, 9));

        this.userComponent = Mockito.mock(UserComponent.class);
        when(this.userComponent.checkEmail(Mockito.anyString())).thenReturn(true);
        when(this.userComponent.checkPassword(Mockito.anyString())).thenReturn(true);
        when(this.userComponent.checkFirstname(Mockito.anyString())).thenReturn(true);
        when(this.userComponent.checkLastname(Mockito.anyString())).thenReturn(true);
        when(this.userComponent.checkBirthday(Mockito.any())).thenReturn(true);
        when(this.userComponent.checkAge(Mockito.any())).thenReturn(true);

        this.user.setUserComponent(this.userComponent);
    }

    @Test
    public void testIsValidWithCorrectUser() {
        assertTrue(this.user.isValid());
    }

    @Test
    public void testIsNotValidEmailFormat() {
        this.user.setEmail("toto");
        when(this.userComponent.checkEmail(Mockito.anyString())).thenReturn(false);
        assertFalse(this.user.isValid());
    }

    @Test
    public void testIsNotValidFirstnameInvalid() {
        this.user.setFirstname(null);
        when(this.userComponent.checkFirstname(Mockito.anyString())).thenReturn(false);
        assertFalse(this.user.isValid());
    }

    @Test
    public void testIsNotValidLastnameInvalid() {
        this.user.setLastname(null);
        when(this.userComponent.checkLastname(Mockito.anyString())).thenReturn(false);
        assertFalse(this.user.isValid());
    }

    @Test
    public void testIncorrectPassword() {
        this.user.setPassword("salut");
        when(this.userComponent.checkPassword(Mockito.anyString())).thenReturn(false);
        assertFalse(this.user.isValid());
    }

    @Test
    public void testIsNotValidIncorrectAge() {
        this.user.setBirthday((LocalDate.now().minusYears(12)));
        when(this.userComponent.checkAge(Mockito.any())).thenReturn(false);
        assertFalse(this.user.isValid());
    }
}
