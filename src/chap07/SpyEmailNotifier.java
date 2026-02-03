package chap07;

public class SpyEmailNotifier implements EmailNotifier {
    private boolean called;  // 호출 여부
    private String email; // 발송 이메일

    public boolean isCalled() {
        return called;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void sendRegistrationEmail(String email) {
        this.email = email;
        this.called = true;
    }
}
