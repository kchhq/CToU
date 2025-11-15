package farm;

import java.util.ArrayList;
import java.util.List;
import farm.livestock.Livestock;
import farm.livestock.Chicken;
import farm.Product;


public class Farm {

    private List<Livestock> animals;
    private Product productInventory;

    private int enclosureLevel; // 사육장 현재 레벨 (시작: 1)
    private int maxCapacity;    // 최대 수용 가능한 동물 수 (시작: 1)

    /** Farm 생성자: 닭 한 마리와 Product Inventory, 사육장 레벨을 초기화 */
    public Farm() {
        this.animals = new ArrayList<>();
        this.productInventory = new Product();

        // 사육장 레벨 및 수용량 초기화
        this.enclosureLevel = 1;
        this.maxCapacity = 1;

        addAnimal(new Chicken("꼬꼬댁"));
        System.out.println("농장을 시작합니다! 사육장 레벨: 1 (최대 수용: 1마리)");
    }

    // ----------------------------------------------------
    // 1. 사육장 리스트 추가 기능 (수용량 체크 포함)
    // ----------------------------------------------------

    /** 사육장 리스트에 가축을 추가 (수용량 체크) */
    public void addAnimal(Livestock animal) {
        if (this.animals.size() < this.maxCapacity) {
            this.animals.add(animal);
        } else {
            System.out.println("❌ 사육장 수용량 초과! [" + animal.getName() + "]를 추가할 수 없습니다. (최대: " + this.maxCapacity + "마리)");
        }
    }

    /** Finance에서 구매 확정 후 호출하여 가축 객체를 사육장에 추가 */
    public void addPurchasedAnimal(Livestock newAnimal) {
        if (newAnimal != null) {
            addAnimal(newAnimal);
        }
    }

    // ----------------------------------------------------
    // 2. 사육장 시설 확장 기능
    // ----------------------------------------------------

    /** 사육장 시설을 확장하고 레벨과 최대 수용량을 증가시킵니다. */
    public void expandEnclosure() {
        this.enclosureLevel++;
        this.maxCapacity++;
        System.out.println("✨ 사육장이 레벨 " + this.enclosureLevel + "로 확장되었습니다! (최대 수용량: " + this.maxCapacity + "마리)");
    }

    // ----------------------------------------------------
    // 3. 사육장 리스트 표시 및 조작 기능
    // ----------------------------------------------------

    /** 현재 사육장 가축들의 상태를 표시 */
    public void displayAllAnimalsStatus() {
        System.out.println("\n--- 현재 사육장 가축 목록 (" + animals.size() + "/" + this.maxCapacity + "마리) ---");
        if (animals.isEmpty()) {
            System.out.println("사육장이 비어있습니다.");
        }
        for (Livestock animal : animals) {
            animal.displayStatus();
        }
        System.out.println("--------------------------");
    }

    /** 특정 이름을 가진 동물 한 마리에게 먹이를 줌. */
    public void feedAnimalByName(String targetName) {
        System.out.println("\n===== 개별 먹이 주기 시작 =====");

        for (Livestock animal : animals) {
            if (animal.getName().equalsIgnoreCase(targetName)) {
                animal.feed();
                System.out.println("===== 개별 먹이 주기 완료 =====");
                return;
            }
        }
        System.out.println("❌ [" + targetName + "] 이름을 가진 동물을 사육장에서 찾을 수 없습니다.");
    }

    // ----------------------------------------------------
    // 4. Getter
    // ----------------------------------------------------

    public int getEnclosureLevel() {
        return enclosureLevel;
    }

    public Product getProductInventory() {
        return productInventory;
    }
}