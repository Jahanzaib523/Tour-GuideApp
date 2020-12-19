package Models;

public class Wallet {
    private String mywalletbalance;

    public Wallet()
    {
        mywalletbalance = "0";
    }

    public Wallet (String balance)
    {
        this.mywalletbalance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "mywalletbalance='" + mywalletbalance + '\'' +
                '}';
    }

    public String getMywalletbalance() {
        return mywalletbalance;
    }

    public void setMywalletbalance(String mywalletbalance) {
        this.mywalletbalance = mywalletbalance;
    }
}
