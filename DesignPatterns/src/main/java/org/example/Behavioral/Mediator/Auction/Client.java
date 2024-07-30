package org.example.Behavioral.Mediator.Auction;

public class Client {
    public static void main(String[] args) {
        AuctionMediator auctionMediator = new Auction();
        Colleague bidder1 = new Bidder("Bidder1", auctionMediator);
        Colleague bidder2 = new Bidder("Bidder2", auctionMediator);
        Colleague bidder3 = new Bidder("Bidder3", auctionMediator);

        bidder1.placeBid(1000);
        bidder2.placeBid(2000);
        bidder3.placeBid(3000);
    }
}
