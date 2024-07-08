package module.blackjack.game;


import module.blackjack.card.Card;
import module.blackjack.person.Dealer;
import module.blackjack.person.Person;
import module.blackjack.person.Player;

public class Rule {

    private final int maxScore = 21;

    public static int getPersonScore(Person person){
        int score = 0;
        /*score = person.getReceivedCards()
                .stream().mapToInt(card -> convertDenominationToNum(card.getDenomination()))
                .sum(); 정무가 알려준 람다쓰는 코드, 잘 모르겠어서 그냥 반복문으로*/
        //person.getReceivedCards().forEach(card -> score.addAndGet(convertDenominationToNum(card.getDenomination()))); 직접 써본건데 아토믹인티저 써야되서 그냥 반복문으로
        for(Card card : person.getReceivedCards()){
            score += convertDenominationToNum(card.getDenomination(), person);
        }
        return score;
    }
    public static String getPersonScoreString(Person person){
        int score = getPersonScore(person);
        if(person.getHasA() && score+10<=21){
            return String.format("%d or %d", getPersonScore(person), getPersonScore(person)+10);
        }return String.format("%d", getPersonScore(person));
    }
    private static int convertDenominationToNum(String denomination, Person person){
        if(denomination.equals("A")){
            person.trueHasA();
            return 1;
        } else if(denomination.equals("J") || denomination.equals("Q") || denomination.equals("K")) {
            return 10;
        }else {
            return Integer.parseInt(denomination);
        }
    }
    public boolean isBlackJack(Person person){
        return person.getHasA() && getPersonScore(person) == 11;
    }
    public boolean isBust(Person person){
        return getPersonScore(person) > maxScore;
    }
    public Person judge(Player player, Dealer dealer){
        int playerScore = getPersonScore(player);
        int dealerScore = getPersonScore(dealer);
        playerScore = (player.getHasA() && playerScore + 10 <=21 ? playerScore+10 : playerScore);
        dealerScore = (dealer.getHasA() && dealerScore + 10 <=21 ? dealerScore+10 : dealerScore);
        if(playerScore>dealerScore){
            return player;  //플레이어 승
        }else if(playerScore<dealerScore){
            return dealer;  //딜러 승
        }else{
            return null;    //비김
        }
    }
}
