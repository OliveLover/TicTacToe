package module.blackjack.card;

public class Card {
    private String denomination;    //카드 끝수: A,1,2,3,4,5,6,7,8,9,10,J,Q,K
    private Suit suit;  //모양: enum 으로 정의되어있음 SPADE, HEART, DIAMOND, CLUB

    public Card(Suit suit, String denomination){
        this.suit = suit;
        this.denomination = denomination;
    }

    public String getDenomination() {
        return denomination;
    }

    public Suit getSuit() {
        return suit;
    }
}
