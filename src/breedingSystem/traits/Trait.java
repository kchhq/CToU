package breedingSystem.traits;

// 특성을 인터페이스로 구현
public interface Trait {
    String id();

    default void onBeforeInteraction(TraitEffectContext ctx) {}
    default void onAfterInteraction(TraitEffectContext ctx) {}
    default String description() {
        return "";
    }
}
