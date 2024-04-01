import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Node;

class TestNode {
    // 기본 이름을 가진 노드 생성 테스트
    @Test
    void testNodeIdDifferent() {
        Node node = Node.createNode();
        Node node1 = Node.createNode();
        assertNotEquals(node.getId(), node1.getId(), "node Id is not different!");
        System.out.println("Node Id Test Passed");
    }

    // 노드 이름 동일 테스트
    @Test
    void testSetName() {
        Node node = Node.createNode();
        node.setName("a");

        Node node1 = Node.createNode();
        node1.setName("a");

        assertEquals(node.getName(), node1.getName(), "node name same is right");
        System.out.println("Node Name Update Test Passed");
    }
}
