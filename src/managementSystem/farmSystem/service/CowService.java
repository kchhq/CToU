package managementSystem.farmSystem.service;

import Entity.livestock.Cow;
import Entity.livestock.Livestock;
import managementSystem.farmSystem.Product;

public class CowService {

    // 🐮 착유 시 HP 비용
    private static final int MILKING_COST = 40;

    /**
     * 소와의 상호작용(착유)을 처리하고 재고를 업데이트합니다.
     * @param cow 대상 Cow 객체
     * @param productInventory Product 객체 (재고 관리)
     */
    public void interactMilk(Cow cow, Product productInventory) {

        // 1. HP 100인지 공통 상태 확인
        if (cow.isReadyForInteraction()) {

            // 2. 상호작용이 가능하면, 서비스 클래스에서 직접 HP 비용 처리 (20 감소)
            cow.takeDamage(MILKING_COST);

            // 3. 재고 업데이트 (우유 추가)
            productInventory.addMilk(1);

            System.out.println(cow.getName() + "를 착유하고 HP가 " + cow.getHp() + "으로 감소했습니다. 🥛");

        } else {
            System.out.println(cow.getName() +
                    ": HP가 " + Livestock.MAX_HP + "이 아니라 착유할 수 없습니다. (현재 HP" + cow.getHp() + ")");
        }
    }
}