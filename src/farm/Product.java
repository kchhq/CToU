package farm;

public class Product {

    private int antlerCount;
    private int woolCount;
    private int milkCount;
    private int eggCount;

    public Product() {
        this.woolCount = 0;
        this.milkCount = 0;
        this.eggCount = 0;
        this.antlerCount = 0;
    }

    // ------------------------------------
    // 상품 추가 (생산) 메서드
    // ------------------------------------


    // 🦌 사슴 뿔 추가 메서드
    public void addAntlers(int count) {
        this.antlerCount += count;
        System.out.println("📦 창고에 뿔 " + count + "개 추가.");
    }

    public void addWool(int count) {
        this.woolCount += count;
        System.out.println("📦 창고에 양털 " + count + "개 추가.");
    }

    public void addMilk(int count) {
        this.milkCount += count;
        System.out.println("📦 창고에 우유 " + count + "L 추가.");
    }

    public void addEggs(int count) {
        this.eggCount += count;
        System.out.println("📦 창고에 알 " + count + "개 추가.");
    }

    // ------------------------------------
    // 상품 차감 (판매) 메서드
    // ------------------------------------

    /** 뿔 재고를 요청 수량만큼 차감 */
    public void removeAntlers(int count) {
        if (this.antlerCount >= count) {
            this.antlerCount -= count;
            System.out.printf("💸 창고에서 [뿔] %d개 판매.%n", count);
        } else {
            System.out.println("❌ 뿔 재고 부족으로 판매 실패.");
        }
    }

    /** 양털 재고를 요청 수량만큼 차감 */
    public void removeWool(int count) {
        if (this.woolCount >= count) {
            this.woolCount -= count;
            System.out.printf("💸 창고에서 [양털] %d개 판매.%n", count);
        } else {
            System.out.println("❌ 양털 재고 부족으로 판매 실패.");
        }
    }

    /** 우유 재고를 요청 수량만큼 차감 */
    public void removeMilk(int count) {
        if (this.milkCount >= count) {
            this.milkCount -= count;
            System.out.printf("💸 창고에서 [우유] %d L 판매.%n", count);
        } else {
            System.out.println("❌ 우유 재고 부족으로 판매 실패.");
        }
    }

    /** 알 재고를 요청 수량만큼 차감 */
    public void removeEggs(int count) {
        if (this.eggCount >= count) {
            this.eggCount -= count;
            System.out.printf("💸 창고에서 [알] %d개 판매.%n", count);
        } else {
            System.out.println("❌ 알 재고 부족으로 판매 실패.");
        }
    }

    // ------------------------------------
    // Getter 및 상태 표시
    // ------------------------------------


    public int getWoolCount() { return woolCount; }
    public int getMilkCount() { return milkCount; }
    public int getEggCount() { return eggCount; }
    public int getAntlerCount() { return antlerCount; }

    public void displayAllProducts() {
        System.out.println("\n--- 🧺 현재 제품 창고 ---");
        System.out.println("뿔: " + antlerCount + "개");
        System.out.println("양털: " + woolCount + "개");
        System.out.println("우유: " + milkCount + "L");
        System.out.println("알: " + eggCount + "개");
        System.out.println("------------------------");
    }

}