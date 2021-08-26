import java.util.ArrayList;
/**
 * Un modelo simple de una subasta.
 * La subasta mantiene una lista de lotes de longitud arbitraria.
 * @Author Boris Angulo
 * @Version 2021.08.25
 */
public class Auction{
    
    private ArrayList<Lot> lots;
    private int nextLotNumber;
    
    /**
     * crar una nueva subasta
     */
    public Auction(){
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }
    
    /**
     * Introducir un nuevo lote a la subasta
     */
    public void enterLot(String description){
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }
    
    /**
     * Muestra la lista completa de los lotes de la subasta
     */
    public void showLots(){
        for(Lot lot : lots){
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Hacer una puja por un lote.
     * imprimir un mensaje indicando si la puja ha tenido exito o no.
     * @param LotNumber el lote por el que se esta pujando.
     * @param bidder la persona que esta pujando por el lote.
     * @param value el valor de la puja.
     */
    public void makeABid(int lotNumber, Person bidder, long value){
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null){
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful){
                System.out.println("The bid for lot number " + lotNumber + " was successful. ");
            }
            else{
                //indicar que puja es mayor.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber + "already has a bid of: " + 
                highestBid.getValue());
            }
        }
    }
    
    /**
     * Devolver el lote con el numero indicado. Devolver null si no existe ningun lote con
     * este numero.
     * @param lotNumber El numero del lote que hay que devolver.
     */
    public Lot getLot(int lotNumber){
        if(lotNumber >= 1 && lotNumber < nextLotNumber){
            //el numero parece ser razonable.
            Lot selectedLot = lots.get(lotNumber - 1);
            //incluir una comprobacion para asegurarnos de tener el lote correcto.
            if(selectedLot.getNumber() != lotNumber){
                System.out.println("Internal Error. Lot number " + selectedLot.getNumber()
                + " was returned instead of " + lotNumber);
                selectedLot = null;
            }
            return selectedLot;
        }
        else{
            System.out.println("Lot number: " + lotNumber + " does not exist. ");
            return null;
        }
    }
    
    public ArrayList<Lot> getUnsold(){
        ArrayList<Lot> Unsold = new ArrayList<Lot>();
        for(Lot lot : lots){
            if(lot.getHighestBid() == null){
                Unsold.add(lot);
            }
        }
        return Unsold;
    }
    
    public void close(){
        for(Lot lot : lots){
            if(lot.getHighestBid() != null){
                System.out.println("\nLote: " + lot.getDescription() 
                + "\nComprador: " + lot.getHighestBid().getBidder().getName()
                + "\nMonto: $" + lot.getHighestBid().getValue());
            }
            else if (lot.getHighestBid() == null){
                System.out.println("\nLote: " + lot.getDescription() 
                + "\nNadie pujo por este lote :(");
            }
        }
    }
}
