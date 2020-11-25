package model.traffic;

public class Klient extends Traffic {

    public Klient() {}

    public Klient(String emri, String adresa, String tel, String email, long nipt,
                  long nrTVSH, String nrBiznesit, String nrLicenses) {
        super(emri, adresa, tel, email, nipt, nrTVSH, nrBiznesit, nrLicenses);
    }
}
