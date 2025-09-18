/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan5;

/**
 *
 * @author Alif
 */
import java.util.Scanner;

// Exception jika kursi sudah terisi
class KursiTidakTersediaException extends Exception {
    public KursiTidakTersediaException(String message) {
        super(message);
    }
}

// Exception jika pembayaran kurang
class PembayaranKurangException extends Exception {
    public PembayaranKurangException(String message) {
        super(message);
    }
}

// Exception jika input tidak valid
class InputTidakValidException extends Exception {
    public InputTidakValidException(String message) {
        super(message);
    }
}

public class TiketBioskop {
    private boolean[] kursi = new boolean[5]; // false = kosong, true = terisi
    private final int hargaTiket = 50000; 

    public int pesanKursi(int nomorKursi, int uangBayar) 
            throws KursiTidakTersediaException, PembayaranKurangException, InputTidakValidException {

        if (nomorKursi < 1 || nomorKursi > kursi.length) {
            throw new InputTidakValidException("Nomor kursi tidak valid! Pilih kursi 1-" + kursi.length);
        }
        if (kursi[nomorKursi - 1]) {
            throw new KursiTidakTersediaException("Kursi nomor " + nomorKursi + " sudah terisi!");
        }
        if (uangBayar < hargaTiket) {
            throw new PembayaranKurangException("Uang tidak cukup. Harga tiket Rp " + hargaTiket);
        }

        kursi[nomorKursi - 1] = true; // tandai kursi sebagai terisi
        int kembalian = uangBayar - hargaTiket;
        System.out.println("Pesan kursi berhasil! Nomor kursi: " + nomorKursi);
        System.out.println("Kembalian Anda: Rp " + kembalian);
        return kembalian; // kembalian dipakai lagi kalau beli tiket lagi
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TiketBioskop bioskop = new TiketBioskop();

        System.out.println("=== PEMESANAN TIKET BIOSKOP ===");
        System.out.println("Harga Tiket : Rp 50.000");

        boolean lanjut = true;
        int saldo = 0; // simpan kembalian
        while (lanjut) {
            try {
                System.out.print("\nMasukkan nomor kursi (1-5): ");
                int nomorKursi = sc.nextInt();

                int uang;
                if (saldo > 0) {
                    System.out.println("Anda punya saldo kembalian: Rp " + saldo);
                    System.out.print("Apakah ingin pakai saldo ini? (y/t): ");
                    String pakaiSaldo = sc.next();
                    if (pakaiSaldo.equalsIgnoreCase("y")) {
                        uang = saldo;
                        saldo = 0; // reset saldo sementara, nanti dihitung ulang
                    } else {
                        System.out.print("Masukkan uang pembayaran: Rp ");
                        uang = sc.nextInt();
                    }
                } else {
                    System.out.print("Masukkan uang pembayaran: Rp ");
                    uang = sc.nextInt();
                }

                // pesan kursi & update saldo kembalian
                saldo = bioskop.pesanKursi(nomorKursi, uang);

            } catch (KursiTidakTersediaException | PembayaranKurangException | InputTidakValidException e) {
                System.out.println("️Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("️Terjadi kesalahan sistem: " + e.getMessage());
            }

            // pilihan lanjut atau tidak
            System.out.print("\n Apakah ingin membeli tiket lagi? (y/t): ");
            String jawab = sc.next();
            if (jawab.equalsIgnoreCase("t")) {
                lanjut = false;
            }
        }

        System.out.println("\n Terima kasih sudah memesan tiket! ");
    }
}


