package managementSystem.farmSystem.service;

import Entity.livestock.Deer;
import Entity.livestock.Livestock;
import managementSystem.farmSystem.Product;

public class DeerService {

    // 🦌 뿔 깎기 시 HP 비용
    private static final int ANTLER_CUT_COST = 50;

    /**
     * 사슴과의 상호작용(뿔 깎기)을 처리하고 재고를 업데이트합니다.
     * @param deer 대상 Deer 객체
     * @param productInventory Product 객체 (재고 관리)
     */
    public void interactCutAntlers(Deer deer, Product productInventory) {

        // 1. HP 100인지 공통 상태 확인
        if (deer.isReadyForInteraction()) {

            // 2. 상호작용이 가능하면, 서비스 클래스에서 직접 HP 비용 처리 (50 감소)
            deer.takeDamage(ANTLER_CUT_COST);

            // 3. 재고 업데이트 (뿔 추가)
            productInventory.addAntlers(1);

            System.out.println(deer.getName() + "의 뿔을 자르고 HP가 " + deer.getHp() + "으로 감소했습니다. 🦌");

        } else {
            System.out.println(deer.getName() +
                    ": HP가 " + Livestock.MAX_HP + "이 아니라 뿔을 자를 수 없습니다. (현재 HP" + deer.getHp() + ")");
        }
    }
}