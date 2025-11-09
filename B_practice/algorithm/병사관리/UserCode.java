package algorithm.병사관리;

import java.io.*;
import java.util.*;

/**
 * mTeam 이 1~5 까지 있음. 
 * 링크드 리스트 배열을 만들어서 인덱스에 해당 팀을 가리키도록. -> 팀의 병사 전체 업데이트할떈 유리.
 * mId로 병사 하나에 접근은 ? -> 병사 배열.
 */

class UserSolution
{
	private Soldier[] soldiers;	
	private LinkedList[] linkedList;
	
	static class Soldier {
	    int id, team, score;
	    Soldier next, prev;
	    
	    public Soldier(int id, int team, int score) {
			super();
			this.id = id;
			this.team = team;
			this.score = score;
		}
	}
	
	static class LinkedList {
		Soldier head = new Soldier(-1, -1, -1);
	}
	
	public void init()
	{
		soldiers = new Soldier[100001];
		linkedList = new LinkedList[6]; // 0~5
		for(int i=1; i<=5; i++) {
			linkedList[i] = new LinkedList();
		}
	}
	
	/**
	 * 고유번호가 mID, 소속팀이 mTeam, 평판 점수가 mScore인 병사를 고용한다.
	   한 테스트 케이스 내에서 동일한 mID를 가진 병사가 여러 번 고용되는 경우는 없음이 보장된다.
	   
	   soldiers 배열에 추가 -> 팀 링크드리스트 추가.
	 * @param mID : 고유번호 (1 ≤ mID ≤ 100,000)
	 * @param mTeam : 소속팀 (1 ≤ mTeam ≤ 5)
	 * @param mScore : 평판 점수 (1 ≤ mScore ≤ 5)
	 */
	public void hire(int mID, int mTeam, int mScore)
	{
		soldiers[mID] = new Soldier(mID, mTeam, mScore);
	    
	    Soldier newSoldier = soldiers[mID];
	    // 새로운 노드의 이전, 다음 세팅
	    newSoldier.prev = linkedList[mTeam].head;
	    newSoldier.next = linkedList[mTeam].head.next;
	    
	    // 기존 노드의 이전노드 세팅
	    if(newSoldier.next != null) newSoldier.next.prev = newSoldier;
	    linkedList[mTeam].head.next = newSoldier;
	}
	
	/**
	 * 고유번호가 mID인 병사를 해고한다.
	   fire() 함수 호출 시, 고유번호가 mID인 병사가 고용되어 있음이 보장된다.
	   팀 링크드리스트 제거 -> 배열에서 제거
	 * @param mID : 고유번호 (1 ≤ mID ≤ 100,000)
	 */
	public void fire(int mID)
	{
		Soldier soldier = soldiers[mID];
		
//		Soldier cur = linkedList[soldier.team].head;
//		
//		while(true) {
//			if(cur.next.id == mID) { // 다음 ID 일치 하는거 찾음
//				if(cur.next.next != null) { // 다음 다음이 존재 
//					cur.next = cur.next.next;
//				} else { // 다음 다음 존재 x 
//					cur.next = null;
//				}
//				break;
//			}
//			cur = cur.next;
//		}
		
		soldier.prev.next = soldier.next;
		if(soldier.next != null) {
			soldier.next.prev = soldier.prev;
		}
		soldiers[mID] = null;
	}

	/**
	 * 고유번호가 mID인 병사의 평판 점수를 mScore로 변경한다.
	   고유번호가 mID인 병사가 고용되어 있음이 보장된다.
	 * @param mID : 고유번호 (1 ≤ mID ≤ 100,000)
	 * @param mScore : 평판 점수 (1 ≤ mScore ≤ 5)
	 */
	public void updateSoldier(int mID, int mScore)
	{
		soldiers[mID].score = mScore;
	}

	/**
	 *  소속팀이 mTeam인 병사들의 평판 점수를 모두 변경한다.
		소속팀이 mTeam인 병사가 한 명 이상 고용되어 있음이 보장된다.
		
		updateTeam() 함수에서의 평판 점수 변경은 아래의 규칙에 따른다.
		‘변경 전 평판 점수 + mChangeScore’가 5보다 클 경우, 평판 점수를 5로 변경한다.
		‘변경 전 평판 점수 + mChangeScore’가 1보다 작을 경우, 평판 점수를 1로 변경한다.
		그 외의 경우, 평판 점수를 ‘변경 전 평판 점수 + mChangeScore’로 변경한다.
		
	 * @param mTeam : 소속팀 (1 ≤ mTeam ≤ 5)
	 * @param mChangeScore : 평판 점수의 변화량 (-4 ≤ mChangeScore ≤ 4)
	 */
	public void updateTeam(int mTeam, int mChangeScore)
	{
		Soldier cur = linkedList[mTeam].head.next;
		
		while(true) {
			if(cur.score + mChangeScore > 5) cur.score = 5;
			else if(cur.score + mChangeScore < 1) cur.score = 1;
			else cur.score += mChangeScore;
			
			if(cur.next == null) break;
			cur = cur.next;
		}
	}
	
	/**
	 * 소속팀이 mTeam인 병사들 중 평판 점수가 가장 높은 병사의 고유번호를 반환한다.
	   평판 점수가 가장 높은 병사가 여러 명일 경우, 고유번호가 가장 큰 병사의 고유번호를 반환한다.
       소속팀이 mTeam인 병사가 한 명 이상 고용되어 있음이 보장된다.
	 * @param mTeam  : 소속팀 (1 ≤ mTeam ≤ 5)
	 * @return 평판 점수가 가장 높은 병사의 고유번호
	 */
	public int bestSoldier(int mTeam)
	{
		Soldier cur = linkedList[mTeam].head.next;
		
		int maxScore = cur.score;
		int maxId = cur.id;
		
		while(true) {
			if(cur.score > maxScore) {
				maxScore = cur.score;
				maxId = cur.id;
			} else if(cur.score == maxScore && cur.id > maxId) maxId = cur.id;
			
			if(cur.next == null) break;
			cur = cur.next;
		}
		return maxId;
	}
}