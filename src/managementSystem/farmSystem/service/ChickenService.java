package managementSystem.farmSystem.service; // 패키지 이름을 farmSystem으로 변경

import Entity.livestock.Chicken;
import Entity.livestock.Livestock; // MAX_HP 상수 참조를 위해 import 필요
import managementSystem.farmSystem.Product; // 💡 Product 클래스 import

public class ChickenService {

    // 🐔 달걀 수확 시 HP 비용
    private static final int EGG_HARVEST_COST = 10;

    /**
     * 닭과의 상호작용(달걀 수확)을 처리하고 재고를 업데이트합니다.
     * @param chicken 대상 Chicken 객체
     * @param productInventory Product 객체 (재고 관리)
     */
    public void interactHarvestEgg(Chicken chicken, Product productInventory) { // 💡 Product 타입 사용

        // 1. HP 100인지 공통 상태 확인
        if (chicken.isReadyForInteraction()) {

            // 2. 상호작용이 가능하면, 서비스 클래스에서 직접 HP 비용 처리 (10 감소)
            chicken.takeDamage(EGG_HARVEST_COST);

            // 3. 재고 업데이트 (달걀 추가)
            productInventory.addEggs(1); // 💡 Product 클래스의 addEggs 메서드 호출

            System.out.println(chicken.getName() + "의 달걀을 수확하고 HP가 " + chicken.getHp() + "으로 감소했습니다. 🥚");

        } else {
            // 실패 메시지 출력
            System.out.println(chicken.getName() +
                    ": HP가 " + Livestock.MAX_HP + "이 아니라 달걀을 수확할 수 없습니다. (현재 HP" + chicken.getHp() + ")");
        }
    }
}