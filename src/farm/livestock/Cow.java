package farm.livestock;

import farm.Product;

public class Cow extends Livestock {


    public Cow(String name) {
        super(name, "소");
    }

    // 🐄 상호작용 메서드: 우유 생산 및 Product 재고 업데이트
    public void interactMilk(Product productInventory) {
        if (getHp() == MAX_HP) {
            takeDamage(50);
            productInventory.addMilk(1); // 💡 Product에 우유 1L 추가 요청

        } else {
            System.out.println(getName() + ": HP가 " + MAX_HP + "이 아니라 우유를 짤 수 없습니다.");
        }
    }
}