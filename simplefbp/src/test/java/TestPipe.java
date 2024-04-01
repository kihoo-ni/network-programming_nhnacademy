import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Message;
import com.nhnacademy.Pipe;

class TestPipe {

    // 기본 이름을 가진 테스트 생성 테스트
    @Test
    void testPipeIdDifferent() {
        Pipe pipe1 = Pipe.createPipe(5);
        Pipe pipe2 = Pipe.createPipe(5);
        assertNotEquals(pipe1.getId(), pipe2.getId(), "pipe Id is not different!");
        System.out.println("pipe Id Test Passed");
    }

    // 메시지 저장 갯수 지정 확인
    @Test
    void testPipeCapacityAlright() {
        Pipe pipe1 = Pipe.createPipe(5);
        assertEquals(5, pipe1.getCapacity(), "pipe capacity is different!");
        System.out.println("pipe capacity Test Passed");
    }

    // 메시지가 하나 이상 저장 할 수 있는지 확인
    @Test
    void testMultipleMessages() {
        Pipe pipe = Pipe.createPipe(3); // 각 테스트 메소드 내에서 객체를 초기화
        // 여러 개의 메시지 추가
        pipe.addMessage(Message.createMessage());
        pipe.addMessage(Message.createMessage());
        pipe.addMessage(Message.createMessage());

        // 추가한 메시지의 개수와 실제 저장된 메시지의 개수 비교
        assertEquals(3, pipe.getNormalMessages().size());
    }

    // 메시지 용량 초과 확인
    @Test
    void testAddMessage() {
        Pipe pipe = Pipe.createPipe(5);

        // 우선순위가 있는 메시지 추가
        Message msg1 = Message.createMessage();
        msg1.setPriority(true);
        pipe.addMessage(msg1);
        assertEquals(1, pipe.getPriorityMessages().size());

        // 우선순위가 없는 메시지 추가
        Message msg2 = Message.createMessage();
        msg2.setPriority(false);
        pipe.addMessage(msg2);
        assertEquals(1, pipe.getNormalMessages().size());

    }

    // 용량을 초과하는 메시지 추가 시도
    @Test
    void testMessagePriority() {
        Pipe pipe = Pipe.createPipe(5);

        // 용량을 초과하는 메시지 추가 시도
        for (int i = 0; i < 10; i++) {
            Message msg = Message.createMessage();
            msg.setPriority(false);
            pipe.addMessage(msg); // 우선순위가 없는 메시지 추가
        }
        assertEquals(5, pipe.getNormalMessages().size()); // 용량 초과로 인해 추가되지 않아야 함
    }
}
