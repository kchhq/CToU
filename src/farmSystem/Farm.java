package farmSystem;

import java.util.ArrayList;
import java.util.List;
import Entity.livestock.Livestock;


public class Farm {

    // 💡 사육장 규칙 관련 상수
    private static final int MAX_ENCLOSURE_LEVEL = 5;

    // 현재 사육장 가축 리스트
    private final List<Livestock> animals;

    private final Product productInventory;

    // 사육장 시설 관련 필드
    private int enclosureLevel;
    private int maxCapacity;

    /** Farm 생성자: 가축 리스트와 사육장 레벨을 초기화 */
    public Farm() {
        this.animals = new ArrayList<>();
        this.productInventory = new Product();

        // 사육장 레벨 및 수용량 초기화 (레벨 1 = 수용량 1)
        this.enclosureLevel = 1;
        this.maxCapacity = 1;
    }

    // ----------------------------------------------------
    // 1. 사육장 리스트 관리 기능 (가축 추가)
    // ----------------------------------------------------

    /** 사육장 리스트에 가축을 추가 (수용량 체크 포함) */
    public boolean addAnimal(Livestock animal) {
        if (this.animals.size() < this.maxCapacity) {
            this.animals.add(animal);
            System.out.println("새로운 가축 [" + animal.getName() + "]가 사육장에 추가되었습니다. (현재 " + this.animals.size() + "/" + this.maxCapacity + "마리)");
            return true;
        } else {
            System.out.println("❌ 사육장 수용량 초과! [" + animal.getName() + "]를 추가할 수 없습니다. (최대: " + this.maxCapacity + "마리)");
            return false;
        }
    }

    /* 외부(구매 시스템)에서 구매 확정 후 호출하여 가축 객체를 사육장에 추가
    public void addPurchasedAnimal(Livestock newAnimal) {
        if (newAnimal != null) {
            addAnimal(newAnimal);
        }
    } */ // MenuController의 BuyAnimal()로 대체함

    // ----------------------------------------------------
    // 2. 사육장 확장 기능 (레벨 5까지 제한)
    // ----------------------------------------------------

    /** 사육장 시설을 확장하고 레벨과 최대 수용량을 증가시킵니다. */
    public boolean expandEnclosure() {
        if (this.enclosureLevel < MAX_ENCLOSURE_LEVEL) {
            this.enclosureLevel++;
            this.maxCapacity = this.enclosureLevel; // 💡 레벨 N = 수용량 N

            System.out.println("✨ 사육장이 레벨 " + this.enclosureLevel + "로 확장되었습니다! (최대 수용량: " + this.maxCapacity + "마리)");
            return true;
        } else {
            System.out.println("🚫 사육장은 최대 레벨 (" + MAX_ENCLOSURE_LEVEL + ")입니다. 더 이상 확장할 수 없습니다.");
            return false;
        }
    }

    // ----------------------------------------------------
    // 3. Getter
    // ----------------------------------------------------

    /** 현재 사육 중인 가축 리스트를 반환합니다. */
    public List<Livestock> getAnimals() {
        return animals;
    }

    /** 현재 사육장 레벨을 반환합니다. */
    public int getEnclosureLevel() {
        return enclosureLevel;
    }

    /** 현재 사육장의 최대 수용량을 반환합니다. */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    // 인벤토리 getter
    public Product getProductInventory() {
        return productInventory;
    }

    // ----------------------------------------------------
    // 4. 모든동물상태
    // ----------------------------------------------------

    public void displayAllAnimalsStatus() {
        System.out.println("\n--- 🟢 현재 사육장 가축 목록 (" + animals.size() + "/" + this.maxCapacity + "마리) ---");

        if (animals.isEmpty()) {
            System.out.println("사육장이 비어있습니다.");
        } else {
            for (Livestock animal : this.animals) {
                animal.displayStatus();
            }
        }
        System.out.println("-------------------------------------------------");
    }

}