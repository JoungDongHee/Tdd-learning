package chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// 테스트 코드 라이프 사이클
// 테스트 코드 라이프 사이클은 @Test 메서드를 실행할때마다 객체를 새로 생성하고 테스트 메서드를 실행한다.
public class LifecycleTest {
    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

    // 테스트 코드 객체 생성이후 제일 먼저 실행됨
    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    void a(){
        System.out.println("a");
    }

    @Test
    void b(){
        System.out.println("b");
    }

    // 모든 테스트 코드가 끝나면 실행한다.
    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }
}
