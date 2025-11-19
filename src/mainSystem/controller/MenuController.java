package mainSystem.controller;

import Entity.enums.MenuState;
import managementSystem.farmSystem.*;

import java.util.Scanner;

public class MenuController {

    private final FeedAnimal feedSystem = new FeedAnimal();
    private final ShowAnimalStatus statusSystem = new ShowAnimalStatus();
    private final FarmAnimalProduce produceSystem = new FarmAnimalProduce();
    private final ProcessBuy buySystem = new ProcessBuy();
    private final ProcessSell sellSystem = new ProcessSell();

    private final Scanner scanner = new Scanner(System.in);

    private int day = 1; // 시작일
    // private Farm currentFarm = new Farm(); currentFarm 엔티티 정의되면 연결

    private MenuState state = MenuState.MAIN;

    public void start() {
        while (state != MenuState.EXIT) {
            switch (state) {
                case MAIN:
                    showMainMenu(); break;
                case FARM:
                    showFarmMenu(); break;
                case SHOP:
                    showShopMenu(); break;
            }
        }
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // 메인 메뉴
    private void showMainMenu() {
        System.out.println("""
                === 메인 메뉴 ===
                1. 농장 현황 보기
                2. 상점 가기
                3. 잠 자기
                0. 게임 종료
                """);
        System.out.print(" 선택 : ");
        int choice = getchoice();

        switch (choice) {
            case 1:
                state = MenuState.FARM;
            case 2:
                state = MenuState.SHOP;
            case 3:
                nextDay();
            case 0:
                state = MenuState.EXIT;
            default:
                System.out.println("당신을 빚을 갚기 전 까지 다른 행동을 할 수 없습니다...");
        }
    }

    // 농장 현황 보기
    private void showFarmMenu() {
        System.out.println("""
                === 농장 현황 ===
                1. 동물 상태 확인
                2. 사료 주기
                3. 수확하기
                4. 돌아가기
                """);

        System.out.print(" 선택 : ");
        int choice = getchoice();

        switch (choice) {
            case 1:
                statusSystem.AnimalsStatus();
            case 2:
                feedSystem.feedAllAnimals();
            case 3:
                produceSystem.produceAll();
            case 4:
                state = MenuState.MAIN;
            default:
                System.out.println("당신은 아직 남은 빚이 생각나 농장으로 다시 발걸음을 돌립니다...");
        }
    }

    private void showShopMenu() {
        System.out.println("""
                === 상점 ===
                1. 구매하기
                2. 판매하기
                3. 돌아가기
                """);

        System.out.print("선택 : ");
        int choice = getChoice();

        switch (choice) {
            case 1:
                buySystem.processBuy();
            case 2:
                sellSystem.processSell();
            case 3:
                state = MenuState.MAIN;
            default:
                System.out.println("상점 주인이 당신을 붙잡아 세웁니다.");
        }
    }

    // 다음 날로 넘어가기 선택
    private void nextDay() {
        System.out.println("당신은 내일을 기대하며 잠에 들었습니다.");
        day++;
        currentFarm.tickAll();  // 모든 동물의 상태 변화
        state = MenuState.MAIN;
    }

    //게임 종료 선택 시
    private void exitGame() {
        System.out.println("게임을 종료합니다.");
        state = MenuState.EXIT;
    }
}