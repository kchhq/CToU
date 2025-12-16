package mainSystem.controller;

import common.enums.*;
import farmSystem.Farm;
import farmSystem.Finance;
import Entity.livestock.Livestock;
import Entity.livestock.*;
import farmSystem.service.*;
import farmSystem.service.ChickenService;
import marketSystem.ShopService;
import farmSystem.Product;
import java.util.List;
import java.util.Scanner;
import breedingSystem.BreedingService;
import breedingSystem.traits.*;


public class MenuController {

    // 시작
    public void start() {
        while (state != MenuState.EXIT) {
            switch (state) {
                case MAIN -> showMainMenu();
                case FARM -> showFarmMenu();
                case SHOP -> showShopMenu();
            }
        }
    }

    // 현재 농장 상태와 자금 상태
    private final Farm farm;
    private final Finance finance;

    // 교배 시스템
    private final BreedingService breedingService = new BreedingService();


    // 상호작용을 위해 객체 생성
    private final ChickenService chickenService = new  ChickenService();
    private final CowService cowService = new  CowService();
    private final DeerService deerService = new  DeerService();
    private final SheepService sheepService = new  SheepService();
    // private final UnicornService unicornService = new UnicornService(); 유니콘 추가하면 씀

    // 스캐너, Menustate, 날짜
    private final Scanner scanner = new Scanner(System.in);
    private MenuState state = MenuState.MAIN;
    private int day = 1;

