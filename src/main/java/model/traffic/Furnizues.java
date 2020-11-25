package model.traffic;

import model.items.Malli;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Furnizues extends Traffic {

    public Furnizues() {
    }

    public Furnizues(String emri, String adresa, String tel, String email, long nipt,
                     long nrTVSH, String nrBiznesit, String nrLicenses) {
        super(emri, adresa, tel, email, nipt, nrTVSH, nrBiznesit, nrLicenses);
    }
}
