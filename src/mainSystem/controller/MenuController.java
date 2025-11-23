package mainSystem.controller;

import common.enums.*;
import farmSystem.Farm;
import farmSystem.Finance;
import Entity.livestock.Livestock;
import Entity.livestock.*;
import marketSystem.ShopService;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    // 현재 농장 상태와 자금 상태
    private final Farm farm;
    private final Finance finance;

    private final Scanner scanner = new Scanner(System.in);
    private MenuState state = MenuState.MAIN;
    private int day = 1;

    public MenuController(Farm farm, Finance finance) {
        this.farm = farm;
        this.finance = finance;
    }

    //
    public void start() {
        while (state != MenuState.EXIT) {
            switch (state) {
                case MAIN -> showMainMenu();
                case FARM -> showFarmMenu();
                case SHOP -> showShopMenu();
            }
        }
    }

    // 유저의 선택 인식
    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
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
                4. 게임 종료
                """);
        System.out.print("선택 : ");
        int choice = getChoice();

        switch (choice) {
            case 1 -> state = MenuState.FARM;
            case 2 -> state = MenuState.SHOP;
            case 3 -> nextDay();
            case 4 -> state = MenuState.EXIT;
            default -> System.out.println("잘못된 입력입니다.");
        }
    }

    private void showFarmMenu() {
        System.out.println("""
                === 농장 ===
                1. 동물 상태 확인
                2. 사료 주기
                3. 수확하기
                4. 돌아가기
                """);
        System.out.print("선택 : ");
        int choice = getChoice();

        switch (choice) {
            case 1 -> farm.displayAllAnimalsStatus(); // 동물 상태 출력
            case 2 -> feedAllAnimals();      // 모든 동물에 사료 주기
            case 3 -> interactWithAnimals(); // 수확 후 Product/Finance에 반영
            case 4 -> state = MenuState.MAIN;// 메인화면으로 돌아가기
            default -> System.out.println("잘못된 입력입니다.");
        }
    }

    //
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
            case 1 -> showBuyMenu(); // 구매 화면 진입
            case 2 -> ShopService.sellProducts(finance, farm);// 판매 화면 진입
            case 3 -> state = MenuState.MAIN;                 // 메인화면으로 돌아가기
            default -> System.out.println("잘못된 입력입니다.");
        }
    }

    // 잠 자기 선택
    private void nextDay() {
        day++;
        System.out.println("\n=== 하루가 지나" + day + "일차가 밝았습니다 ===\n");
        state = MenuState.MAIN;
    }

    // 모든 동물 사료 주기
    private void feedAllAnimals() {
        List<Livestock> animals = farm.getAnimals();

        //예외 1 : 사육장에 동물이 없음
        if (animals.isEmpty()) {
            System.out.println("사육장에 동물이 없습니다.");
            return;
        }

        System.out.println("\n[모든 동물에게 사료를 줍니다]");
        for (Livestock animal : animals) {
            animal.feed();  // ★ 여기서 Livestock의 feed() 호출
        }
        System.out.println();
    }

    // 모든 동물 상호작용
    private void interactWithAnimals() {
        List<Livestock> animals = farm.getAnimals();

        if (animals.isEmpty()) {
            System.out.println("사육장에 동물이 없습니다.");
            return;
        }

        System.out.println("\n[동물들과 상호작용합니다. (HP가 조금 감소합니다)]");
        for (Livestock animal : animals) {
            animal.takeDamage(40);  // 상호작용 후 HP감소
        }
        System.out.println();
    }

    // 구매 화면
    private void showBuyMenu() {
        while (true) {
            System.out.println("""
                === 구매하기 ===
                1. 동물 구매
                2. 사료 구매
                3. 사육장 확장
                4. 돌아가기
                """);
            System.out.print("선택 : ");
            int choice = getChoice();

            switch (choice) {
                case 1 -> buyAnimal();       // 동물 관련
                case 2 -> buyFeed();         // 사료 관련
                case 3 -> buyEnclosure();    // 사육장 확장
                case 4 -> {
                    System.out.println("상점 메인으로 돌아갑니다.");
                    return;                 // 구매 화면으로 돌아감
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 동물 구매
    private void buyAnimal() {
        //  빈 사육장이 있는지 검증
        if (farm.getAnimals().size() >= farm.getMaxCapacity()) {
            System.out.println(" 빈 사육장이 없습니다. 동물을 더 키우려면 사육장을 확장해주세요.");
            return;
        }
        // 텍스트 출력
        System.out.println("""
            === 동물 구매 ===
            1. 닭 (Chicken)  - 50원
            2. 양 (Sheep)    - 100원
            3. 소 (Cow)      - 200원
            4. 사슴 (Deer)   - 150원
            5. 유니콘 (Unicorn) - 3000원
            6. 돌아가기
            """);
        System.out.print("선택 : ");
        int choice = getChoice();

        String animalKey;
        switch (choice) {
            case 1 -> animalKey = "Chicken";
            case 2 -> animalKey = "Sheep";
            case 3 -> animalKey = "Cow";
            case 4 -> animalKey = "Deer";
            case 5 -> animalKey = "Unicorn";
            case 6 -> {
                System.out.println("동물 구매를 취소합니다.");
                return;
            }
            default -> {
                System.out.println("잘못된 입력입니다.");
                return;
            }
        }

        // 가격 및 타입 조회
        ShopService.ItemPurchaseInfo info =
                ShopService.getPriceAndType(animalKey, farm.getEnclosureLevel());

        if (info.type != ItemType.ANIMAL) {
            System.out.println("이 항목은 동물이 아닙니다.");
            return;
        }

        int price = info.price;

        // 돈이 있는지 확인
        if (!finance.subtractMoney(price)) {
            return;
        }

        // 동물 이름 입력
        System.out.print(choice+"의 이름을 입력하세요: ");
        String name = scanner.nextLine();

        // 실제 동물 객체 생성
        Livestock newAnimal;
        switch (animalKey) {
            case "Chicken" -> newAnimal = new Chicken(name);
            case "Sheep"   -> newAnimal = new Sheep(name);
            case "Cow"     -> newAnimal = new Cow(name);
            case "Deer"    -> newAnimal = new Deer(name);
            case "Unicorn" -> newAnimal = new Unicorn(name, "Unicorn"); // 네 생성자에 맞춰줘
            default -> {
                System.out.println("알 수 없는 동물 타입입니다.");
                finance.addMoney(price); // 방어적으로 환불
                return;
            }
        }
        // addAnimal() 써서 동물 추가
        boolean added = farm.addAnimal(newAnimal);

        // 사육장 부족 등으로 인해 동물 구매 실패 시 환불
        if (!added) {
            System.out.println("동물을 사육장에 추가하지 못해 돈을 환불합니다.");
            finance.addMoney(price);
        }
    }


    // 사료 구매
    private void buyFeed() {
        System.out.println("[사료 구매] 기능은 아직 구현 중입니다.");
        // TODO: 동물별 사료 종류를 다르게 할지, 한 번에 사지는 사료 개수를 정할지 정하고 만들어야할듯
        //  사료 이름 입력 → 가격 확인 → 자금 확인 → 재고/인벤토리 증가
    }

    // 사육장 확장
    private void buyEnclosure() {
        String itemName = "사육장 확장";

        // 가격 및 타입 조회
        ShopService.ItemPurchaseInfo info =
                ShopService.getPriceAndType(itemName, farm.getEnclosureLevel());

        if (info.type == ItemType.UNPURCHASABLE) {
            // 구매 실패
            return;
        }

        if (info.type != ItemType.FACILITY) {
            System.out.println("사육장 확장 정보가 올바르지 않습니다.");
            return;
        }

        int price = info.price;

        // 돈 차감
        if (!finance.subtractMoney(price)) {
            return; // 잔액 부족
        }

        // Farm.expandEnclosure()호출해서 사육장 확장
        boolean success = farm.expandEnclosure();

        if (!success) {
            // **예외처리 정상적인경우 이 코드는 실행되지 않음
            System.out.println("사육장 확장에 실패하여 돈을 환불합니다.");
            finance.addMoney(price);
        }
    }





}


