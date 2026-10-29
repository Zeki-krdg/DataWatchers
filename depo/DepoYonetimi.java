package depo;

import model.Depo;
import model.Ilac;
import java.util.ArrayList;
import java.util.List;

// Ilac bagli liste dugumu
class IlacDugum {
    Ilac ilac;
    IlacDugum sonraki;

    public IlacDugum(Ilac ilac) {
        this.ilac = ilac;
        this.sonraki = null;
    }
}

public class DepoYonetimi {
    private List<Depo> depoListesi = new ArrayList<>();
    private IlacDugum bas = null;
    private int toplamMiktar = 0;

    // Depo ekleme
    public void depoEkle(Depo d) {
        depoListesi.add(d);
    }

    // Depo silme
    public boolean depoSil(int id) {
        for (int i = 0; i < depoListesi.size(); i++) {
            if (depoListesi.get(i).getId() == id) {
                depoListesi.remove(i);
                return true;
            }
        }
        return false;
    }

    // Depoya bagli liste ile ilac ekleme
    public boolean ilacEkle(Ilac yeni, int maxKapasite) {
        if (toplamMiktar + yeni.getMiktar() > maxKapasite) {
            System.out.println("Kapasite yetersiz, ilac eklenemedi.");
            return false;
        }

        IlacDugum yeniDugum = new IlacDugum(yeni);
        yeniDugum.sonraki = bas;
        bas = yeniDugum;
        toplamMiktar += yeni.getMiktar();
        return true;
    }

    // Bagli listedeki ilaclari ekrana basma
    public void ilaclariGoster() {
        if (bas == null) {
            System.out.println("Depoda ilac yok.");
            return;
        }

        IlacDugum temp = bas;
        while (temp != null) {
            System.out.println("ID: " + temp.ilac.getId() + " - Ad: " + temp.ilac.getAd() + " - Miktar: " + temp.ilac.getMiktar());
            temp = temp.sonraki;
        }
    }
}