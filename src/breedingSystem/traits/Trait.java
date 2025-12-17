package breedingSystem.traits;

// 특성을 인터페이스로 구현
public interface Trait {
    String id();

    default void onBeforeInteraction(TraitEffectContext ctx) {} // 상호작용 전에 작동
    default void onAfterInteraction(TraitEffectContext ctx) {} // 상호작용 후에 작동
    String displayName(); // 특성의 한글 이름 표시
    default String description() { // 특성의 설명 표시
        return "";
    }
}
