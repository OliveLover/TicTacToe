package module.blackjack.game;


import module.blackjack.card.CardDeck;
import module.blackjack.person.Dealer;
import module.blackjack.person.Person;
import module.blackjack.person.Player;

public class GameRound {
    Player player;
    Dealer dealer;
    Rule rule = new Rule();

    CardDeck deck = new CardDeck();

    int betMoney;
    public GameRound(Player player, Dealer dealer){
        this.player = player;
        this.dealer = dealer;
    }
    private boolean hitOrStand(Person person){
        if(person.selectHitOrStand()){
            person.receiveCard(deck.drawCard());
            return true;
        }
        return false;
    }
    private void reset(){
        player.removeAllCards();
        dealer.removeAllCards();
    }
    private void playerWin(){   //승
        UI.resultMessage(player, betMoney*2);
        player.receiveMoney(betMoney*2);
        reset();
    }
    private void playerWin(boolean isBlackJack){   //승
        UI.resultMessage(player, (int)(betMoney*2.5));
        player.receiveMoney((int)(betMoney*2.5));
        reset();
    }
    private void playerLose(){  //패
        UI.resultMessage(dealer, 0);
        reset();
    }
    private void push(){    //무승부
        UI.resultMessage(null, betMoney);
        player.receiveMoney(betMoney);
        reset();
    }
    public void run(){
        UI.sessionStartMessage(player);
        betMoney = player.betMoney();
        player.receiveCard(deck.drawCard());
        player.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        UI.openBothCard(player, dealer, true);
        if(rule.isBlackJack(player)){
            UI.openBothCard(player, dealer);
            if(rule.isBlackJack(dealer)){
                UI.blackJackMessage(player);
                UI.blackJackMessage(dealer);
                push();
            }else{
                UI.blackJackMessage(player);
                playerWin(true);
            }
            return;
        }
        while (true){
            if(!hitOrStand(player)){    //스탠드
                if(rule.isBlackJack(dealer)){
                    UI.blackJackMessage(dealer);
                    playerLose();
                    return;
                }
                boolean isHit = false;
                do{
                    UI.openBothCard(player, dealer);
                    isHit = hitOrStand(dealer);
                }while (isHit);
                if(rule.isBust(dealer)){
                    UI.bustMessage(dealer);
                    playerWin();
                }else {
                    Person judgeResult = rule.judge(player, dealer);
                    if(judgeResult instanceof Player){
                        playerWin();
                    } else if (judgeResult instanceof Dealer) {
                        playerLose();
                    }else {
                        push();
                    }
                }return;
            }
            UI.openBothCard(player, dealer, true);    //히트
            if(rule.isBust(player)){
                UI.bustMessage(player);
                playerLose();
                return;
            }
        }
    }

}
