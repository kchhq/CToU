package farm.livestock;

import farm.Product;

public class Chicken extends Livestock {

    public Chicken(String name) {
        super(name, "닭");
    }

    // 🐔 상호작용 메서드: 알 생산 및 Product 재고 업데이트

    public void interactLayEgg(Product productInventory) {
        // HP가 MAX_HP(100)일 때만 가능
        if (getHp() == MAX_HP) {

            // 1. HP 감소 (생산 비용)
            takeDamage(50);

            // 2. Product 재고 업데이트
            productInventory.addEggs(1); // Product 클래스의 addEggs 메서드 호출

        } else {
            System.out.println(getName() + ": HP가 " + MAX_HP + "이 아니라 알을 낳을 수 없습니다. (현재 HP" + getHp() + ")");
        }
    }
}