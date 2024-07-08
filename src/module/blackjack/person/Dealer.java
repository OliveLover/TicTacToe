package module.blackjack.person;


import module.blackjack.game.Rule;

public class Dealer extends Person{

    @Override
    public boolean selectHitOrStand() {
        return Rule.getPersonScore(this) <= 16;
    }
}
