package common.enums;

public enum PreferredFeed {
    // 닭 (Chicken) - SEED
    CHICKEN("SEED"),

    // 소 (Cow) - FEED
    COW("FEED"),

    // 양 (Sheep) - HAY
    SHEEP("HAY"),

    // 사슴 (Deer) - FRUIT
    DEER("FRUIT"),

    // 유니콘 (Unicorn) - CUPCAKE
    UNICORN("CUPCAKE");

    private final String feedType;

    // 생성자
    PreferredFeed(String feedType) {
        this.feedType = feedType;
    }

    // 선호 사료 종류를 반환하는 getter 메서드
    public String getFeedType() {
        return feedType;
    }
}