package farmSystem.service;

import Entity.livestock.Cow;
import Entity.livestock.Livestock;
import farmSystem.Product;

public class CowService {

    // 🐮 착유 시 HP 비용
    private static final int MILKING_COST = 40;

    /**
     * 소와의 상호작용(착유)을 처리하고 재고를 업데이트합니다.
     * @param cow 대상 Cow 객체
     * @param productInventory Product 객체 (재고 관리)
     */
    public void interactMilk(Cow cow, Product productInventory) {
        // 1. 오늘 상호작용을 이미 했는지 확인
        if (cow.getInteractedToday()) {
            System.out.println("동물이 지쳐 오늘은 더 이상 상호작용 할 수 없습니다.");
        } else if (cow.getHp() < Livestock.MAX_HP) {
            // 실패 메시지 출력
            System.out.println(cow.getName() +
                    ": HP가 " + Livestock.MAX_HP + "이 아니라 착유할 수 없습니다. (현재 HP" + cow.getHp() + ")");
        } else {
            // 상호작용이 가능하면, 서비스 클래스에서 직접 HP 비용 처리 (20 감소)
            cow.takeDamage(MILKING_COST);

            // 재고 업데이트 (우유 추가)
            productInventory.addMilk(1); // 우유 재고 1L 증가
            cow.setInteractedToday(true); // 상호작용을 했으니 InteractedToday를 true로 변경
            System.out.println(cow.getName() + "를 착유하고 HP가 " + cow.getHp() + "으로 감소했습니다. 🥛소가 지쳤습니다.");
        }
    }
}