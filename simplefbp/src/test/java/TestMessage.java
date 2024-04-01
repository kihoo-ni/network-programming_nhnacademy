import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Message;

class TestMessage {
    @Test
    void testMessageIdUnique() { // 아이디 고유 식별 검사
        Message message1 = Message.createMessage();
        Message message2 = Message.createMessage();
        assertNotEquals(message1.getId(), message2.getId(), "Message Id must different!");
        System.out.println("Message ID Unique Test Passed");
    }

    @Test
    void testMessageCreationTime() { // 메시지 생성 시간 정보 null체크 검사
        Message message = Message.createMessage();
        assertNotNull(message.getCreateTime(), "Message creation time is null!");
        System.out.println("Message Creation Time Test Passed");
    }
}