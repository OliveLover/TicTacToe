package module.blackjack.game;


import module.blackjack.person.Dealer;
import module.blackjack.person.Player;

public class GameClient {

    Player player = new Player();
    Dealer dealer = new Dealer();

    public void run() {
        boolean going = true;
        while (going) {
            if(player.getMoney()==0 && !player.getCtmd()){
                System.out.println("플레이어가 가진 돈이 없습니다.\n게임을 종료합니다");
                going = false;
            }
            GameRound gameRound = new GameRound(player, dealer);
            gameRound.run();
            UI.moneyMessage(player.getMoney());
            going = UI.keepGoing();
        }
    }

}
