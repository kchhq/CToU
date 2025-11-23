package mainSystem;

import farmSystem.Farm;
import farmSystem.Finance;
import mainSystem.controller.MenuController;

public class gameEXE {
    public static void main(String[] args) {
        System.out.println("""
                === 동물 키우기 시뮬레이터 ===
                당신은 빚을 갚기 위해 귀농 생활을 택했습니다.
                동물을 키우고 수익을 내 빚을 갚아보세요.
                """);

        Farm farm = new Farm();
        Finance finance = new Finance(farm, 500); // 네 Finance 생성자에 맞게

        MenuController controller = new MenuController(farm, finance);
        controller.start();

        System.out.println("게임이 종료되었습니다.");
    }
}
