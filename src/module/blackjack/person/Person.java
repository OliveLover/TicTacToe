package module.blackjack.person;



import module.blackjack.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {

    protected List<Card> receivedCards = new ArrayList<>();
    protected boolean hasA = false;

    public void receiveCard(Card card){ //CardDeck 으로부터 카드를 한장 받아온다.
        receivedCards.add(card);
    }

    public List<Card> getReceivedCards(){
        return receivedCards;
    }
    public abstract boolean selectHitOrStand();   //히트(카드한장더받기)할지 스탠드(안받기)할지 정하는 메서드, 딜러랑 플레이어의 방식이 다름
    public void removeAllCards(){   //한 세션이 끝나고 다음세션을 시작할 때 가지고있는 카드와 카운트를 초기화
        receivedCards = new ArrayList<>();
        hasA = false;
    }

    public boolean getHasA() {
        return hasA;
    }

    public void trueHasA() {
        this.hasA = true;
    }
}
