package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalaculatorTest {

    @Test
    void plus(){
        int result = Calculator.plus(1,2); // 1 과 2 를 더하는 기능을 만들겠다.
        // 1 과 2를 더했을때 예상값은 3이다. 동일하지 않으면 assertionFaildErro 발생
        assertEquals(3,result); // 강제로 값을 입력하여 1차적으로 통과했다
        assertEquals(5,Calculator.plus(2,3)); // 검증 로직을 하나 더 추가한다.
    }
}
