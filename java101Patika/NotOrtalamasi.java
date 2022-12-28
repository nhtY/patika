import java.util.Scanner;

public class NotOrtalamasi {

	public static void main(String[] args) {
        
		
		int matNot, fizikNot, kimyaNot, turkceNot, tarihNot, muzikNot;
		

		Scanner scanner = new Scanner(System.in);
        
		
		System.out.print("Matematik notunuzu girin: ");
		matNot = scanner.nextInt();
		
		System.out.print("Fizik notunuzu girin: ");
		fizikNot = scanner.nextInt();
		
		System.out.print("Kimya notunuzu girin: ");
		kimyaNot = scanner.nextInt();
		
		System.out.print("Türkçe notunuzu girin: ");
		turkceNot = scanner.nextInt();
		
		System.out.print("Tarih notunuzu girin: ");
		tarihNot = scanner.nextInt();
		
		System.out.print("Müzik notunuzu girin: ");
		muzikNot = scanner.nextInt();
		
		double notOrtalamasi = (matNot + fizikNot + kimyaNot + turkceNot + tarihNot + muzikNot) / 6 ;
        
		System.out.println("Not ortalamanız: " + notOrtalamasi);
		
		System.out.println(notOrtalamasi > 60 ? "Sınıfı geçtiniz" : "Sınıfta kaldınız");
			
	}
}
