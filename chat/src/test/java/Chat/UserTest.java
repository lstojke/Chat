package Chat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UserTest {
    private User user;


    public void setUser() {
        user = new User("127.0.0.1", 5000, data -> {
            user.addMessage(data);
        });
    }

    @Test
    void addMessage() {
        setUser();
        MessageCloud messageCloud = new MessageCloud("name", "notNull", new byte[0]);
        user.addMessage(messageCloud);
        assertFalse(user.isEmpty());
    }

    @Test
    void getRightMessage() {
        setUser();
        MessageCloud msg1 = new MessageCloud("name", "notNull", new byte[0]);
        MessageCloud msg2 = new MessageCloud("2", "Null", new byte[5]);
        user.addMessage(msg1);
        user.addMessage(msg2);
        assertEquals(user.getMessage(1).getText(), msg2.getText());
    }

}