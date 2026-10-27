package model;

import java.util.Objects;

/**
 * Depo varlık (entity) sınıfı.
 * Fiziksel depo ve lokasyon bilgilerini temsil eder.
 */
public class Depo implements Comparable<Depo> {
    private int id;
    private String ad;
    private int kapasite;
    private String konum;

    public Depo() {
    }

    public Depo(int id, String ad, int kapasite, String konum) {
        this.id = id;
        this.ad = ad;
        this.kapasite = kapasite;
        this.konum = konum;
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

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public String getKonum() {
        return konum;
    }

    public void setKonum(String konum) {
        this.konum = konum;
    }

    // --- Yardımcı Metotlar ---

    /**
     * CSV formatında satır üretir.
     * Format: id,ad,kapasite,konum
     */
    public String toCSV() {
        return id + "," + ad + "," + kapasite + "," + konum;
    }

    /**
     * CSV satırından Depo nesnesi üretir.
     * 
     * @param csvSatir Virgülle ayrılmış CSV satırı
     * @return Yeni Depo nesnesi
     */
    public static Depo fromCSV(String csvSatir) {
        if (csvSatir == null || csvSatir.trim().isEmpty()) {
            return null;
        }
        String[] parcalar = csvSatir.split(",");
        if (parcalar.length < 4) {
            throw new IllegalArgumentException("Geçersiz Depo CSV satırı (en az 4 sütun bekleniyor): " + csvSatir);
        }
        // Başlık satırı kontrolü
        if (parcalar[0].trim().equalsIgnoreCase("id")) {
            return null;
        }
        int id = Integer.parseInt(parcalar[0].trim());
        String ad = parcalar[1].trim();
        int kapasite = Integer.parseInt(parcalar[2].trim());
        String konum = parcalar[3].trim();

        return new Depo(id, ad, kapasite, konum);
    }

    @Override
    public String toString() {
        return "Depo{" +
                "id=" + id +
                ", ad='" + ad + '\'' +
                ", kapasite=" + kapasite +
                ", konum='" + konum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depo depo = (Depo) o;
        return id == depo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Depo diger) {
        return Integer.compare(this.id, diger.id);
    }
}
