import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        Bilgiler calisanlar = new Bilgiler();
        File file = new File("bilgiler.txt");

        if(!file.exists()){
            file.createNewFile();
        }

        int secim;

        do{

            System.out.print("""
                0 - Çıkış
                1 - Ekleme 
                2 - Silme 
                3 - Arama
                4 - Listele
                5 - Kurum Bilgisi
                6 - Personel Kayıt

                Lütfen bir işlem seçiniz:""");
            secim = scanner.nextInt();

            if(secim == 0){
                System.out.println("Çıkış yapılıyor...");
                System.out.println("Çıkış yapıldı.");
                break;
            }else if(secim == 1){
                FileReader freader = new FileReader(file);
                BufferedReader breader = new BufferedReader(freader);
                String line;
                while((line = breader.readLine()) != null){
                    Calisan yeni = new Calisan();
                    String[] dizi = new String[5];
                    dizi = line.split(" ");
                    yeni.index = Integer.parseInt(dizi[0]);
                    yeni.ad = dizi[1];
                    yeni.soyad = dizi[2];
                    yeni.yas = Integer.parseInt(dizi[3]);
                    yeni.maas = Integer.parseInt(dizi[4]);
                    calisanlar.Ekleme(yeni);
                }
                calisanlar.Yazdir();
                System.out.println();
            }else if(secim == 2){
                System.out.print("Bir isim giriniz: ");
                String isim = scanner.next();
                System.out.print("Soyisim girin : ");
                String soyisim = scanner.next();
                calisanlar.Silme(isim, soyisim);
                calisanlar.Yazdir();
                System.out.println();
            }else if(secim == 3){
                System.out.print("Bir isim giriniz: ");
                String isim = scanner.next();
                System.out.print("Soyisim girin : ");
                String soyisim = scanner.next();
                calisanlar.Arama(isim, soyisim);
                calisanlar.Yazdir();
                System.out.println();
            }else if(secim == 4){
                calisanlar.Listele();
                System.out.println();
            }else if(secim == 5){
                calisanlar.kurumBilgisi();
                System.out.println();
            }else if(secim == 6){
                calisanlar.personelKayit();
            }else{
                System.out.println("Lütfen geçerli bir işlem giriniz..");
            }
        }while(secim != 0);
        
        scanner.close();
    }
}