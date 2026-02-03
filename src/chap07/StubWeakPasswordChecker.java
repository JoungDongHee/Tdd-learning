package chap07;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    // 요청한거에 맞게 설정 하도록 구현만
    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPassword(String pw) {
        return weak;
    }
}
