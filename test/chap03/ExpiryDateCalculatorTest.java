package chap03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    // 서비스 를 사용하면 매달 1만원을 선불로 납부한다. 납부일 기준으로 한달뒤 서비스가 만료된다.
    // 2개월 이상 요금을 납부할수 있다.
    // 10만원 을 납부하면 1년 제공한다.

    @Test
    void 만원_납부하면_한달뒤_만료일이_됨(){
        int payMent = 10_000;
        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2019,3,10))
                        .payMentAmount(payMent)
                        .build()
                ,LocalDate.of(2019,4,10));
        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2019,5,5))
                        .payMentAmount(payMent)
                        .build()
                ,LocalDate.of(2019,6,5));

    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음(){
        int payMent = 10_000;
        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2019,1,31))
                        .payMentAmount(payMent)
                        .build()
                ,LocalDate.of(2019,2,28));
        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2019,5,31))
                        .payMentAmount(payMent)
                        .build()
                ,LocalDate.of(2019,6,30));
        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2020,1,31))
                        .payMentAmount(payMent)
                        .build()
                ,LocalDate.of(2020,2,29));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        //첫 납부일은 1월 31일 이다. 한달뒤 는 2월 28일 이다. 2월 28일날 납두를 하면 첫 납부일 기준으로 3월 31일이다.
        PayData payData = new PayData.Builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payMentAmount(10_000)
                .build();

        asserExpryDate(payData,LocalDate.of(2019,3,31));

        PayData payData2 = new PayData.Builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payMentAmount(10_000)
                .build();

        asserExpryDate(payData2,LocalDate.of(2019,3,30));

        PayData payData3 = new PayData.Builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payMentAmount(10_000)
                .build();

        asserExpryDate(payData3,LocalDate.of(2019,7,31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산(){
        // 2만원 이상납부하면 2개월 연장
        // 3만원은 3개월 ... N 만원은 N개월
        asserExpryDate(new PayData.Builder()
                .billingDate(LocalDate.of(2019,3,1))
                .payMentAmount(20_000)
                .build(),
                LocalDate.of(2019,5,1));


        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2019,6,1))
                        .payMentAmount(20_000)
                        .build(),
                LocalDate.of(2019,8,1));


        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .payMentAmount(30_000)
                        .build(),
                LocalDate.of(2019,6,1));

        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2019,8,1))
                        .payMentAmount(40_000)
                        .build(),
                LocalDate.of(2019,12,1));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부(){
        asserExpryDate(new PayData.Builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2,28))
                .payMentAmount(20_000).build(),
                LocalDate.of(2019,4,30));

        asserExpryDate(new PayData.Builder()
                        .firstBillingDate(LocalDate.of(2019,1,31))
                        .billingDate(LocalDate.of(2019,2,28))
                        .payMentAmount(40_000).build(),
                LocalDate.of(2019,6,30));

        asserExpryDate(new PayData.Builder()
                        .firstBillingDate(LocalDate.of(2019,3,31))
                        .billingDate(LocalDate.of(2019,4,30))
                        .payMentAmount(30_000).build(),
                LocalDate.of(2019,7,31));
    }

    @Test
    void 십만원_이상_납부하면_1년_제공(){
        asserExpryDate(new PayData.Builder()
                .billingDate(LocalDate.of(2019,1,28))
                .payMentAmount(100_000).build(),LocalDate.of(2020,1,28));

        asserExpryDate(new PayData.Builder()
                .billingDate(LocalDate.of(2020,2,29))
                .payMentAmount(130_000)
                .build()
                ,LocalDate.of(2021,5,29));

        asserExpryDate(new PayData.Builder()
                        .billingDate(LocalDate.of(2020,2,29))
                        .payMentAmount(150_000)
                        .build()
                ,LocalDate.of(2021,7,29));
    }

    private void asserExpryDate(PayData payData,LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expryDate = cal.calculateExpryDate(payData);
        assertEquals(expectedExpiryDate,expryDate);
    }


}
