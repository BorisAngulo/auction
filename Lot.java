
public class Lot{
    //la puja mas alta actualmente para este lote
    private Bid highestBid;
    private int numberLot;
    private String description;
    public Lot(int numberLot, String description){
        highestBid = null;
        this.numberLot = numberLot;
        this.description = description;
    }
    
    /**
     * Intento de pujar por este lote. Para que tenga exito, la puja tiene que tener un valor
     * mayor que el de las demas pujas existentes.
     * @Param bid una nueva puja.
     * @return true si tiene exito, false en caso contrario.
     */
    public boolean bidFor(Bid bid){
        if(highestBid == null){
            highestBid = bid;
            return true;
        }
        else if(bid.getValue() > highestBid.getValue()){
            highestBid = bid;
            return true;
        }
        else{
            return false;
        }
    }
    
    public Bid getHighestBid(){
        return highestBid;
    }
    
    public int getNumber(){
        return numberLot;
    }
    
    public String getDescription(){
        return description;
    }
}
