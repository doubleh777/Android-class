package generator;
import java.util.Random;
public class dataGeneratorMain {

	public static void main(String[] args) {
		Random random = new Random();
		for(int id = 0 ; id < 100000 ; id++){
			int rand;
			System.out.print(id + ",");
			System.out.print("A,");
			
			rand = random.nextInt(6);
			switch(rand){
			case 0 :
				System.out.print("U,");
				break;
			case 1 :
				System.out.print("B,");
				break;
			case 2 :
				System.out.print("S,");
				break;
			case 3 :
				System.out.print("G,");
				break;
			case 4 :
				System.out.print("P,");
				break;
			case 5 :
				System.out.print("D,");
				break;
			}
			rand = random.nextInt(9999);
			System.out.println(rand);
		}
	}

}
