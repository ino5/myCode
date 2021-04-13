package numberbaseball;

import java.util.Scanner;


public class numberbaseball {
	public static int checkStrike(int[] YOUR_NUMBER, int[] ask ) {
		int numberStrike = 0;
		for(int i =0; i<3; i++) {
			if(YOUR_NUMBER[i] == ask[i]) ++numberStrike;
		}
		return numberStrike;
	}

	public static int checkBall(int[] YOUR_NUMBER, int[] ask ) {
		int numberBall = 0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i!=j) {
					if(YOUR_NUMBER[i] == ask[j]) ++numberBall;
				}
			}
		}
		return numberBall;
	}
	
	
	
	public static void main (String[] args) {
		int[] answer = {10,10,10};
		int[] ask = {0,0,0};
		int[] ask2 = {0,0,0};
		Scanner sc = new Scanner(System.in);		
		int S=0;
		int B;
		int S2=0;
		
		int currentStep=0;
		final int[] YOUR_NUMBER = new int[3];
		int YOUR_NUMBER_temp;
		int count = 0;
		
		//save YOUR_NUMBER
		System.out.print("What is your number : ");
		YOUR_NUMBER_temp=sc.nextInt();
		for(int i=0; i<3; i++) {
			YOUR_NUMBER[i] = (int)(YOUR_NUMBER_temp/Math.pow(10, 2-i))%10;
		}
		
		//step1 - search balls
		if(currentStep==0) {
			++currentStep;
			System.out.println("Step"+currentStep);
		}
		while (currentStep==1) {
			if(S==3) break;
			while (true) {
				for(int i=0; i<3; i++) {
					ask[i]=(int)(Math.random()*10);
				}
				if(ask[0]>0 && ask[0]!=ask[1] && ask[0]!=ask[2] && ask[1]!=ask[2]) {
					break;	
				}
			}

			System.out.println("check : "+ask[0]+ask[1]+ask[2]);


			S = checkStrike(YOUR_NUMBER, ask);	
			B = checkBall(YOUR_NUMBER, ask);
			System.out.println("check, S/B, count : "+ask[0]+ask[1]+ask[2]+", "+S+"/"+B+", "+(++count));
			if(B>0) {
				for(int i=0; i<3; i++) {
					ask2[i]=ask[i];
				}
				break;
			}
		}
		
		//step2 - search strikes
		if(currentStep==1) {
			++currentStep;
			System.out.println("Step"+currentStep);
		}
		while (true) {
			if(S==3) break;
			for(int i=0; i<3; i++) {
				ask[i]=ask2[i];
			}
			int j=(int)((Math.random()*10)%3);
			int j2=(int)((Math.random()*10)%3);
			if(j==j2) continue;
			ask[j]=ask2[j2];
			ask[j2]=ask2[j];
			for(int i=0; i<3; i++) {
				ask2[i]=ask[i];	
			}			
			System.out.println("check : "+ask[0]+ask[1]+ask[2]);


			S = checkStrike(YOUR_NUMBER, ask);	
			B = checkBall(YOUR_NUMBER, ask);
			System.out.println("check, S/B, count : "+ask[0]+ask[1]+ask[2]+", "+S+"/"+B+", "+(++count));
			if(B==0) {		
				S2=S;
				break;			
			}
		}
		
		//step3 - search the strike place
		if(currentStep==2) {
			++currentStep;
			System.out.println("Step"+currentStep);
		}
		for (int i2=0; i2<3; i2++) {
			if(S==3) break;
			while (true) {
				ask[i2]=(int)(Math.random()*10);
				if(ask[i2]!=ask2[i2] && ask[0]>0 && ask[0]!=ask[1] && ask[0]!=ask[2] && ask[1]!=ask[2]) {
					break;	
				}
			}
			
			System.out.println("check : "+ask[0]+ask[1]+ask[2]);


			S = checkStrike(YOUR_NUMBER, ask);	
			B = checkBall(YOUR_NUMBER, ask);
			System.out.println("check, S/B, count : "+ask[0]+ask[1]+ask[2]+", "+S+"/"+B+", "+(++count));
			if(S==S2-1) {
				answer[i2]=ask2[i2];
				break;			
			}
			ask[i2]=ask2[i2];
			if(i2==2) i2=-1;
		}
		
		//step4 - search balls + 1answer
		if(currentStep==3) {
			++currentStep;
			System.out.println("Step"+currentStep);
		}
		while (true) {
			if(S==3) break;
			while (true) {
				for(int i=0; i<3; i++) {
					ask[i]=(int)(Math.random()*10);
					if(answer[i]<10)ask[i]=answer[i];
				}
				if(ask[0]>0 && ask[0]!=ask[1] && ask[0]!=ask[2] && ask[1]!=ask[2]) {
					break;	
				}
			}

			System.out.println("check : "+ask[0]+ask[1]+ask[2]);


			S = checkStrike(YOUR_NUMBER, ask);	
			B = checkBall(YOUR_NUMBER, ask);
			System.out.println("check, S/B, count : "+ask[0]+ask[1]+ask[2]+", "+S+"/"+B+", "+(++count));
			if(B>0) {
				for(int i=0; i<3; i++) {
					ask2[i]=ask[i];
				}
				break;
			}
		}
		
		//step5 - search 2strikes (1answer)
		if(currentStep==4) {
			++currentStep;
			System.out.println("Step"+currentStep);
		}
		while (true) {
			if(S==3) break;
			for(int i=0; i<3; i++) {
				ask[i]=ask2[i];
			}
			int j=(int)((Math.random()*10)%3);
			int j2=(int)((Math.random()*10)%3);
			if(j==j2) continue;
			if(answer[j]<10 || answer[j2]<10) continue;
			ask[j]=ask2[j2];
			ask[j2]=ask2[j];
			for(int i=0; i<3; i++) {
				ask2[i]=ask[i];	
			}			
			S = checkStrike(YOUR_NUMBER, ask);	
			B = checkBall(YOUR_NUMBER, ask);
			System.out.println("check, S/B, count : "+ask[0]+ask[1]+ask[2]+", "+S+"/"+B+", "+(++count));
			if(S>=2 && B==0) {		
				S2=S;
				break;			
			}
		}
		
		//step6 - search the strike place (1answer)
		if(currentStep==5) {
			++currentStep;
			System.out.println("Step"+currentStep);
		}
		for (int i2=0; i2<3; i2++) {
			if(S==3) break;
			if(answer[i2]<10) continue;
			while (true) {
				ask[i2]=(int)(Math.random()*10);
				if(ask[i2]!=ask2[i2] && ask[0]>0 && ask[0]!=ask[1] && ask[0]!=ask[2] && ask[1]!=ask[2]) {
					break;	
				}
			}
			

			S = checkStrike(YOUR_NUMBER, ask);	
			B = checkBall(YOUR_NUMBER, ask);
			System.out.println("check, S/B, count : "+ask[0]+ask[1]+ask[2]+", "+S+"/"+B+", "+(++count));
			if(S==S2-1) {
				answer[i2]=ask2[i2];
				break;			
			}
			ask[i2]=ask2[i2];
			if(i2==2) i2=-1;
		}		
		
		//step7 - search the last answer
		if(currentStep==6) {
			++currentStep;
			System.out.println("Step"+currentStep);
		}
		while (true) {
			if(S==3) break;
			while (true) {	
				for(int i=0; i<3; i++) {
					ask[i]=(int)(Math.random()*10);
					if(answer[i]<10)ask[i]=answer[i];					
				}
				if(ask[0]>0 && ask[0]!=ask[1] && ask[0]!=ask[2] && ask[1]!=ask[2]) {
					break;	
				}
			}

			S = checkStrike(YOUR_NUMBER, ask);	
			B = checkBall(YOUR_NUMBER, ask);
			System.out.println("check, S/B, count : "+ask[0]+ask[1]+ask[2]+", "+S+"/"+B+", "+(++count));
			if(S==3) {
				for(int i=0; i<3; i++) {
					ask2[i]=ask[i];
				}
				break;
			}
		}		
		sc.close();
		System.out.println("finish");

		

	}
		
}
