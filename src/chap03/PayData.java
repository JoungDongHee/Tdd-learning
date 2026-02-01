package chap03;

import java.time.LocalDate;

public class PayData {

    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payMentAmount;

    public PayData() {
    }

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payMentAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payMentAmount = payMentAmount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayMentAmount() {
        return payMentAmount;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public static class Builder {
        private PayData payData = new PayData();

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            payData.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            payData.billingDate = billingDate;
            return this;
        }

        public Builder payMentAmount(int payMentAmount) {
            payData.payMentAmount = payMentAmount;
            return this;
        }
        public PayData build() {
            return payData;
        }
    }
}
