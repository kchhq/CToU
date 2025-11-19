package managementSystem.farmSystem.service;

import Entity.livestock.Sheep;
import Entity.livestock.Livestock;
import managementSystem.farmSystem.Product;

public class SheepService {

    // 🐑 털 깎기 시 HP 비용
    private static final int SHEARING_COST = 30;

    /**
     * 양과의 상호작용(털 깎기)을 처리하고 재고를 업데이트합니다.
     * @param sheep 대상 Sheep 객체
     * @param productInventory Product 객체 (재고 관리)
     */
    public void interactShear(Sheep sheep, Product productInventory) {

        // 1. HP 100인지 공통 상태 확인
        if (sheep.isReadyForInteraction()) {

            // 2. 상호작용이 가능하면, 서비스 클래스에서 직접 HP 비용 처리 (30 감소)
            sheep.takeDamage(SHEARING_COST);

            // 3. 재고 업데이트 (양털 추가)
            productInventory.addWool(1);

            System.out.println(sheep.getName() + "의 털을 깎고 HP가 " + sheep.getHp() + "으로 감소했습니다. 🧶");

        } else {
            System.out.println(sheep.getName() +
                    ": HP가 " + Livestock.MAX_HP + "이 아니라 털을 깎을 수 없습니다. (현재 HP" + sheep.getHp() + ")");
        }
    }
}