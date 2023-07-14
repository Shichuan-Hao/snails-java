#  Motivation

将所有内容连接在一起是应用程序开发中一个乏味的部分。有多种方法可以将数据、服务和表示类相互连接。 为了对比这些方法，以披萨订购网站编写计费
代码为例子：
```java
public interface BillingService {
  /**
   * Attempts to charge the order to the credit card. Both successful and
   * failed transactions will be recorded.
   *
   * @return a receipt of the transaction. If the charge was successful, the
   *      receipt will be successful. Otherwise, the receipt will contain a
   *      decline note describing why the charge failed.
   */
  Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
```
随着实现，我们将为我们的代码编写单元测试。在测试中，我们需要一个 FakeCreditCardProcessor 来避免向真正的信用卡收费！

## 直接调用构造函数 Direct constructor calls
当我们刚刚更新信用卡处理器和交易记录器时，代码如下所示：
```java
public class RealBillingService implements BillingService {
  public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
    CreditCardProcessor processor = new PaypalCreditCardProcessor();
    TransactionLog transactionLog = new DatabaseTransactionLog();

    try {
      ChargeResult result = processor.charge(creditCard, order.getAmount());
      transactionLog.logChargeResult(result);

      return result.wasSuccessful()
          ? Receipt.forSuccessfulCharge(order.getAmount())
          : Receipt.forDeclinedCharge(result.getDeclineMessage());
     } catch (UnreachableException e) {
      transactionLog.logConnectException(e);
      return Receipt.forSystemFailure(e.getMessage());
    }
  }
}
```
该代码给模块化和可测试性带来了问题。对真实信用卡处理器的直接编译时，依赖意味着测试代码将向信用卡收取费用！测试当收费被拒绝或服务不可用时会发生什么也很困难。

## 依赖注入 Dependency Injection

## 使用 Guice 进行依赖注入


