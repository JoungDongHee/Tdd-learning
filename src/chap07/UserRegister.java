package chap07;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker weakPasswordChecker, UserRepository userRepository ,  EmailNotifier emailNotifier) {
        this.passwordChecker = weakPasswordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if(passwordChecker.checkPassword(pw)){
            throw new WeakPasswordException();
        }

        User user = userRepository.findById(id);
        if(user != null){
            throw new DupIdException();
        }
        userRepository.save(new User(id,pw,email));
        emailNotifier.sendRegistrationEmail(email);

    }
}
