import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Bilgiler {
    Calisan ilk = null;
    Calisan son = null;

    public void Ekleme(Calisan yeni) {
        if (ilk == null) {
            ilk = yeni;
            son = yeni;
            yeni.next = ilk;
        } else {
            Calisan suanki = ilk;
            Calisan onceki = null;

            do {
                if (yeni.yas < suanki.yas || (yeni.yas == suanki.yas && yeni.maas < suanki.maas) ||
                    (yeni.yas == suanki.yas && yeni.maas == suanki.maas && yeni.ad.compareTo(suanki.ad) < 0) ||
                    (yeni.yas == suanki.yas && yeni.maas == suanki.maas && yeni.ad.compareTo(suanki.ad) == 0 && yeni.index < suanki.index)) {

                    yeni.next = suanki;

                    if (onceki == null) {
                        ilk = yeni;
                        son.next = ilk;
                    } else {
                        onceki.next = yeni;
                    }
                    return;
                }

                onceki = suanki;
                suanki = suanki.next;
            } while (suanki != ilk);

            onceki.next = yeni;
            yeni.next = ilk;
            son = yeni;
        }
    }

    public void Silme(String isim, String soyisim) {
        if (ilk == null) {
            System.out.println("Liste boş, silme işlemi yapılamaz.");
            return;
        }

        Calisan gecici = ilk, bironceki = null;

        do {
            if (gecici.ad.equals(isim) && gecici.soyad.equals(soyisim)) {
                break;
            }
            bironceki = gecici;
            gecici = gecici.next;
        } while (gecici != ilk);

        if (gecici == ilk) {
            ilk = gecici.next;
            son.next = ilk;
        } else {
            bironceki.next = gecici.next;
            if (gecici == son) {
                son = bironceki;
                son.next = ilk;
            }
        }
        System.out.println("\n" + isim + " " + soyisim + " silindi.\n");
    }

    public void Arama(String isim, String soyisim){
        if (ilk == null) {
            System.out.println("Liste boş.");
            return;
        }

        Calisan gecici = ilk;
        int toplamGelir =0 , sayac=0 ;
        boolean mevcutMu = false;

        do{
            toplamGelir += gecici.maas;
            sayac += 1;
            gecici = gecici.next;
        }while(gecici.next != ilk);

        float ortalamaGelir = toplamGelir / sayac;
    
        do{
            if(gecici.ad.equals(isim) && gecici.soyad.equals(soyisim)){
                System.out.println("\n" + isim + " " + soyisim + " listede mevcuttur. \nYaşı : " + gecici.yas + " \nMaaşı : " + gecici.maas);
                if(gecici.maas > ortalamaGelir){
                    System.out.println("Gelir durumu : üst gelirli\n");
                }else{
                    System.out.println("Gelir durumu : alt gelirli\n");
                }
                mevcutMu = true;
                break;
            }
            gecici = gecici.next;
        }while(gecici.next != ilk);

        if(!mevcutMu){
            System.out.println("\n" + isim + " " + soyisim + " listede mevcuttur değildir.\n");
        }
    }

    public void Listele(){
        if (ilk == null) {
            System.out.println("Liste boş.");
            return;
        }

        Calisan gecici = ilk;
        ArrayList <Calisan> calisanList = new ArrayList<Calisan>();

        do{
            calisanList.add(gecici);
            gecici = gecici.next;
        }while(gecici != ilk);

        calisanList.sort(Comparator.comparing(calisan -> calisan.ad));

        for (Calisan calisan : calisanList) {
            System.out.println(calisan.index + "\t" + calisan.ad + "\t" + calisan.soyad + "\t" + calisan.yas + "\t" + calisan.maas);
        }
    }

    public void kurumBilgisi(){
        if (ilk == null) {
            System.out.println("Liste boş.");
            return;
        }

        Calisan gecici = ilk;
        int toplamGelir =0 , sayac=0 ;

        do{
            toplamGelir += gecici.maas;
            sayac += 1;
            gecici = gecici.next;
        }while(gecici.next != ilk);

        float ortalamaGelir = toplamGelir / sayac;

        System.out.println("\nToplam personel maaşı : " + toplamGelir + " TL ");
        System.out.println("Ortalama personel maaşı : " + ortalamaGelir + " TL ");
        System.out.println("Toplam personel sayısı  : " + sayac);
    }

    public void personelKayit() throws IOException{
        if (ilk == null) {
            System.out.println("Liste boş, kayıt yapılamaz.");
            return;
        }

        BufferedWriter bwriter = new BufferedWriter(new FileWriter("bilgilerYeni"));
        Calisan gecici = ilk;
        
        do{
            bwriter.write(gecici.index + "\t" +  gecici.ad + "\t" + gecici.soyad + "\t" + gecici.yas + "\t" + gecici.maas);
            bwriter.newLine();
            gecici = gecici.next;
        }while(gecici != ilk);

        System.out.println("\nBilgiler dosyaya başarıyla kaydedildi.\n");
        bwriter.close();
    }

    public void Yazdir() {
        if (ilk == null) {
            System.out.println("Liste boş.");
            return;
        }

        Calisan gecici = ilk;
        do {
            System.out.println(gecici.index + "\t" + gecici.ad + "\t" + gecici.soyad + "\t" + gecici.yas + "\t" + gecici.maas);
            gecici = gecici.next;
        } while (gecici != ilk);
    }
}

class Calisan {
    int index;
    String ad, soyad;
    int yas;
    int maas;
    Calisan next;
}