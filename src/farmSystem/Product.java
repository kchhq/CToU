package farmSystem;

public class Product {

    private int antlerCount;
    private int woolCount;
    private int milkCount;
    private int eggCount;
    private int feedstuffCount;

    // 고가치 생산품들
    private int goldenEggCount;
    private int goldenWoolCount;
    private int goldenAntlerCount;

    public Product() {
        this.woolCount = 0;
        this.milkCount = 0;
        this.eggCount = 0;
        this.antlerCount = 0;
        this.feedstuffCount = 0;

        this.goldenEggCount = 0;
        this.goldenWoolCount = 0;
        this.goldenAntlerCount = 0;
    }

    // ------------------------------------
    // 상품 추가 (생산) 메서드
    // ------------------------------------

    // 🦌 사슴 뿔 추가
    public void addAntlers(int count) {
        if (count <= 0) return;
        this.antlerCount += count;
        System.out.println("📦 창고에 뿔 " + count + "개 추가.");
    }

    // ✨🦌 황금 뿔 추가
    public void addGoldenAntlers(int count) {
        if (count <= 0) return;
        this.goldenAntlerCount += count;
        System.out.println("📦 창고에 황금 뿔 " + count + "개 추가.");
    }

    // 🐑 양털 추가
    public void addWool(int count) {
        if (count <= 0) return;
        this.woolCount += count;
        System.out.println("📦 창고에 양털 " + count + "개 추가.");
    }

    // ✨🐑 황금 양털 추가
    public void addGoldenWool(int count) {
        if (count <= 0) return;
        this.goldenWoolCount += count;
        System.out.println("📦 창고에 황금 양털 " + count + "개 추가.");
    }

    // 🥛 우유 추가
    public void addMilk(int count) {
        if (count <= 0) return;
        this.milkCount += count;
        System.out.println("📦 창고에 우유 " + count + "L 추가.");
    }

    // 🥚 알 추가
    public void addEggs(int count) {
        if (count <= 0) return;
        this.eggCount += count;
        System.out.println("📦 창고에 알 " + count + "개 추가.");
    }

    // ✨🥚 황금 알 추가
    public void addGoldenEggs(int count) {
        if (count <= 0) return;
        this.goldenEggCount += count;
        System.out.println("📦 창고에 황금 알 " + count + "개 추가.");
    }

    // ------------------------------------
    // 상품 차감 (판매) 메서드
    // ShopService 방식(전량 판매)과 호환되게 "복수형" 유지
    // ------------------------------------

    /** 🥛 우유 재고를 요청 수량만큼 차감 */
    public void removeMilk(int count) {
        if (this.milkCount >= count) {
            this.milkCount -= count;
            System.out.printf(" 창고에서 [우유] %d L 판매.%n", count);
        } else {
            System.out.println("우유 재고 부족으로 판매 실패.");
        }
    }

    /** 🥚 알 재고를 요청 수량만큼 차감 */
    public void removeEggs(int count) {
        if (this.eggCount >= count) {
            this.eggCount -= count;
            System.out.printf(" 창고에서 [알] %d개 판매.%n", count);
        } else {
            System.out.println("알 재고 부족으로 판매 실패.");
        }
    }

    /** ✨🥚 황금 알 재고를 요청 수량만큼 차감 */
    public void removeGoldenEggs(int count) {
        if (this.goldenEggCount >= count) {
            this.goldenEggCount -= count;
            System.out.printf(" 창고에서 [황금 알] %d개 판매.%n", count);
        } else {
            System.out.println("황금 알 재고 부족으로 판매 실패.");
        }
    }

    /** 🐑 양털 재고를 요청 수량만큼 차감 */
    public void removeWool(int count) {
        if (this.woolCount >= count) {
            this.woolCount -= count;
            System.out.printf(" 창고에서 [양털] %d개 판매.%n", count);
        } else {
            System.out.println("양털 재고 부족으로 판매 실패.");
        }
    }

    /** ✨🐑 황금 양털 재고를 요청 수량만큼 차감 */
    public void removeGoldenWool(int count) {
        if (this.goldenWoolCount >= count) {
            this.goldenWoolCount -= count;
            System.out.printf(" 창고에서 [황금 양털] %d개 판매.%n", count);
        } else {
            System.out.println("황금 양털 재고 부족으로 판매 실패.");
        }
    }

    /** 🦌 뿔 재고를 요청 수량만큼 차감 */
    public void removeAntlers(int count) {
        if (this.antlerCount >= count) {
            this.antlerCount -= count;
            System.out.printf(" 창고에서 [뿔] %d개 판매.%n", count);
        } else {
            System.out.println("뿔 재고 부족으로 판매 실패.");
        }
    }

    /** ✨🦌 황금 뿔 재고를 요청 수량만큼 차감 */
    public void removeGoldenAntlers(int count) {
        if (this.goldenAntlerCount >= count) {
            this.goldenAntlerCount -= count;
            System.out.printf(" 창고에서 [황금 뿔] %d개 판매.%n", count);
        } else {
            System.out.println("황금 뿔 재고 부족으로 판매 실패.");
        }
    }

    // ------------------------------------
    // Getter 및 상태 표시
    // ------------------------------------

    public int getEggCount() { return eggCount; }
    public int getGoldenEggCount() { return goldenEggCount; }

    public int getWoolCount() { return woolCount; }
    public int getGoldenWoolCount() { return goldenWoolCount; }

    public int getMilkCount() { return milkCount; }

    public int getAntlerCount() { return antlerCount; }
    public int getGoldenAntlerCount() { return goldenAntlerCount; }

    // 전체 재고 조회
    public void displayAllProducts() {
        System.out.println("\n---  현재 제품 창고 ---");
        System.out.println("뿔: " + antlerCount + "개");
        System.out.println("황금뿔: " + goldenAntlerCount + "개");
        System.out.println("양털: " + woolCount + "개");
        System.out.println("황금양털: " + goldenWoolCount + "개");
        System.out.println("우유: " + milkCount + "L");
        System.out.println("알: " + eggCount + "개");
        System.out.println("황금알: " + goldenEggCount + "개");
        System.out.println("------------------------");
    }
}
