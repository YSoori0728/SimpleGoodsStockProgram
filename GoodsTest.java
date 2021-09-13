package C0901_Practice_Acc;

import java.util.*;

import javax.sound.sampled.Line;

public class GoodsTest {
	
	//재고 배열의 인덱스를 나타내는 전역변수
	public static int cnt = 0;
	
	//화면을 나누는 메소드
	static void line() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println();
	}
	
	//해당 재고가 이미 존재하는지 확인하는 메소드
	static GoodsInfo searchGoods(GoodsInfo[] g, String code) {
		for(int i=0;i<cnt;i++) {
			if(g[i].code.equals(code)) {
				return g[i];
			}
		}
		return null;
	}
	
	//모든 재고와 수량을 확인하는 메소드
	static void showAll(GoodsInfo[] g) {
		if(cnt==0) {
			System.out.println("등록된 재고가 없습니다.");
		}else {
			for(int i=0;i<cnt;i++) {
				System.out.println(g[i].code +" : "+g[i].stocks);
			}
		}
	}
	
	//삭제할 재고의 인덱스 번호를 찾는 메소드
	static int findDel(GoodsInfo[] g, String code) {
		for(int i=0;i<cnt;i++) {
			if(g[i].code.equals(code)) {
				return i;
			}
		}
		return -1;
	}
	
	//재고를 삭제하는 메소드
	static void delGoods(GoodsInfo[] g, int delCnt) {
		for(int i=delCnt;i<cnt;i++) {
			g[i]=g[i+1];
		}
		cnt--;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String code; // 상품명
		int stocks, select=0, delCnt; //수량, 프로그램 조작 버튼, 삭제할 재고의 인덱스
		
		GoodsInfo[] g = new GoodsInfo[100]; // 우선 재고가 들어갈 배열생성
		
		GoodsInfo g2 = null; //입고 시 중복이 있는지 확인할 변수
		
		System.out.println("재고 관리 프로그램을 실행합니다.");
		line();
		
		//반복문 사용하여 반복문이 종료될 때까지 계속 실행.
		while(select!=5) {
			
			//조작에 사용될 번호들의 정보 출력
			System.out.println("1.입고 2.출고 3.삭제 4.현황 5.종료");
			
			line();
			
			//조작할 번호를 스캐너를 통해 입력받는다.
			select = sc.nextInt();
			System.out.println();
			
			
			//입력받은 번호를 스위치문을 사용해서 조작한다.
			switch (select) {
			//1번 입력시 입고실행
			case 1:
				System.out.print("상품명 : "); code = sc.next(); // 상품명을 먼저 입력받음.
				System.out.print("수량 : "); stocks = sc.nextInt(); // 해당 상품의 수량을 입력받음.
				g2 = searchGoods(g, code); //해당 상품이 존재하는지 확인하는 메소드를 활용해서 이미 등록이 되어있는 상품인지 새로 등록할 상품인지 확인.
				//이미 등록된 상품이 없다면 재고 배열에 상품정보를 등록하고, 전역변수인 인덱스를 1개 증가시킴.
				//이미 등록된 상품이 존재하면 해당 상품의 수량만 입력받은 수량만큼 증가시킨다.
				if(g2==null) {
					g[cnt++] = new GoodsInfo(code, stocks);
				}else {
					g2.store(stocks);
				}
				
				line();
				break;
				
			//2번 입력시 출고 실행	
			case 2:
				
				//전역변수 cnt가 0이면 등록된 재고가 하나도 없으므로 등록된 물품이 없음을 출력, 0이 아니면 등록된 재고가 있으므로 출고할 상품명을 입력받음.
				if(cnt==0) {
					System.out.println("등록된 재고이 없습니다.");
				}else {
					
					//출고할 상품명을 입력받았을 때 해당 상품명이 재고배열에 존재하는지 확인 후 없으면 해당 물품이 없음을 출력
					System.out.print("상품명 : "); code = sc.next();
					g2 = searchGoods(g, code);
					
					if(g2 == null) {
						System.out.println("해당하는 재고가 없습니다.");
					}else {
						System.out.print("수량 : "); stocks = sc.nextInt();
						g2.out(stocks);
					}
					//해당하는 재고가 존재하면 g2.out(stocks)메소드를 수행. 
				}
				line();
				break;
				
			//3번 입력시 삭제 실행	
			case 3:
				
				//2번과 같이 전역변수 cnt가 0이면 등록된 재고가 하나도 없으므로 등록된 물품이 없음을 출력, 0이 아니면 등록된 재고가 있으므로 출고할 상품명을 입력받음.
				if(cnt==0) {
					System.out.println("등록된 상품이 없습니다.");
				}else {
					
					//출고할 상품명을 입력받았을 때 해당 상품명이 재고배열에 존재하는지 확인 후 없으면 해당 물품이 없음을 출력
					System.out.print("상품명 : "); code = sc.next();
					delCnt = findDel(g, code);
					if(delCnt==-1) {
						System.out.println("등록된 상품이 없습니다.");
					}else {
						delGoods(g, delCnt);
					}
					//해당하는 재고가 존재하면 delGoods(g, delCnt)메소드를 수행. 
				}
				line();
				break;
				
			//4번 입력시 등록된 재고의 이름과 수량을 모두 보여줌.	
			case 4:
				System.out.println("재고 현황을 출력합니다.");
				line();
				showAll(g);
				break;
				
			//5번 입력시 프로그램을 종료하고 반복문을 빠져나감.	
			case 5:
				System.out.println("프로그램을 종료합니다.");
				break;

			default:
				System.out.println("올바른 번호를 입력해주세요.");
				line();
				break;
			}
			
		}
		
	}
}
