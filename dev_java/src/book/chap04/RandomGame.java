package book.chap04;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*
 * 0부터 9사이의 임의의 숫자를 채번하기
 */
public class RandomGame {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		RandomGameSimulation rand = new RandomGameSimulation();
		
		for (int cnt = 0; cnt < 3; cnt++) {
			int choice = Integer.parseInt(JOptionPane.showInputDialog("숫자"));
			int imsi = rand.random(choice, cnt);
			if(imsi == choice ) {
				break;
			}
		}//end of for
		
	}//end of main
}//end of RandomGame
