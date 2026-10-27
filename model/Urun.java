package model;

import java.util.Objects;

/**
 * Urun varlık (entity) sınıfı.
 * Sistemdeki ürün bilgilerini ve stok durumunu temsil eder.
 */
public class Urun implements Comparable<Urun> {
    private int id;
    private String ad;
    private String kategori;
    private double fiyat;
    private int adet;
    private int kritikSeviye;
    private int depoId;

    public Urun() {
    }

    public Urun(int id, String ad, String kategori, double fiyat, int adet, int kritikSeviye, int depoId) {
        this.id = id;
        this.ad = ad;
        this.kategori = kategori;
        this.fiyat = fiyat;
        this.adet = adet;
        this.kritikSeviye = kritikSeviye;
        this.depoId = depoId;
    }

    // --- Getter ve Setter Metotları ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public int getKritikSeviye() {
        return kritikSeviye;
    }

    public void setKritikSeviye(int kritikSeviye) {
        this.kritikSeviye = kritikSeviye;
    }

    public int getDepoId() {
        return depoId;
    }

    public void setDepoId(int depoId) {
        this.depoId = depoId;
    }

    // --- Yardımcı Metotlar ---

    /**
     * Ürün stok miktarının kritik seviyede veya altında olup olmadığını kontrol eder.
     */
    public boolean isKritikSeviyede() {
        return this.adet <= this.kritikSeviye;
    }

    /**
     * CSV dosyasına yazılacak satır formatını üretir.
     * Format: id,ad,kategori,fiyat,adet,kritikSeviye,depoId
     */
    public String toCSV() {
        return id + "," + ad + "," + kategori + "," + fiyat + "," + adet + "," + kritikSeviye + "," + depoId;
    }

    /**
     * CSV satırından Urun nesnesi üretir.
     * 
     * @param csvSatir Virgülle ayrılmış CSV satırı
     * @return Yeni Urun nesnesi
     */
    public static Urun fromCSV(String csvSatir) {
        if (csvSatir == null || csvSatir.trim().isEmpty()) {
            return null;
        }
        String[] parcalar = csvSatir.split(",");
        if (parcalar.length < 7) {
            throw new IllegalArgumentException("Geçersiz Urun CSV satırı (en az 7 sütun bekleniyor): " + csvSatir);
        }
        // Başlık satırı (header) kontrolü
        if (parcalar[0].trim().equalsIgnoreCase("id")) {
            return null;
        }
        int id = Integer.parseInt(parcalar[0].trim());
        String ad = parcalar[1].trim();
        String kategori = parcalar[2].trim();
        double fiyat = Double.parseDouble(parcalar[3].trim());
        int adet = Integer.parseInt(parcalar[4].trim());
        int kritikSeviye = Integer.parseInt(parcalar[5].trim());
        int depoId = Integer.parseInt(parcalar[6].trim());

        return new Urun(id, ad, kategori, fiyat, adet, kritikSeviye, depoId);
    }

    @Override
    public String toString() {
        return "Urun{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", kategori='" + kategori + '\'' +
                ", fiyat=" + fiyat +
                ", adet=" + adet +
                ", kritikSeviye=" + kritikSeviye +
                ", depoId=" + depoId +
                (isKritikSeviyede() ? " [KRİTİK STOK!]" : "") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Urun urun = (Urun) o;
        return id == urun.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Urun diger) {
        return Integer.compare(this.id, diger.id);
    }
}
