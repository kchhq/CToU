package farm.livestock;

import farm.Product;


public class Deer extends Livestock {

    public Deer(String name) {
        super(name, "사슴");
    }

    // 🦌 상호작용 메서드: 뿔 자르기 및 Product 재고 업데이트

    /**
     * 상호작용 키로 호출되는 메서드. 뿔을 자르고 Product 객체에 1개를 추가합니다.
     * @param productInventory Product 객체 (재고 관리 시스템)
     */
    public void interactCutAntlers(Product productInventory) {
        // HP가 MAX_HP(100)일 때만 가능
        if (getHp() == MAX_HP) {

            // 1. HP 감소 (생산 비용)
            takeDamage(50);

            // 2. Product 재고 업데이트
            // Note: Product 클래스에 addAntlers(int count) 메서드가 있다고 가정합니다.
            productInventory.addAntlers(1);

            System.out.println(getName() + "의 뿔을 자르고 HP가 50으로 감소했습니다. 🦌");

        } else {
            System.out.println(getName() + ": HP가 " + MAX_HP + "이 아니라 뿔을 자를 수 없습니다. (현재 HP" + getHp() + ")");
        }
    }
}