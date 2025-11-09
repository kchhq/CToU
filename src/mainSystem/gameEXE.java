package mainSystem;

import Entity.Currentfarm.Currentfarm;
import managementSystem.marketSystem.*;
import managementSystem.farmSystem.*;
import Entity.Farm.*;
import Entity.livestock.*;
import java.util.Scanner;
import Entity.enums.MenuState;

public class gameEXE {
    public static void main(String[] args) {
        System.out.println("""
                === 동물 키우기 시뮬레이터 ===
                당신은 빚을 갚기 위해 귀농 생활을 택했습니다.
                동물을 키우고 수익을 내 빚을 갚아보세요.
                """);

        // 농장 상태 초기화
        Currentfarm.init();

        // 컨트롤러 실행
        MenuController controller = new MenuController();
        controller.start();

        System.out.println("게임이 종료되었습니다. ");
    }
}