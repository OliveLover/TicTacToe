package module.blackjack.game;



import module.blackjack.card.Card;
import module.blackjack.card.Suit;
import module.blackjack.person.Dealer;
import module.blackjack.person.Person;
import module.blackjack.person.Player;

import java.util.Scanner;

public class UI {
    static Scanner sc = new Scanner(System.in);
    public static void openCard(Person person){
        String personName;
        if(person instanceof Player) {
            personName = "플레이어";
        }else {
            personName = "딜러";
        }
        System.out.printf("%s의 카드는 : ", personName);
        person.getReceivedCards().forEach(card -> UI.personCardOutput(card));
        System.out.printf("입니다.   점수 : %s 점\n", Rule.getPersonScoreString(person));
    }
    public static void openCard(Person person, boolean dealerBeginning){
        System.out.printf("딜러의 카드는 : ");
        UI.personCardOutput(person.getReceivedCards().get(0));
        System.out.printf("?   입니다.\n");
    }
    public static void openBothCard(Player player, Dealer dealer){
        UI.openCard(dealer);
        UI.openCard(player);
        System.out.println("");
    }
    public static void openBothCard(Player player, Dealer dealer, boolean dealerBeginning){
        UI.openCard(dealer, dealerBeginning);
        UI.openCard(player);
        System.out.println("");
    }
    public static void personCardOutput(Card card){
        System.out.printf("%s   ", UI.cardViewer(card));
    }

    private static String cardViewer(Card card){
        if(card.getSuit()== Suit.SPADE){
            return "♠"+card.getDenomination();
        }if(card.getSuit()==Suit.HEART){
            return "♥"+card.getDenomination();
        }if(card.getSuit()==Suit.DIAMOND){
            return "♦"+card.getDenomination();
        }else{
            return "♣"+card.getDenomination();
        }
    }

    public static boolean hitSelector(){
        String select;
        while (true){
            System.out.print("카드를 한장 더 받겠습니까?(Hit or Stand?) y / n : ");
            select = sc.nextLine();
            System.out.println("");
            if(select.equals("y")){
                return true;
            } else if (select.equals("n")) {
                return false;
            }else {
                System.out.println("올바른 값을 입력하세요");
            }
        }
    }

    private static int getInput(){
        while (true){
            String str = sc.nextLine();
            int num = 0;
            try {
                num = Integer.parseInt(str);
            }catch (NumberFormatException e){
                System.out.print("숫자로 된 값만 입력하시오 : ");
            }if(num != 0){
                return num;
            }
        }
    }

    public static int betInputs(int currentMoney){
        int betting;
        while(true){
            System.out.print("베팅할 금액을 정해주십시오(최소 1000 ~ 최대 50000) : ");
            betting = getInput();
            System.out.println("");
            if(betting>currentMoney){
                System.out.println("베팅한 금액이 잔액보다 많습니다. 다시 입력해 주세요");
            }else if(betting >= 1000 && betting <= 50000){
                System.out.printf("%d 원을 베팅합니다\n", betting);
                return betting;
            }else {
                System.out.println("올바른 금액을 입력하세요");
            }
        }
    }

    public static int setPlayersSeedMoney(){
        int seedMoney;
        while(true){
            System.out.print("게임 시작 시 플레이어가 가지고 있을 돈의 액수를 입력해 주시오(최소 5000 ~ 최대 500000) : ");
            seedMoney = getInput();
            System.out.println("");
            if(seedMoney >= 5000 && seedMoney <= 500000){
                return seedMoney;
            }else {
                System.out.println("올바른 금액을 입력하세요");
            }
        }
    }
    public static void ctmd(){
        System.out.println("*\uCE58\uD2B8\uBAA8\uB4DC \uD65C\uC131\uD654*");
    }
    public static void sessionStartMessage(Player player){
        System.out.printf("***게임을 시작합니다***\n\n(플레이어의 잔액은 %d 원 입니다.)", player.getMoney());
    }
    public static void moneyMessage(int money){
        System.out.printf("(플레이어의 잔액은 %d 원 입니다.)\n", money);
    }
    public static void bustMessage(Person person){
        if(person instanceof Player) {
            System.out.println("플레이어 버스트!(21점 초과)");
        }else {
            System.out.println("딜러 버스트!(21점 초과)");
        }
    }
    public static void blackJackMessage(Person person){
        if(person instanceof Player) {
            System.out.println("플레이어 블랙잭!");
        }else {
            System.out.println("딜러 블랙잭!");
        }
    }
    public static void resultMessage(Person person, int betting){
        if(person instanceof Player) {
            System.out.println("플레이어의 승리입니다!");
            System.out.printf("%d 원을 받았습니다.\n", betting, ((Player) person).getMoney());
        }else if(person instanceof Dealer){
            System.out.println("플레이어의 패배입니다, 딜러 승리!");
        }else {
            System.out.println("무승부 입니다");
            System.out.printf("%d 원을 받았습니다.\n", betting);
        }
        System.out.println("");
    }
    public static boolean keepGoing(){
        System.out.print("게임을 계속하려면 아무키나 누르십시오 , 종료하려면 n을 입력하시오 : ");
        String input = sc.nextLine();
        System.out.println("");
        return (input.equals("n") ? false : true);

    }
}
