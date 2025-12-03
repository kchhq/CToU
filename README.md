### CtoU (Chicken to Unicorn) : 목장 시뮬레이터
### 프로젝트 구조

- mainSystem (실행 부분)
    - controller (실제 게임 실행)
    - gameEXE (코드 시작점, 컨트롤러 호출)
- Entity (엔티티 정의 패키지)
    - livestock (가축)
    - Sheep
    - Cow
    - Deer
    - Sheep
    - Unicorn
- farmSystem
  - service
    - FarmService (동물 현황 표시, 사료 주기, 상호작용 로직) 
    - ChickenService
    - CowService
    - SheepService
    - DeerService
    - UnicornService
  - Farm (현재 사육장 상태)
  - Finance (현재 자금 표시 및 증가, 차감 로직)
  - Product (인벤토리 관리 및 표시 로직)
- marketSystem
  - ShopManager (상점 UI 표시 및 기능 호출)
  - ShopService (구매, 판매, 사육장 확장 기능)
- breedingSystem (교배 로직)
