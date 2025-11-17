package Entity.livestock;

public abstract class Livestock {
    private String name;
    private String type;
    private int hp;

    public static final int MAX_HP = 100;

    protected Livestock(String name, String type){
        this.name = name;
        this.type = type;
        this.hp = 10;
    }

    /**
     * 🍚 각 동물 종류별로 고유한 사료량을 반환하는 추상 메서드.
     * @return HP 회복량 (사료량)
     */
    protected abstract int getFeedAmount(); // <--- 이 메서드를 추가합니다.

    // 밥주기 : 사료량만큼 HP를 회복시킵니다.
    public void feed(){
        int amount = getFeedAmount(); // 각 동물의 사료량(회복량)을 가져옵니다.

        // HP 회복 계산: HP를 amount만큼 더하되, MAX_HP를 초과할 수 없습니다.
        int oldHp = this.hp;
        this.hp = Math.min(MAX_HP, this.hp + amount);

        // 실제로 회복된 HP 양 계산
        int recoveredHp = this.hp - oldHp;

        System.out.println(name +" 에게 밥을 주었습니다. HP가 " + recoveredHp + "만큼 회복하여 현재 HP는 " + hp + "이(가) 되었습니다.");
    }


    //공통기능

    public void displayStatus() {
        System.out.println("[" + type + "] " + name
                + ", hp=" + hp + "/" + MAX_HP);
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

    /**
     * 동물의 현재 HP가 최대 HP인지 확인합니다.
     * 상호작용 가능 여부를 판단하는 공통 로직입니다.
     * @return HP가 maxHp와 같으면 true, 아니면 false
     */
    public boolean isReadyForInteraction() {
        return this.hp == this.MAX_HP;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
}