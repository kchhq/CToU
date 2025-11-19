package mainSystem;


import managementSystem.farmSystem.Farm;
import managementSystem.farmSystem.Finance;
// import Entity.enums.MenuState;

public class gameEXE {
    public static void main(String[] args) {
        System.out.println("""
                === 동물 키우기 시뮬레이터 ===
                당신은 빚을 갚기 위해 귀농 생활을 택했습니다.
                동물을 키우고 수익을 내 빚을 갚아보세요.
                """);

        // 1. 핵심 객체 초기화 (Farm과 Finance)
        Farm farm = new Farm(); // Farm 객체 생성 (닭 한 마리로 시작, 사육장 레벨 1)
        Finance finance = new Finance(farm, 500); // Finance 객체 생성 (Farm 연결, 초기 자금 500원)

        // 2. 컨트롤러 실행

        MenuController controller = new MenuController(farm, finance);
        controller.start();

        System.out.println("게임이 종료되었습니다. ");
    }
}