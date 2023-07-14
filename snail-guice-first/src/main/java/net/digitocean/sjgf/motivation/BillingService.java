package net.digitocean.sjgf.motivation;

/**
 * @author: haoshichuan
 * @date: 2023/7/14 8:54
 */
public interface BillingService {
    /**
     * Attempts to charge the order to the credit card. Both successful and
     * failed transactions will be recorded.
     * 尝试通过信用卡收取订单费用。成功和失败的交易将会被记录。
     *
     * @return a receipt of the transaction. If the charge was successful, the
     *      receipt will be successful. Otherwise, the receipt will contain a
     *      decline note describing why the charge failed.
     *      返回：交易凭据，如果充值成功，返回成功标志的交易凭据；如果充值失败，返回是失败
     *      标志和原因的交易凭据
     */
    Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
