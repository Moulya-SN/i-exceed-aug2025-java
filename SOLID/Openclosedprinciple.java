abstract class PaymentMethod
  {
     abstract public double paymentProcess(double amount);
  }
class creditcard extends PaymentMethod
 {
     @Override
     public double paymentProcess(double amount)
     {
        return amount*=.10;
     }
 }

class debitcard extends PaymentMethod
 {
     @Override
     public double paymentProcess(double amount)
     {
        return amount*=.12;
     }
 }

 class UPI extends PaymentMethod
 {
     @Override
     public double paymentProcess(double amount)
       {
          return amount*=.15;
       }
 }
class netbanking extends PaymentMethod
  {
      @Override
      public double paymentProcess(double amount)
        {
            return amount*=.18;
        }
  }
class cryptocurrency extends PaymentMethod
  {
      @Override
      public double paymentProcess(double amount)
        {
            return amount*=.21;
        }
  }
class paymentprocess
 {
     public double processType(PaymentMethod method,double amount)
       {
            return method.paymentProcess(amount);  
       }
  }
class  Openclosedprinciple
 {
   public static void main(String asd[])
     {
        PaymentMethod cc=new creditcard();
        PaymentMethod dc=new debitcard();
        PaymentMethod upi=new UPI();
        PaymentMethod net=new netbanking();
        PaymentMethod cr=new cryptocurrency();
        paymentprocess po=new paymentprocess();
        System.out.println(po.processType(net,10000));

     }
 }
      
