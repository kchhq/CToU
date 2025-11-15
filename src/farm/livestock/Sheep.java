package farm.livestock;

import farm.Product;

public class Sheep extends Livestock {
    // private int woolCount; 필드 제거됨

    public Sheep(String name) {
        super(name, "양");
    }

    // 🐑 상호작용 메서드: 털 깎기 및 Product 재고 업데이트
    public void interactShear(Product productInventory) {
        int currentHp = getHp();
        if (currentHp == MAX_HP) {
            takeDamage(50);
            productInventory.addWool(1); // 💡 Product에 양털 1개 추가 요청
        } else {
            System.out.println(getName() + ": 털을 깎을 준비가 안 됐습니다. (현재 HP" + currentHp + ")");
        }
    }
}