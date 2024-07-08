package module.blackjack.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CardDeck {
    private List<Card> cards = new ArrayList<>(52);
    private Stack<Card> mixedCards = new Stack<>();
    public CardDeck(){
        makeCards();
        mixCards();
    }
    private void mixCards(){    //cards 리스트에서 랜덤으로 한장씩 뽑아 섞여있는 카드 스택을 만든다
        for(int i = 0; i<52; i++){
            int rand = (int)(Math.random()*cards.size());
            mixedCards.push(cards.get(rand));
            cards.remove(rand);
        }

    }
    private void makeCards(){   //카드 52장을 순서대로 만들어 cards 리스트에 넣는다
        for(Suit suit : Suit.values()){
            for(int i=1; i<=13; i++){
                inputCard(suit, i);
            }
        }
    }
    private void inputCard(Suit suit, int number){
        this.cards.add(new Card(suit, convertNumToDenomination(number)));
    }
    private String convertNumToDenomination(int number){    //1->A, 11->J, 12->Q, 13->K로 바꿔주는 메서드
        if(number == 1){
            return "A";
        }else if(number == 11){
            return "J";
        }else if(number == 12){
            return "Q";
        }else if(number == 13){
            return "K";
        }else{
            return Integer.toString(number);
        }
    }
    public Card drawCard(){ //스택에서 카드를 한장 뽑는다
        return mixedCards.pop();
    }
}
