package apap.tutorial.belajarbelajar.service;

import apap.tutorial.belajarbelajar.model.PengajarModel;

public interface PengajarService {
    void addPengajar(PengajarModel pengajar);

    PengajarModel findPengajar(Long no);

    void updatePengajar(PengajarModel pengajar);

    void deletePengajar(PengajarModel pengajar);
}
