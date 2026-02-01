package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
//    public LocalDate calculateExpryDate(LocalDate billingDate, int payMent) {
//
//        return billingDate.plusMonths(1);
//    }

    public LocalDate calculateExpryDate(PayData payData) {
        int addedMonths;
        if(payData.getPayMentAmount() >= 100_000){
            int restAmount = payData.getPayMentAmount() % 100_000;
            int plusMonth = restAmount / 10_000;
            addedMonths = 12+plusMonth;
        }else{
            addedMonths = payData.getPayMentAmount()/10_000;
        }

        if(payData.getFirstBillingDate() != null){
            return expryDateUsingFirstBillingDate(payData, addedMonths);
//            if(payData.getFirstBillingDate().equals(LocalDate.of(2019,1,31))) {
//                return LocalDate.of(2019,3,31);
//            }
        }else{
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    // 리팩토링으로 메서드를 추출한다.
    private LocalDate expryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths); // 한달뒤 만료일
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

        if(dayOfFirstBilling != candidateExp.getDayOfMonth()){ // 첫 납부일 과 한달뒤 만료일이 다르면
            final int dayLenofCadiMon = lastDayOfMonth(candidateExp);
            if (dayLenofCadiMon < dayOfFirstBilling) {
                // candidateExp이 가지고 있는 월의 마지막 날이, 첫 납부일의 일자보다 적은 경우
                return candidateExp.withDayOfMonth(dayLenofCadiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        }else{
            return candidateExp;
        }
    }

    private static int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}
