package Entity.livestock;

import common.enums.PreferredFeed;

public abstract class Livestock {
    private String name;
    private String type;
    private int hp;
    private int stressIndex;

    public static final int MAX_HP = 100;
    public static final int MAX_STRESS_INDEX = 100;

    // 오늘 상호작용 했는지 여부
    private boolean interactedToday = false;
    // 오늘 사료를 줬는지 여부
    private boolean fedToday = false;

    protected Livestock(String name, String type){
        this.name = name;
        this.type = type;
        this.hp = 10;
        this.stressIndex = 0;
    }

    /**
     * 🍚 각 동물 종류별로 고유한 사료량을 반환하는 추상 메서드.
     * @return HP 회복량 (사료량)
     */
    protected abstract int getFeedAmount();
    protected abstract int getStressFromUnpreferredFeed();
    protected abstract int getStressDecreaseAmount();

    // 밥주기 : 사료량만큼 HP를 회복시킵니다.
    public void feed(PreferredFeed feedType){
        if(fedToday){
            System.out.println("동물이 배가 부른지 사료를 먹지 않습니다.");
            return;
        } else {
            // **추상 메서드를 호출하여 각 동물의 고유값을 사용합니다.**
            if (feedType != getPreferredFeed()) {
                increaseStress(getStressFromUnpreferredFeed()); // 비선호 음식: 스트레스 지수 상승
                System.out.println("🚫 주의! " + name + "에게 선호하지 않는 사료를 주어 스트레스 지수가 상승했습니다. (현재 스트레스: " + this.stressIndex + ")");
            } else {
                // 선호 음식: 스트레스 지수 감소 (비선호 감소량의 절반)
                decreaseStress(getStressDecreaseAmount() / 2);
                System.out.println("✅ " + name + "에게 선호 사료를 주었습니다. 스트레스 지수가 소폭 감소했습니다. (현재 스트레스: " + this.stressIndex + ")");
            }

            // HP 회복 로직
            int amount = getFeedAmount();
            int oldHp = this.hp;
            this.hp = Math.min(MAX_HP, this.hp + amount);
            int recoveredHp = this.hp - oldHp;

            System.out.println(name + " 에게 밥을 주었습니다. HP가 " + recoveredHp + "만큼 회복하여 현재 HP는 " + hp + "이(가) 되었습니다.");
            setFedToday(true);
        }
    }


    //공통기능

    public void displayStatus() {
        System.out.println("[" + type + "] " + name
                + ", hp=" + hp + "/" + MAX_HP
                + ", 스트레스=" + stressIndex + "/" + MAX_STRESS_INDEX); // 🐂 스트레스 지수 표시 추가
    }


    // 상호작용시 피 깎임
    /**
     * 동물의 HP를 감소시킵니다.
     * @param dmg 감소시킬 HP 양
     */

    public void takeDamage(int dmg) {
        if (dmg <= 0) return;
        this.hp = Math.max(0, this.hp - dmg);
        System.out.println(name + "의 HP가 깎였습니다. HP : " + this.hp);
    }

    // 🧼 케이지 청소 및 방문 상호작용
    public void cleanAndVisit() {
        int decreaseAmount = getStressDecreaseAmount(); // **추상 메서드를 호출하여 고유값을 사용**
        decreaseStress(decreaseAmount);
        setInteractedToday(true);

        System.out.println("🤗 " + name + "을(를) 방문하고 사육장을 청소했습니다. 스트레스 지수가 "
                + decreaseAmount + "만큼 감소했습니다. (현재 스트레스: " + this.stressIndex + ")");
    }

    protected void increaseStress(int amount) {
        if (amount <= 0) return;
        this.stressIndex = Math.min(MAX_STRESS_INDEX, this.stressIndex + amount);
    }

    /**
     * ⬇️ 스트레스 지수를 감소시키는 헬퍼 메서드. 0 미만으로 내려가지 않습니다.
     * @param amount 감소시킬 스트레스 양
     */
    protected void decreaseStress(int amount) {
        if (amount <= 0) return;
        this.stressIndex = Math.max(0, this.stressIndex - amount);
    }

    /**
     * 동물의 현재 HP가 최대 HP인지 확인합니다.
     * 상호작용 가능 여부를 판단하는 공통 로직입니다.
     * @return HP가 maxHp와 같으면 true, 아니면 false
     */
    // 오늘 상호작용을 하지 않았고 HP가 MAX이면 상호작용이 가능
    public boolean isReadyForInteraction() {
        return !interactedToday && this.hp == this.MAX_HP;
    }

    // 오늘 상호작용 여부 getter : service에서 쓰임
    public boolean getInteractedToday() {
        return interactedToday;
    }
    // 오늘 상호작용 여부 setter : Nextday()에서 쓰임
    public void setInteractedToday(boolean interactedToday) {
        this.interactedToday = interactedToday;
    }
    // 오늘 사료 급여 여부 getter
    public boolean getFedToday() {
        return fedToday;
    }
    // 오늘 사료 급여 여부 setter
    public void setFedToday(boolean fedToday) {
        this.fedToday = fedToday;
    }


    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getStressIndex() { return stressIndex; }

    public abstract PreferredFeed getPreferredFeed();

}