package farm.livestock;

public abstract class Livestock {
    public int level;
    private String name;
    private String type;
    private int hp;



    //상수
    protected static final int MAX_HP = 100;
    protected static final int MAX_LEVEL=3;

    //생성자
    protected Livestock(String name, String type){
        this.name=name;
        this.type=type;
        this.level=1;
        this.hp=10;
    }


    //밥주기 : HP를 100으로 만들고 , HP가 100이되면 레벨이 +1
    //Level은 최대 3

    public void feed(){
        boolean wasFull=(hp == MAX_HP);
        hp = MAX_HP;
        System.out.println(name +" 에게 밥을 주었습니다. HP가 " + hp + "이(가) 되었습니다.");

        //이미 HP가 100이였을 때는 레벨이 오르지 않음
        if(!wasFull && level < MAX_LEVEL){
            level++;
            System.out.println(name + "이(가) 레벨업 ! 현재레벨: " + level);
        } else if (level >= MAX_LEVEL) {
            System.out.println(name + "은(는) 배불러요 ㅠㅠ ! (최대레벨입니다)");

        }

    }

    //공통기능
    // 동물상태 표시
    public void displayStatus() {
        System.out.println("[" + type + "] " + name
                + ", level=" + level
                + ", hp=" + hp + "/" + MAX_HP);
    }

    // 상호작용시 피 깎임
    protected void takeDamage(int dmg) {
        if (dmg <= 0) return;
        this.hp = Math.max(0, this.hp - dmg);
        System.out.println(name + "이(가)의 HP가 깎였습니다. HP : " + this.hp);
    }

    // Getter
    public String getName() { return name; }
    public int getHp() { return hp; }


}
