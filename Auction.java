import java.util.ArrayList;
import java.util.Iterator;
/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> listOfLots;
    // The number that will be given to the next lot entered into this auction.
    private int nextLotNumber;
    

    /**
     * Create a new auction.
     */
    public Auction()
    {
        listOfLots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        listOfLots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot aLot : listOfLots) {
            System.out.println(aLot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null if a lot with this 
     * number does not exist.
     * @param lotNumber The number of the lot to return.
     * @return The lot with the given number, or null.
     */
    public Lot getLot(int lotNumber)
    {
        if(lotNumber < 1){
            System.out.println("lot" + lotNumber + "does not exist");
            return null;
        }
        for (Lot lot : listOfLots){
            if(lot.getNumber()==lotNumber){
                return lot;
            }
        }
        System.out.println("Lot" + lotNumber+ " does not exist");
        return null;
    }
    public void closeAuction()
    {   
        for(Lot lot : listOfLots) {
            Bid highestBid = lot.getHighestBid();
            if (highestBid != null){
                System.out.println("Lot" + lot.getNumber() + ":" +lot.getDescription());
                System.out.println("Sold to:" + highestBid.getBidder().getName());
                System.out.println("Winning bid: $" + highestBid.getValue());
                } 
            else{
                System.out.println("Lot" + lot.getNumber() + ":" + lot.getDescription());
                System.out.println("No bids received.");
            }
    }
    } 
    public ArrayList<Lot> getUnsold() {
    ArrayList<Lot> unsoldLots = new ArrayList<>();
    for (Lot lot : listOfLots) {
        if (lot.getHighestBid() == null) {
            unsoldLots.add(lot);
        }
    }
    return unsoldLots;
    }
    /**
     * Remove the lot with the given lot number.
     * @param number The number of the lot to be removed.
     * @return The Lot with the given number, or null if
     * there is no such lot.
     */
    public Lot removeLot(int number)
    {
        Iterator<Lot> it = listOfLots.iterator();
        while(it.hasNext()){
            Lot l = it.next();
            int lotNumber = l.getNumber();
            if (lotNumber == number){
                it.remove();
                
            }
        }
        return null;
    }
} 


