package managementSystem.marketSystem;

import managementSystem.farmSystem.Farm;
import managementSystem.farmSystem.Finance;
import managementSystem.farmSystem.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import Entity.enums.ItemType;


public class ShopService {

    // ----------------------------------------------------
    // 1. 상수 및 Enum 정의
    // ----------------------------------------------------
    private static final int MAX_ENCLOSURE_LEVEL = 5;

    public static class ItemPurchaseInfo {
        public final ItemType type;
        public final int price;

        public ItemPurchaseInfo(ItemType type) {
            this.type = type;
            this.price = 0;
        }

        public ItemPurchaseInfo(ItemType type, int price) {
            this.type = type;
            this.price = price;
        }
    }

    // ----------------------------------------------------
    // 2. 가격 상수 맵 정의
    // ----------------------------------------------------

    // 2-1. 동물 구매 가격
    public static final Map<String, Integer> ANIMAL_PRICES;
    static {
        Map<String, Integer> aMap = new HashMap<>();
        aMap.put("Chicken", 50);
        aMap.put("Sheep", 100);
        aMap.put("Cow", 200);
        aMap.put("Deer", 150);
        aMap.put("Unicorn",3000);
        ANIMAL_PRICES = Collections.unmodifiableMap(aMap);
    }

    // 2-2. 사료 가격
    public static final Map<String, Integer> ITEM_PRICES;
    static {
        Map<String, Integer> iMap = new HashMap<>();
        iMap.put("일반 사료", 10);
        ITEM_PRICES = Collections.unmodifiableMap(iMap);
    }

    // 2-3. 시설 및 확장 가격
    public static final Map<String, Integer> FACILITY_PRICES;
    static {
        Map<String, Integer> fMap = new HashMap<>();
        fMap.put("사육장 확장", 300);
        FACILITY_PRICES = Collections.unmodifiableMap(fMap);
    }

    // 2-4. 판매 가격
    public static final Map<String, Integer> SELL_PRICES;
    static {
        Map<String, Integer> sMap = new HashMap<>();
        sMap.put("양털", 15);
        sMap.put("계란", 3);
        sMap.put("우유", 5);
        sMap.put("뿔", 120);
        SELL_PRICES = Collections.unmodifiableMap(sMap);
    }

    // ----------------------------------------------------
    // 3. 구매 정보 조회 메서드
    // ----------------------------------------------------

    /**
     * 특정 상품의 가격, 종류, 구매 가능 여부를 ItemPurchaseInfo 객체로 반환합니다.
     */
    public static ItemPurchaseInfo getPriceAndType(String itemName, int currentLevel) {
        Integer price;

        // 1. 동물 가격 확인
        if ((price = ANIMAL_PRICES.get(itemName)) != null) {
            return new ItemPurchaseInfo(ItemType.ANIMAL, price);
        }

        // 2. 아이템 가격 확인
        if ((price = ITEM_PRICES.get(itemName)) != null) {
            return new ItemPurchaseInfo(ItemType.FEED, price);
        }

        // 3. 시설 가격 확인
        if ((price = FACILITY_PRICES.get(itemName)) != null) {
            // 사육장 확장에 대한 특별 체크 로직
            if (itemName.equals("사육장 확장")) {
                if (currentLevel >= MAX_ENCLOSURE_LEVEL) {
                    System.out.println("❌ 사육장이 이미 최대 레벨(" + MAX_ENCLOSURE_LEVEL + ")입니다. 더 이상 확장할 수 없습니다.");
                    return new ItemPurchaseInfo(ItemType.UNPURCHASABLE);
                }
            }
            return new ItemPurchaseInfo(ItemType.FACILITY, price);
        }

        // 4. 상품을 찾지 못한 경우
        System.out.println("❌ 상품 [" + itemName + "]을(를) 찾을 수 없습니다.");
        return new ItemPurchaseInfo(ItemType.NOT_FOUND);
    }

    // ----------------------------------------------------
    // 4. 판매 처리 메서드
    // ----------------------------------------------------

    /**
     * 농장의 생산물(Product)을 판매하고 총 수익을 Finance에 추가합니다.
     */
    public static void sellProducts(Finance finance, Farm farm) {
        int totalRevenue = 0;
        Product product = farm.getProductInventory();

        System.out.println("\n===== 생산물 판매 시작 =====");

        for (String item : SELL_PRICES.keySet()) {
            int count = 0;

            // 재고 수량 조회 및 제거 로직 분리
            switch (item) {
                case "Wool":
                    count = product.getWoolCount();
                    if (count > 0) product.removeWool(count);
                    break;
                case "Egg":
                    count = product.getEggCount();
                    if (count > 0) product.removeEggs(count);
                    break;
                case "Milk":
                    count = product.getMilkCount();
                    if (count > 0) product.removeMilk(count);
                    break;
                case "Antler":
                    count = product.getAntlerCount();
                    if (count > 0) product.removeAntlers(count);
                    break;
            }

            if (count > 0) {
                int price = SELL_PRICES.get(item);
                int revenue = count * price;

                totalRevenue += revenue;
                System.out.println("✅ " + item + " " + count + "개 판매 완료. (+" + revenue + "원)");
            }
        }

        if (totalRevenue > 0) {
            finance.addMoney(totalRevenue);
        } else {
            System.out.println("판매할 생산물이 없습니다.");
        }

        System.out.println("===== 생산물 판매 완료 =====");
    }
}