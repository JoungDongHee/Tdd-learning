package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {
    private UserRegister userRegister;
    private MomoryUserRepository fakeRepository = new MomoryUserRepository();
//    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
//    private MomoryUserRepository fakeRepository = new MomoryUserRepository();
//    private SpyEmailNotifier emailNotifier = new SpyEmailNotifier();

    // Mockito 를 사용하여 Mock 객체로 전환
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);


    @BeforeEach
    public void setUp() {
        userRegister = new UserRegister(mockPasswordChecker,fakeRepository,mockEmailNotifier); // 의존성 주입
    }

    @DisplayName("회원가입시 암호검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id","pw","email");
        BDDMockito.then(mockPasswordChecker).should().checkPassword(BDDMockito.anyString());
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword(){
        //mockPasswordChecker.setWeak(true); // 암호가 약하다고 응답하도록 설정
        BDDMockito.given(mockPasswordChecker.checkPassword("pw")).willReturn(true);
        assertThrows(WeakPasswordException.class,()->{ // 암호가 약할 경우 WeakPasswordException 이 발생해야 한다.
           userRegister.register("id","pw","email");
        });
    }

    @DisplayName("이미 같은 ID 가 존재하면 가입 실패")
    @Test
    void duplicateId(){
        fakeRepository.save(new User("id","pw","email@email.com"));
        assertThrows(DupIdException.class,()->{
            userRegister.register("id","pw2","email");
        });
    }

    @DisplayName("같은 ID 가 없으면 가입에 성공한다.")
    @Test
    void noDupId_RegisterSuccess(){
        userRegister.register("id","pw2","email");
        User saveUser = fakeRepository.findById("id"); // 가입 결과 확인
        assertEquals("id",saveUser.getId()); // 가입한 ID 확인
        assertEquals("email",saveUser.getEmail()); // 이메일도 검증
    }

    @DisplayName("가입하면 메일을 전송한다.")
    @Test
    void whenRegisterThenSendEmail(){
        userRegister.register("id","pw2","email@email.com");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier).should().sendRegistrationEmail(captor.capture());
        String realEmail = captor.getValue();
        assertEquals("email@email.com",realEmail);
//        assertTrue(mockEmailNotifier.isCalled());
//        assertEquals("email@email.com",mockEmailNotifier.getEmail());
    }
}
