import fr.esgi.todolist.core.User;
import fr.esgi.todolist.core.component.EmailService;
import fr.esgi.todolist.core.component.TodolistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailTest {

    private User user;

    private EmailService emailService;

    @Before
    public void beforeTest() {
        // USER
        this.user = new User("test@test.com", "123456789", "teo", "gouro", LocalDate.of(1997, 9, 9));

        // EMAIL
        this.emailService = Mockito.mock(EmailService.class);
        when(this.emailService.send(Mockito.any())).thenReturn(true);
        this.user.setEmailService(this.emailService);
    }

    @Test
    public void testSendEmailIfAbove18yo() {
        assertTrue(this.emailService.send(this.user.getBirthday()));
    }

    @Test
    public void testSendEmailIfBelow18yo() {
        this.user.setBirthday((LocalDate.now().minusYears(15)));
        when(this.emailService.send(Mockito.any())).thenReturn(false);
        assertFalse(this.emailService.send(this.user.getBirthday()));
    }
}

