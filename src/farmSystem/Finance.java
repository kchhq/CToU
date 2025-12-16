package farmSystem;


public class Finance {

    private int money; // 농장의 현재 자금
    private Farm farm; // Farm 객체에 접근 (시스템 연동용)

    /** Finance 생성자 */
    public Finance(Farm farm, int startMoney) {
        this.farm = farm;
        this.money = startMoney;
        System.out.println("현재 돈: " + this.money + "원");
    }

    /** 현재 자금을 반환 */
    public int getMoney() {
        return money;
    }

    // ----------------------------------------------------
    // 💰 자금 추가/차감 메서드 (Marketsystem에서 호출될 예정)
    // ----------------------------------------------------

    /**
     * 돈을 증가시킵니다 (수익).
     * @param amount 추가할 금액
     */
    public void addMoney(int amount) {
        if (amount > 0) {
            this.money += amount;
            System.out.println("💰 " + amount + "원이 추가되었습니다. (현재 잔액: " + this.money + "원)");
        }
    }

    /**
     * 돈을 감소시킵니다 (지출).
     * @param amount 차감할 금액
     * @return 돈 차감 성공 시 true, 잔액 부족 시 false
     */
    public boolean subtractMoney(int amount) {
        if (amount <= 0) {
            System.out.println("0 이하의 금액은 차감할 수 없습니다.");
            return false;
        }

        if (this.money >= amount) {
            this.money -= amount;
            System.out.println("💸 " + amount + "원이 차감되었습니다. (현재 잔액: " + this.money + "원)");
            return true;
        } else {
            System.out.println("잔액 부족! " + amount + "원을 차감할 수 없습니다. (현재 잔액: " + this.money + "원)");
            return false;
        }
    }

}