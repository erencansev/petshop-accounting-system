import java.util.*;


class Urun {
    String kategori;
    String altKategori;
    String marka;
    int adet;
    double fiyat;

    public Urun(String kategori, String altKategori, String marka, int adet, double fiyat) {
        this.kategori = kategori;
        this.altKategori = altKategori;
        this.marka = marka;
        this.adet = adet;
        this.fiyat = fiyat;
    }
}

public class accounting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> cüzdan = new HashMap<>();
        Map<String, Urun> stok = new HashMap<>();
        Map<String, List<String>> kategoriAltKategoriler = new HashMap<>();
        Map<String, Integer> stokAdet = new HashMap<>();

        kategoriAltKategoriler.put("kedi", Arrays.asList("kedi kumu", "kedi maması", "oyuncak"));
        kategoriAltKategoriler.put("köpek", Arrays.asList("köpek maması", "tasma", "yatak"));
        kategoriAltKategoriler.put("kuş", Arrays.asList("kafes", "kuş yemi", "oyuncak"));
        kategoriAltKategoriler.put("balık", Arrays.asList("balık yemi", "akvaryum", "dekor"));

        System.out.println("Pet Shop Sahibi Girişi");
        System.out.print("Kullanıcı Adı: ");
        String kullaniciAdi = scanner.nextLine();

        System.out.print("Mağaza Adı: ");
        String magazaAdi = scanner.nextLine();

        System.out.println("Para Birimi Seçenekleri:");
        System.out.println("1. Türk Lirası (TL)");
        System.out.println("2. Amerikan Doları (USD)");
        System.out.println("3. Euro (EUR)");
        System.out.print("Para Birimini Seçin (1/2/3): ");
        int paraBirimiSecimi = scanner.nextInt();
        String paraBirimi = "";
        switch (paraBirimiSecimi) {
            case 1:
                paraBirimi = "TL";
                break;
            case 2:
                paraBirimi = "USD";
                break;
            case 3:
                paraBirimi = "EUR";
                break;
        }

        System.out.print("Dükkan Konumu (Şehir): ");
        String sehir = scanner.next();

        System.out.println("Başlıyoruz! İşte Pet Shop sahibi olarak yolculuğunuz başlıyor...");
        System.out.println("-------------------------------------------------------");
        System.out.println("Hoş geldin, " + kullaniciAdi + "!");
        System.out.println("Mağaza Adı: " + magazaAdi);
        System.out.println("Para Birimi: " + paraBirimi);
        System.out.println("Dükkan Konumu (Şehir): " + sehir);
        System.out.println("-------------------------------------------------------");

        double bakiye = 00.0;

        while (true) {
            System.out.println("Lütfen yapmak istediğiniz işlemi seçin:");
            System.out.println("1. Ürün Ekle");
            System.out.println("2. Ürün Sat");
            System.out.println("3. Güncel Bakiyeyi Görüntüle");
            System.out.println("4. Ürün Stok Sorgula");
            System.out.println("5. Mağaza Stok Durumu görüntüle");
            System.out.println("0. Çıkış");


            System.out.print("Seçiminizi yapın (1/2/3/4/5/6/7/0): ");
            int secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    System.out.print("Ürün Kategorisi Seçin (kedi, köpek, kuş, balık): ");
                    String kategori = scanner.next();
                    List<String> altKategoriler = kategoriAltKategoriler.get(kategori);

                    if (altKategoriler == null) {
                        System.out.println("Geçersiz kategori seçimi.");
                        break;
                    }

                    System.out.println("Alt Kategoriler:");
                    for (int i = 0; i < altKategoriler.size(); i++) {
                        System.out.println((i + 1) + ". " + altKategoriler.get(i));
                    }

                    System.out.print("Alt Kategori Seçin (1/2/3): ");
                    int altKategoriSecimi = scanner.nextInt();

                    if (altKategoriSecimi < 1 || altKategoriSecimi > altKategoriler.size()) {
                        System.out.println("Geçersiz alt kategori seçimi.");
                        break;
                    }

                    String secilenAltKategori = altKategoriler.get(altKategoriSecimi - 1);

                    System.out.println("Marka Seçenekleri:");
                    System.out.println("1. Marka 1");
                    System.out.println("2. Marka 2");
                    System.out.println("3. Marka 3");
                    System.out.print("Markayı Seçin (1/2/3): ");
                    int markaSecimi = scanner.nextInt();

                    String marka = "";
                    switch (markaSecimi) {
                        case 1:
                            marka = "Marka 1";
                            break;
                        case 2:
                            marka = "Marka 2";
                            break;
                        case 3:
                            marka = "Marka 3";
                            break;
                    }

                    scanner.nextLine();
                    System.out.print("Ürün Adı: ");
                    String urunAdi = scanner.nextLine();
                    System.out.print("Ürün Adedi: ");
                    int adet = scanner.nextInt();
                    System.out.print("Ürün Fiyatı: ");
                    double fiyat = scanner.nextDouble();

                    Urun yeniUrun = new Urun(kategori, secilenAltKategori, marka, adet, fiyat);
                    stok.put(urunAdi, yeniUrun);


                    if (stokAdet.containsKey(urunAdi)) {
                        int mevcutAdet = stokAdet.get(urunAdi);
                        stokAdet.put(urunAdi, mevcutAdet + adet);
                    } else {
                        stokAdet.put(urunAdi, adet);
                    }

                    System.out.println("Ürün Eklendi.");
                    break;

                case 2:
                    System.out.print("Satmak istediğiniz ürünün tam adını girin: ");
                    scanner.nextLine();
                    String satilanUrunAdi = scanner.nextLine();

                    if (stok.containsKey(satilanUrunAdi)) {
                        Urun satilanUrun = stok.get(satilanUrunAdi);

                        if (satilanUrun.adet > 0) {
                            System.out.print("Satılacak Ürün Adedi: ");
                            int satilanAdet = scanner.nextInt();

                            if (satilanAdet <= satilanUrun.adet) {
                                double toplamFiyat = satilanAdet * satilanUrun.fiyat;
                                bakiye += toplamFiyat;


                                int mevcutAdet = stokAdet.get(satilanUrunAdi);
                                stokAdet.put(satilanUrunAdi, mevcutAdet - satilanAdet);

                                satilanUrun.adet -= satilanAdet;

                                System.out.println("Ürün Satıldı. Toplam Fiyat: " + toplamFiyat + " " + paraBirimi);
                            } else {
                                System.out.println("Stokta yeterli ürün yok.");
                            }
                        } else {
                            System.out.println("Bu ürün stokta yok.");
                        }
                    } else {
                        System.out.println("Ürün bulunamadı.");
                    }
                    break;

                case 3:
                    System.out.println("Güncel Bakiye: " + bakiye + " " + paraBirimi);
                    break;
                case 4:
                    System.out.print("Sorgulamak istediğiniz ürünün adını girin: ");
                    scanner.nextLine();
                    String sorgulananUrunAdi = scanner.nextLine();

                    if (stok.containsKey(sorgulananUrunAdi)) {
                        Urun sorgulananUrun = stok.get(sorgulananUrunAdi);
                        System.out.println("Ürün Adı: " + sorgulananUrunAdi);
                        System.out.println("Kategori: " + sorgulananUrun.kategori);
                        System.out.println("Alt Kategori: " + sorgulananUrun.altKategori);
                        System.out.println("Marka: " + sorgulananUrun.marka);
                        System.out.println("Adet: " + sorgulananUrun.adet);
                        System.out.println("Fiyat: " + sorgulananUrun.fiyat + " " + paraBirimi);
                    } else {
                        System.out.println("Ürün bulunamadı.");
                    }
                    break;

                case 5:
                    System.out.println("Stok Listesi:");
                    for (Map.Entry<String, Urun> entry : stok.entrySet()) {
                        String urunAdi2 = entry.getKey();
                        Urun urun = entry.getValue();
                        System.out.println("Ürün Adı: " + urunAdi2);
                        System.out.println("Kategori: " + urun.kategori);
                        System.out.println("Alt Kategori: " + urun.altKategori);
                        System.out.println("Marka: " + urun.marka);
                        System.out.println("Adet: " + urun.adet);
                        System.out.println("Fiyat: " + urun.fiyat + " " + paraBirimi);
                        System.out.println("-----------------------------------------");
                    }
                    break;

                case 0:
                    System.out.println("Teşekkür ederiz! Pet Shop sahibi olarak yolculuğunuzu tamamladınız.");
                    System.exit(0);


                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
}
