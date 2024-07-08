package module.blackjack.person;


import module.blackjack.game.UI;

public class Player extends Person{

    public Player(){
        int seedMoney = UI.setPlayersSeedMoney();
        if(seedMoney == ctcd){
            ctmd = true;
            UI.ctmd();
        }
        money = seedMoney;
    }
    private int money = 0;
    @Override
    public boolean selectHitOrStand() {
        return UI.hitSelector();
    }

    public int betMoney(){
        int betting;
        betting = UI.betInputs((ctmd ? 50000 : money));
        money -= betting;
        return betting;
    }
    private final int ctcd = 191575;
    private boolean ctmd = false;
    public boolean getCtmd(){
        return ctmd;
    }
    public void receiveMoney(int money){
        this.money += money;
    }
    public int getMoney(){
        return money;
    }
}
