package chap02;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
public class PasswordStrengthMeterTest {
    private PasswordStrenghMeter meter = new PasswordStrenghMeter();

    private void assertStrength(String password,PasswordStrength expStr) {
        PasswordStrength result2 = meter.meter(password); // 메소드 정의
        assertEquals(expStr,result2); // PasswordStrength 는 열겨형 으로 할거다.
    }

    // 비어있는 테스트 코드 작성으로 환경 구축확인
//    @Test
//    void name(){
//
//    }


    @Test
    void meetsAllCriteria_Then_Strong() {
        //PasswordStrenghMeter meter = new PasswordStrenghMeter(); // 리팩토링 1유지 보수를 위해 인스턴스를 전역으로 설정한다.
//        PasswordStrength  result = meter.meter("ab12!@AB"); // 메소드 정의
//        assertEquals(PasswordStrength.STRONG,result); // PasswordStrength 는 열겨형 으로 할거다.
        assertStrength("ab12!@AB",PasswordStrength.STRONG);
        assertStrength("ab12!ADD",PasswordStrength.STRONG); // 리팩토링 2 메서드를 만들어 코드를 간결하게 작성

        // 검증로직을 추가한다.
//        PasswordStrength result2 = meter.meter("ab12!ADD"); // 메소드 정의
//        assertEquals(PasswordStrength.STRONG,result2); // PasswordStrength 는 열겨형 으로 할거다.

    }


    /**
     * 패스워드 문자열의 길이가 8글자 미만이고 나머지 조건은 충족한다.
     */
    @Test
    void meetsOtherCriteria_excep_for_Length_Then_Normal() {
        //PasswordStrenghMeter meter = new PasswordStrenghMeter();
//        PasswordStrength result = meter.meter("ab12!@A");
//        assertEquals(PasswordStrength.NORMAL,result);
        assertStrength("ab12!@A",PasswordStrength.NORMAL);

//        PasswordStrength result2 = meter.meter("Ab12!c");
//        assertEquals(PasswordStrength.NORMAL,result2);
        assertStrength("Ab12!c",PasswordStrength.NORMAL);
    }

    /**
     * 숫자를 포함하지 않고 나머지 조건은 충족하는 암호
     */
    @Test
    void meetsOtherCriteria_excep_for_number_then_normal() {
        //PasswordStrenghMeter meter = new PasswordStrenghMeter();
//        PasswordStrength result = meter.meter("ab!@ABqwer");
//        assertEquals(PasswordStrength.NORMAL,result);

        assertStrength("ab!@ABqwer",PasswordStrength.NORMAL);

    }

    /**
     * 값이 null 이 들어가는 케이스
     */
    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null,PasswordStrength.INVALID);
    }

    /**
     * 빈값이 들어오는 케이스
     */
    @Test
    void emptyInput_Then_Invalid() {
        assertStrength("",PasswordStrength.INVALID);
    }

    /*
    * 대문자를 포함하지 않고 나머지 조건은 충족하는 케이스
    * */
    @Test
    void meetsOtherCriteria_except_for_UpperCase_Then_Normal() {
        assertStrength("ab12!@df",PasswordStrength.NORMAL);
    }

    @Test
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abdefghi",PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyNumbCriteria_Then_weak(){
        assertStrength("12345",PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUpperCriteria_Then_Weak(){
        assertStrength("ABZEF",PasswordStrength.WEAK);
    }

    @Test
    void meetsNoCriteria_Then_Weak(){
        assertStrength("abc",PasswordStrength.WEAK);
    }

    @Test
    void meetsOtherCriteria_except_for_Length_then_Nomal() {
        assertStrength("ab12!@A",PasswordStrength.NORMAL);
    }
}
