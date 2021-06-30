public class BankAccount{
    public int balance;

    /*@
      @public normal_behavior
      @ensures balance == \old(balance) + x;
      @ */
    public void update(int x){
        this.balance = this.balance + x;
    }
}