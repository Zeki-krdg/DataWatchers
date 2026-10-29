package depo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graf {
    private int depoSayisi;
    private List<List<Integer>> komsuListesi;

    public Graf(int depoSayisi) {
        this.depoSayisi = depoSayisi;
        komsuListesi = new ArrayList<>();
        for (int i = 0; i < depoSayisi; i++) {
            komsuListesi.add(new ArrayList<>());
        }
    }

    // Depolar arasina yol ekleme
    public void yolEkle(int u, int v) {
        komsuListesi.get(u).add(v);
        komsuListesi.get(v).add(u);
    }

    // BFS ile en az aktarmali yolu bulma
    public int enKisaYolBFS(int baslangic, int hedef) {
        if (baslangic == hedef) return 0;

        boolean[] ziyaretEdildi = new boolean[depoSayisi];
        Queue<Integer> kuyruk = new LinkedList<>();

        ziyaretEdildi[baslangic] = true;
        kuyruk.add(baslangic);

        int adimSayisi = 0;

        while (!kuyruk.isEmpty()) {
            int boyut = kuyruk.size();
            adimSayisi++;

            for (int i = 0; i < boyut; i++) {
                int mevcut = kuyruk.poll();

                for (int komsu : komsuListesi.get(mevcut)) {
                    if (komsu == hedef) {
                        return adimSayisi; // Kaç depo aktarmayla gidildiğini döner
                    }
                    if (!ziyaretEdildi[komsu]) {
                        ziyaretEdildi[komsu] = true;
                        kuyruk.add(komsu);
                    }
                }
            }
        }

        return -1; // Yol yoksa
    }
}