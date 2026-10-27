package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * StokHareketi varlık (entity) sınıfı.
 * Ürünlerin giriş, çıkış ve depolar arası transfer işlemlerini temsil eder.
 */
public class StokHareketi {
    public static final String TUR_GIRIS = "GIRIS";
    public static final String TUR_CIKIS = "CIKIS";
    public static final String TUR_TRANSFER = "TRANSFER";

    private static final DateTimeFormatter TARIH_FORMATI = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int urunId;
    private String tur; // GIRIS, CIKIS, TRANSFER
    private int miktar;
    private String tarih;
    private int kaynakDepoId;
    private int hedefDepoId;

    public StokHareketi() {
    }

    public StokHareketi(int urunId, String tur, int miktar, String tarih, int kaynakDepoId, int hedefDepoId) {
        this.urunId = urunId;
        this.tur = tur;
        this.miktar = miktar;
        this.tarih = tarih;
        this.kaynakDepoId = kaynakDepoId;
        this.hedefDepoId = hedefDepoId;
    }

    /**
     * Tarih parametresi verilmediğinde sistem saatini otomatik atayan yapıcı metot.
     */
    public StokHareketi(int urunId, String tur, int miktar, int kaynakDepoId, int hedefDepoId) {
        this(urunId, tur, miktar, LocalDateTime.now().format(TARIH_FORMATI), kaynakDepoId, hedefDepoId);
    }

    // --- Getter ve Setter Metotları ---

    public int getUrunId() {
        return urunId;
    }

    public void setUrunId(int urunId) {
        this.urunId = urunId;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public int getKaynakDepoId() {
        return kaynakDepoId;
    }

    public void setKaynakDepoId(int kaynakDepoId) {
        this.kaynakDepoId = kaynakDepoId;
    }

    public int getHedefDepoId() {
        return hedefDepoId;
    }

    public void setHedefDepoId(int hedefDepoId) {
        this.hedefDepoId = hedefDepoId;
    }

    // --- Yardımcı Metotlar ---

    /**
     * CSV satırı formatında çıktı üretir.
     * Format: urunId,tur,miktar,tarih,kaynakDepoId,hedefDepoId
     */
    public String toCSV() {
        return urunId + "," + tur + "," + miktar + "," + tarih + "," + kaynakDepoId + "," + hedefDepoId;
    }

    /**
     * CSV satırından StokHareketi nesnesi üretir.
     * 
     * @param csvSatir Virgülle ayrılmış CSV satırı
     * @return Yeni StokHareketi nesnesi
     */
    public static StokHareketi fromCSV(String csvSatir) {
        if (csvSatir == null || csvSatir.trim().isEmpty()) {
            return null;
        }
        String[] parcalar = csvSatir.split(",");
        if (parcalar.length < 6) {
            throw new IllegalArgumentException("Geçersiz StokHareketi CSV satırı (en az 6 sütun bekleniyor): " + csvSatir);
        }
        // Başlık satırı kontrolü
        if (parcalar[0].trim().equalsIgnoreCase("urunId")) {
            return null;
        }
        int urunId = Integer.parseInt(parcalar[0].trim());
        String tur = parcalar[1].trim();
        int miktar = Integer.parseInt(parcalar[2].trim());
        String tarih = parcalar[3].trim();
        int kaynakDepoId = Integer.parseInt(parcalar[4].trim());
        int hedefDepoId = Integer.parseInt(parcalar[5].trim());

        return new StokHareketi(urunId, tur, miktar, tarih, kaynakDepoId, hedefDepoId);
    }

    @Override
    public String toString() {
        return "StokHareketi{" +
                "urunId=" + urunId +
                ", tur='" + tur + '\'' +
                ", miktar=" + miktar +
                ", tarih='" + tarih + '\'' +
                ", kaynakDepoId=" + kaynakDepoId +
                ", hedefDepoId=" + hedefDepoId +
                '}';
    }
}