    public MenuController(Farm farm, Finance finance) {
        this.farm = farm;
        this.finance = finance;
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
                ======== 메인 메뉴 ========
                1. 농장 현황 보기
                2. 상점 가기
                3. 잠 자기
                4. 게임 종료
                """);
        System.out.print("선택 : ");
        int choice = getChoice();

        switch (choice) {
            case 1 -> {
                System.out.println(">>> 농장 현황 보기\n");
                state = MenuState.FARM;
            }
            case 2 -> {
                System.out.println(">>> 상점 가기\n");
                state = MenuState.SHOP;
            }
            case 3 -> {
                System.out.println(">>> 잠 자기\n");
                nextDay();
            }
            case 4 -> {
                System.out.println(">>> 게임 종료\n");
                state = MenuState.EXIT;
            }
            default -> System.out.println("잘못된 입력입니다.\n");
        }
    }

    private void showFarmMenu() {
        System.out.println("""
                ======== 농장 ========
                1. 동물 상태 확인
                2. 사료 주기
                3. 사육장 청소/방문
                4. 수확하기
                5. 재고 확인
                6. 교배 시도
                7. 돌아가기
                """);

        System.out.print("선택 : ");
        int choice = getChoice();

        switch (choice) {
            case 1 -> {
                System.out.println(">>> 동물 상태 확인\n");
                farm.displayAllAnimalsStatus(); // 동물 상태 출력
            }
            case 2 -> {
                System.out.println(">>> 사료 주기\n");
                feedAllAnimals();
            }      // 모든 동물에 사료 주기
            case 3 -> {
                System.out.println("선택 : 사육장 청소/방문\n");
                cleanAndVisitAnimal();
            }
            case 4 -> {
                System.out.println(">>> 수확하기\n");
                interactWithAnimals();
            } // 수확 후 Product/Finance에 반영
            case 5 -> {
                System.out.println(">>> 재고 확인\n");
                farm.getProductInventory().displayAllProducts();
            }
            case 6 -> {
                System.out.println(">>> 교배하기\n");
                breedAnimals();
            }
            case 7 -> {
                System.out.println(">>> 메인화면으로 돌아가기\n");
                state = MenuState.MAIN;
            }// 메인화면으로 돌아가기
            default -> System.out.println("잘못된 입력입니다.\n");
        }
    }

    // 상점 메뉴
    private void showShopMenu() {
        System.out.println("""
                ======== 상점 ========
                1. 구매하기
                2. 판매하기
                3. 돌아가기
                """);

        System.out.print("선택 : ");
        int choice = getChoice();

        switch (choice) {
            case 1 -> {
                System.out.println(">>> 구매하기\n");
                showBuyMenu();
            }                           // 구매 화면 진입
            case 2 -> {
                System.out.println(">>> 판매하기\n");
                ShopService.sellProducts(finance, farm);
            } // 판매 화면 진입
            case 3 -> {
                System.out.println(">>> 메인 화면으로 돌아가기\n");
                state = MenuState.MAIN;
            }                  // 메인화면으로 돌아가기
            default -> System.out.println("잘못된 입력입니다.\n");
        }
    }

    // 잠 자기 선택
    private void nextDay() {
        // 날짜를 1 증가시키고
        day++;
        for (Livestock animal : farm.getAnimals()) {
            //오늘 상호작용 했는지 여부를 false로 변경
            animal.setInteractedToday(false);
            //오늘 사료 급여 여부를 false로 변경
            animal.setFedToday(false);
            //오늘 사육장 청소 여부를 false로 변경
            animal.setCleanedToday(false);
        }
        System.out.println("\n=== 하루가 지나" + day + "일차가 밝았습니다. ===\n");
        state = MenuState.MAIN;
    }

    // 모든 동물 사료 주기
    private void feedAllAnimals() {
        List<Livestock> animals = farm.getAnimals();

        // 예외처리 : 사육장에 동물이 없음
        if (animals.isEmpty()) {
            System.out.println("사육장에 동물이 없습니다.");
            return;
        }

        // 1. 사용자에게 급여할 사료 종류를 선택하도록 요청합니다.
        PreferredFeed selectedFeed = selectFeedType();

        // 사용자가 취소를 선택한 경우
        if (selectedFeed == null) {
            System.out.println("사료 주기를 취소합니다.");
            return;
        }

        System.out.println("\n[모든 동물에게 사료 (" + selectedFeed.name() + ")를 급여합니다]");
        for (Livestock animal : animals) {
            // ★ 수정된 부분: selectedFeed 인수를 전달합니다.
            animal.feed(selectedFeed);
        }
        System.out.println();
    }

    // 헬퍼 메서드: 사용자에게 사료 종류를 선택하게 함
    private PreferredFeed selectFeedType() {
        while (true) {
            System.out.println("======= 어떤 동물이 선호하는 사료를 주시겠습니까? =======");

            // PreferredFeed Enum의 값들을 출력
            PreferredFeed[] feeds = PreferredFeed.values();
            for (int i = 0; i < feeds.length; i++) {
                System.out.println((i + 1) + ". " + feeds[i].name());
            }
            System.out.println((feeds.length + 1) + ". 취소하고 돌아가기");

            System.out.print("선택 : ");
            int choice = getChoice();

            if (choice > 0 && choice <= feeds.length) {
                return feeds[choice - 1];
            } else if (choice == feeds.length + 1) {
                return null; // 취소
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }


    // 모든 동물 상호작용
    private void interactWithAnimals() {
        List<Livestock> animals = farm.getAnimals();

        if (animals.isEmpty()) {
            System.out.println("사육장에 동물이 없습니다.");
            return;
        }

        Product inventory = farm.getProductInventory();

        System.out.println("\n[동물들과 상호작용을 시도합니다.]");
        for (Livestock animal : animals) {

            // 1. 수확 로직 (각 동물별 서비스 호출)
            if(animal instanceof Chicken chicken) {
                chickenService.interactHarvestEgg(chicken, inventory);
            } else if (animal instanceof Cow cow) {
                cowService.interactMilk(cow, inventory);
            } else if (animal instanceof Sheep sheep) {
                sheepService.interactShear(sheep, inventory);
            } else if (animal instanceof Deer deer) {
                deerService.interactCutAntlers(deer, inventory);
            }
        }
        System.out.println();
    }

    // 사육장 청소/방문
    private void cleanAndVisitAnimal() {
        var animals = farm.getAnimals();

        if (animals.isEmpty()) {
            System.out.println("농장에 동물이 없습니다.\n");
            return;
        }

        System.out.println("======= 청소/방문할 동물 선택 =======");
        for (int i = 0; i < animals.size(); i++) {
            Livestock a = animals.get(i);
            System.out.printf("%d) [%s] %s (스트레스:%d/%d)%n",
                    i + 1,
                    a.getClass().getSimpleName(),
                    a.getName(),
                    a.getStressIndex(),
                    Livestock.MAX_STRESS_INDEX
            );
        }
        System.out.println((animals.size() + 1) + ") 돌아가기");
        System.out.print("선택 : ");

        int choice = getChoice();
        int idx = choice - 1;

        if (choice == animals.size() + 1) {
            System.out.println("취소\n");
            return;
        }
        if (idx < 0 || idx >= animals.size()) {
            System.out.println("잘못된 입력입니다.\n");
            return;
        }

        Livestock target = animals.get(idx);

        // 하루 1회 제한(이미 청소/방문 했으면 막기)
        if (target.getCleanedToday()) {
            System.out.println("오늘은 이미 청소를 했습니다.\n");
            return;
        }
        target.cleanAndVisit();


        // 여기서 실제 호출
        target.cleanAndVisit();
        System.out.println();
    }


    // 교배 로직
    private void breedAnimals() {
        List<Livestock> animals = farm.getAnimals();

        if (animals.size() < 2) {
            System.out.println("교배하려면 동물이 최소 2마리 있어야 합니다.");
            return;
        }
        if (animals.size() >= farm.getMaxCapacity()) {
            System.out.println("빈 사육장이 없습니다. 사육장을 확장한 뒤 교배하세요.");
            return;
        }

        // 1) 전체 목록 출력
        System.out.println("======= 보유 동물 목록 =======");
        for (int i = 0; i < animals.size(); i++) {
            Livestock a = animals.get(i);
            System.out.printf("%d) [%s] %s (공통:%s, 종:%s)%n",
                    i + 1,
                    a.getClass().getSimpleName(),
                    a.getName(),
                    a.getCommonTrait() == null ? "없음" : a.getCommonTrait().id(),
                    a.getSpeciesTrait() == null ? "없음" : a.getSpeciesTrait().id()
            );
        }

        // 2) 첫 번째 부모 선택
        System.out.print("첫 번째 부모 번호: ");
        int idx1 = getChoice() - 1;
        if (idx1 < 0 || idx1 >= animals.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }
        Livestock p1 = animals.get(idx1);

        // 3) 두 번째 부모 후보(같은 종)만 보여주기
        System.out.println("\n======= 같은 종 후보 =======");
        boolean hasCandidate = false;
        for (int i = 0; i < animals.size(); i++) {
            if (i == idx1) continue;
            Livestock a = animals.get(i);
            if (a.getClass().equals(p1.getClass())) {
                hasCandidate = true;
                System.out.printf("%d) [%s] %s (공통:%s, 종:%s)%n",
                        i + 1,
                        a.getClass().getSimpleName(),
                        a.getName(),
                        a.getCommonTrait() == null ? "없음" : a.getCommonTrait().id(),
                        a.getSpeciesTrait() == null ? "없음" : a.getSpeciesTrait().id()
                );
            }
        }
        if (!hasCandidate) {
            System.out.println("같은 종의 다른 동물이 없습니다.");
            return;
        }

        System.out.print("두 번째 부모 번호: ");
        int idx2 = getChoice() - 1;
        if (idx2 < 0 || idx2 >= animals.size() || idx2 == idx1) {
            System.out.println("잘못된 번호입니다.");
            return;
        }
        Livestock p2 = animals.get(idx2);

        if (!p1.getClass().equals(p2.getClass())) {
            System.out.println("같은 종끼리만 교배할 수 있습니다.");
            return;
        }

        // 4) 교배 실행
        Livestock child;
        try {
            child = breedingService.breed(p1, p2);
        } catch (Exception e) {
            System.out.println("교배 실패: " + e.getMessage());
            return;
        }

        // 5) 농장에 추가
        boolean added = farm.addAnimal(child);
        if (!added) {
            System.out.println("사육장 수용량 때문에 자식을 추가하지 못했습니다.");
            return;
        }

        System.out.printf("🎉 교배 성공! [%s] %s 탄생! (공통:%s, 종:%s)%n",
                child.getClass().getSimpleName(),
                child.getName(),
                child.getCommonTrait() == null ? "없음" : child.getCommonTrait().id(),
                child.getSpeciesTrait() == null ? "없음" : child.getSpeciesTrait().id()
        );
    }


    // 구매 화면
    private void showBuyMenu() {
        while (true) {
            System.out.println("""
                ======== 구매하기 ========
                1. 동물 구매
                2. 사육장 확장
                3. 돌아가기
                """);
            System.out.print("선택 : ");
            int choice = getChoice();

            switch (choice) {
                case 1 -> {
                    System.out.println(">>> 동물 구매\n");
                    buyAnimal();
                }       // 동물 관련
                case 2 -> {
                    System.out.println(">>> 사육장 확장\n");
                    buyEnclosure();
                }    // 사육장 확장
                case 3 -> {
                    System.out.println("상점 메인으로 돌아갑니다.\n");
                    return;                 // 구매 화면으로 돌아감
                }
                default -> System.out.println("잘못된 입력입니다.\n");
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
            ======== 동물 구매 ========
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
                System.out.println("동물 구매를 취소합니다.\n");
                return;
            }
            default -> {
                System.out.println("잘못된 입력입니다.\n");
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
            case "Unicorn" -> newAnimal = new Unicorn(name,"Unicorn");
            default -> {
                System.out.println("알 수 없는 동물 타입입니다.");
                finance.addMoney(price); // 예외처리
                return;
            }
        }

        assignCommonTrait(newAnimal);
        assignSpeciesTrait(newAnimal);

        // addAnimal() 써서 동물 추가
        boolean added = farm.addAnimal(newAnimal);

        // 사육장 부족 등으로 인해 동물 구매 실패 시 환불
        if (!added) {
            System.out.println("동물을 사육장에 추가하지 못해 돈을 환불합니다.");
            finance.addMoney(price);
        }
    }
    // 공통특성을 부여하는 메서드
    private void assignCommonTrait(Livestock animal) {
        CommonTrait[] traits = CommonTrait.values(); // NORMAL, LIVELY, STRESSFULL
        int idx = java.util.concurrent.ThreadLocalRandom.current().nextInt(traits.length);
        animal.setCommonTrait(traits[idx]);
    }

    private void assignSpeciesTrait(Livestock animal) {
        if (!TraitRng.chance(30)) return;

        if (animal instanceof Unicorn) {
            UnicornTrait[] traits = UnicornTrait.values();
            animal.setSpeciesTrait(traits[java.util.concurrent.ThreadLocalRandom.current().nextInt(traits.length)]);
            return;
        }

        if (animal instanceof Chicken) {
            ChickenTrait[] traits = ChickenTrait.values();
            animal.setSpeciesTrait(traits[java.util.concurrent.ThreadLocalRandom.current().nextInt(traits.length)]);
            return;
        }

        if (animal instanceof Sheep) {
            SheepTrait[] traits = SheepTrait.values();
            animal.setSpeciesTrait(traits[java.util.concurrent.ThreadLocalRandom.current().nextInt(traits.length)]);
            return;
        }

        if (animal instanceof Cow) {
            CowTrait[] traits = CowTrait.values();
            animal.setSpeciesTrait(traits[java.util.concurrent.ThreadLocalRandom.current().nextInt(traits.length)]);
            return;
        }

        if (animal instanceof Deer) {
            DeerTrait[] traits = DeerTrait.values();
            animal.setSpeciesTrait(traits[java.util.concurrent.ThreadLocalRandom.current().nextInt(traits.length)]);
        }
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
            // ** 예외처리 정상적인경우 이 코드는 실행되지 않음
            System.out.println("사육장 확장에 실패하여 돈을 환불합니다.");
            finance.addMoney(price);
        }
    }
}