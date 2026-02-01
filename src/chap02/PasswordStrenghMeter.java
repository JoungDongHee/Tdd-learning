package chap02;

public class PasswordStrenghMeter {
    public PasswordStrength meter(String password) {
        if(password == null || password.isEmpty()) return PasswordStrength.INVALID;
//        int metCounts = 0;
//        boolean lengthEnough = password.length()>=8;
//        if(lengthEnough) metCounts++;
//        boolean containsNum = meetsContainingNumberCriteria(password);
//        if(containsNum) metCounts++;
//        boolean containsUPP = meetsContainingUppercaseCriteria(password);
//        if(containsUPP) metCounts++;

        // 리팩토링 변수를 인라인 화 화여 코드를 간결하게 수정
        // 리팩토링 2 메소드 로 추출
        int metCounts = getMetCounts(password);
        if(metCounts <=1)  return PasswordStrength.WEAK;
        if(metCounts ==2)  return PasswordStrength.NORMAL;

        // 리팩토링 조건 을 한개만 충족하면 WEAK
//        if(lengthEnough && !containsNum && !containsUPP){
//            return PasswordStrength.WEAK;
//        }
//
//        if(!lengthEnough && containsNum && !containsUPP){
//            return PasswordStrength.WEAK;
//        }
//
//        if(!lengthEnough && !containsNum && containsUPP){
//            return PasswordStrength.WEAK;
//        }
        
          // 조건을 2개만 충족시 NOMAL
//        if(!lengthEnough) return PasswordStrength.NORMAL;
//        if(!containsNum) return PasswordStrength.NORMAL;
//        if(!containsUPP) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private static int getMetCounts(String password) {
        int metCounts = 0;
        if(password.length()>=8) metCounts++;
        if(meetsContainingNumberCriteria(password)) metCounts++;
        if(meetsContainingUppercaseCriteria(password)) metCounts++;
        return metCounts;
    }

    private static boolean meetsContainingUppercaseCriteria(String password) {
        boolean containsUpp = false;
        for(char ch : password.toCharArray()){
            if(Character.isUpperCase(ch)){
                containsUpp = true;
                break;
            }
        }
        return containsUpp;
    }

    private static boolean meetsContainingNumberCriteria(String s) {
        boolean containsNum = false;
        for(char ch : s.toCharArray()){
            if(ch>= '0'&& ch<='9'){
                containsNum = true;
                break;
            }
        }
        return containsNum;
    }
}
