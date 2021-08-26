
public class Bid{
    
    private long money;
    private Person bidder;
    public Bid(Person bidder, long money){
        this.money = money;
        this.bidder = bidder;
    }
    
    public long getValue(){
        return money;
    }
    
    public Person getBidder(){
        return bidder;
    }
}
