package chap05;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("@DisplayName 테스트 코드")
public class DisPlayNameTest {

    @DisplayName("값이 같은지 비교")
    @Test
    void a(){
        System.out.println("a");
    }

    @Test
    void notDisplayName(){
        System.out.println("notDisplayName");
    }

    @DisplayName("익센션 발생 여부 테스트")
    @Test
    void exceptionTest(){
        System.out.println("exceptionTest");
    }

}
