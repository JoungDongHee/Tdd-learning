package chap05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledTest {
    @Disabled
    @Test
    public void testDiable() {
        System.out.println("이 코드는 Disabled 로 인해 실행안됨");
    }

    @Test
    public void testEnable() {
        System.out.println("이 코드는 실행됨");
    }
}
