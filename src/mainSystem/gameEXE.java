package mainSystem;

import farmSystem.Farm;
import farmSystem.Finance;
import mainSystem.controller.MenuController;

public class gameEXE {
    public static void main(String[] args) {
        System.out.println("""
                ======== 동물 키우기 시뮬레이터 ========
                동물 키우기 시뮬레이터 입니다.
                동물을 키우고 상호작용을 통해 생산물을 얻어 판매하면 수익을 얻을 수 있습니다.
                돈을 모아서 전설의 동물 유니콘을 구매해보세요!
                """);

        Farm farm = new Farm();
        Finance finance = new Finance(farm, 100); // 초기 자금 100원?

        MenuController controller = new MenuController(farm, finance);
        controller.start();

        System.out.println("게임이 종료되었습니다.");
    }
}
