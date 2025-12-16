package breedingSystem;

import Entity.livestock.*;
import breedingSystem.traits.*;

public class BreedingService {

    public Livestock breed(Livestock parent1, Livestock parent2) {

        // 1) 같은 종인지 체크
        if (!parent1.getClass().equals(parent2.getClass())) {
            throw new IllegalArgumentException("같은 종의 동물만 교배할 수 있습니다.");
        }

        // 2) 자식 생성
        Livestock child = createChild(parent1);

        // 3) 공용 특성 유전 (있으면 40% 확률)
        inheritCommonTrait(parent1, parent2, child);

        // 4) 종 특성 유전
        inheritSpeciesTrait(parent1, parent2, child);

        return child;
    }

    private Livestock createChild(Livestock parent) {
        String childName = parent.getName() + "의 자식";

        if (parent instanceof Chicken) return new Chicken(childName);
        if (parent instanceof Cow) return new Cow(childName);
        if (parent instanceof Sheep) return new Sheep(childName);
        if (parent instanceof Deer) return new Deer(childName);

        // 너희 Unicorn 생성자에 맞춰서 유지 (현재 Unicorn(String name, String type))
        if (parent instanceof Unicorn) return new Unicorn(childName, "Unicorn");

        throw new IllegalStateException("알 수 없는 동물 타입입니다.");
    }

    private void inheritCommonTrait(Livestock p1, Livestock p2, Livestock child) {
        CommonTrait t1 = (CommonTrait) p1.getCommonTrait();
        CommonTrait t2 = (CommonTrait) p2.getCommonTrait();

        // 둘 다 없음
        if (t1 == null && t2 == null) return;

        // 유전 확률(너가 쓰던 값 유지)
        if (!TraitRng.chance(40)) return;

        // 하나만 있으면 그거
        if (t1 == null) {
            child.setCommonTrait(t2);
            return;
        }
        if (t2 == null) {
            child.setCommonTrait(t1);
            return;
        }

        // 둘 다 있고, 같은 특성이면 그대로
        if (t1 == t2) {
            child.setCommonTrait(t1);
            return;
        }

        // 둘 다 있고 다르면 랜덤 선택
        child.setCommonTrait(TraitRng.chance(50) ? t1 : t2);
    }


    private void inheritSpeciesTrait(Livestock p1, Livestock p2, Livestock child) {

        // 유니콘 예외 규칙
        if (child instanceof Unicorn) {
            inheritUnicornTrait(p1, p2, (Unicorn) child);
            return;
        }

        Trait t1 = p1.getSpeciesTrait();
        Trait t2 = p2.getSpeciesTrait();

        // 둘 다 없음
        if (t1 == null && t2 == null) return;

        // 유전 확률
        if (!TraitRng.chance(40)) return;

        // 하나만 있으면 그거
        if (t1 == null) {
            child.setSpeciesTrait(t2);
            return;
        }
        if (t2 == null) {
            child.setSpeciesTrait(t1);
            return;
        }

        // 둘 다 있고 같은 특성이면 그대로
        if (t1 == t2) {
            child.setSpeciesTrait(t1);
            return;
        }

        // 둘 다 있고 다르면 랜덤 선택
        child.setSpeciesTrait(TraitRng.chance(50) ? t1 : t2);
    }


    // 예외 : 유니콘 교배 시부모 중 AT_LEAST_3RD가 있으면 30% 확률로 WIN_ALWAYS로 변환되어서 유전
    private void inheritUnicornTrait(Livestock p1, Livestock p2, Unicorn child) {

        boolean hasWinAlways =
                p1.getSpeciesTrait() == UnicornTrait.WIN_ALWAYS ||
                        p2.getSpeciesTrait() == UnicornTrait.WIN_ALWAYS;

        boolean hasAtLeast3rd =
                p1.getSpeciesTrait() == UnicornTrait.AT_LEAST_3RD ||
                        p2.getSpeciesTrait() == UnicornTrait.AT_LEAST_3RD;

        // 1) WIN_ALWAYS는 일반 유전(예: 40%)
        if (hasWinAlways && TraitRng.chance(40)) {
            child.setSpeciesTrait(UnicornTrait.WIN_ALWAYS);
            return;
        }

        // 2) AT_LEAST_3RD는 직접 유전 금지, 대신 30%로 WIN_ALWAYS로 변환 유전
        if (hasAtLeast3rd && TraitRng.chance(30)) {
            child.setSpeciesTrait(UnicornTrait.WIN_ALWAYS);
        }
    }

}
