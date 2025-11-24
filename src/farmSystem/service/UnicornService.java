/* 임시 : 유니콘 상호작용
package farmSystem.service;


import Entity.livestock.Sheep;
import Entity.livestock.Livestock;
import Entity.livestock.Unicorn;
import farmSystem.Product;

public class UnicornService {

    // 유니콘 상호작용 시 HP 비용
    private static final int SHEARING_COST = 30;

    public void interactShear(Unicorn unicorn, Product productInventory) {

        // 1. 오늘 상호작용을 이미 했는지 확인
        if (unicorn.getInteractedToday()) {
            System.out.println("동물이 지쳐 오늘은 더 이상 상호작용 할 수 없습니다.");
        } else if (unicorn.getHp() < Livestock.MAX_HP) {
            System.out.println(unicorn.getName() +
                    ": HP가 " + Livestock.MAX_HP + "이 아니라 상호작용 할 수 없습니다. (현재 HP" + unicorn.getHp() + ")");
        } else {
            // 2. 상호작용이 가능하면, 서비스 클래스에서 직접 HP 비용 처리 (30 감소)
            unicorn.takeDamage(SHEARING_COST);

            // 3. 재고 업데이트 (양털 추가)
            productInventory.addWool(1); // 양털 재고 1개 추가
            unicorn.setInteractedToday(true); // 상호작용을 했으니 InteractedToday를 true로 변경
            System.out.println(unicorn.getName() + "과 상호작용하고 HP가 " + unicorn.getHp() + "으로 감소했습니다. 유니콘이 지쳤습니다.");

        }
    }
} */
