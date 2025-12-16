package breedingSystem.traits;

import farmSystem.Product;

public class TraitEffectContext {

    // =========================
    // HP(체력) 관련 제어 플래그
    // =========================

    // true면 상호작용 시 HP 감소를 아예 하지 않음
    private boolean cancelHpDamage = false;

    // null이 아니면 HP를 해당 값으로 강제 설정
    private Integer forceHpTo = null;

    // ====== 경주(유니콘) 관련 ======
    private Integer forceRaceRank = null;   // 1~5 중 강제 등수
    private Integer capWorstRaceRank = null; // "최소 3등" 같은 제한(숫자가 작을수록 좋음)


    // =========================
    // 생산물 관련 제어 플래그
    // =========================

    // true면 생산물 추가 자체를 취소
    private boolean cancelProductAdd = false;

    // true면 일반 알 대신 황금 알로 교체
    private boolean replaceWithGoldenEgg = false;

    // true면 일반 뿔 대신 황금 뿔로 교체
    private boolean replaceWithGoldenWool = false;

    private boolean replaceWithGoldenAntler = false;

    // 생산물 배수 (기본값 1배)
    private int productMultiplier = 1;


    // =========================
    // 참조 객체
    // =========================

    // 현재 상호작용에서 사용 중인 재고 객체
    private final Product productInventory;

    public TraitEffectContext(Product productInventory) {
        this.productInventory = productInventory;
    }


    // =========================
    // Getter (읽기 전용)
    // =========================

    public Product productInventory() { return productInventory; }
    public boolean isCancelHpDamage() { return cancelHpDamage; }
    public Integer getForceHpTo() { return forceHpTo; }
    public boolean isCancelProductAdd() { return cancelProductAdd; }
    public boolean isReplaceWithGoldenEgg() { return replaceWithGoldenEgg; }
    public boolean isReplaceWithGoldenWool() { return replaceWithGoldenWool; }
    public boolean isReplaceWithGoldenAntler() { return replaceWithGoldenAntler; }
    public int getProductMultiplier() { return productMultiplier; }

    //int가 아니라 Integer로 해야 함
    public Integer getForceRaceRank() { return forceRaceRank; }
    public Integer getCapWorstRaceRank() { return capWorstRaceRank; }


    // =========================
    // Setter / Command 메서드
    // (Trait에서 호출됨)
    // =========================

    // HP 감소를 완전히 무시하도록 요청
    public void cancelHpDamage() {
        this.cancelHpDamage = true;
    }

    // HP를 특정 값으로 강제 설정하도록 요청
    public void forceHpTo(int hp) {
        this.forceHpTo = hp;
    }

    // 생산물 추가 자체를 취소하도록 요청
    public void cancelProductAdd() {
        this.cancelProductAdd = true;
    }

    // 일반 알 대신 황금 알을 생성하도록 요청
    public void replaceWithGoldenEgg() {
        this.replaceWithGoldenEgg = true;
    }
    // 일반 양털 대신 황금양털을 생성하도록 요청
    public void replaceWithGoldenWool() {
        this.replaceWithGoldenWool = true;
    }

    // 일반 뿔 대신 황금 뿔을 생성하도록 요청
    public void replaceWithGoldenAntler() {
        this.replaceWithGoldenAntler = true;
    }

    // 생산물 배수를 증가시키도록 요청
    public void multiplyProduct(int factor) {
        if (factor > productMultiplier) {
            productMultiplier = factor;
        }
    }
    // 경주 등수를 바꿈
    public void forceRaceRank(int rank) {
        this.forceRaceRank = rank;
    }
    // 결과가 4,5등이면 3등으로 변경
    public void capWorstRaceRank(int worstRank) {
        this.capWorstRaceRank = worstRank;
    }
}
