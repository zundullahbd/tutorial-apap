package apap.tutorial.belajarbelajar.service;

import apap.tutorial.belajarbelajar.model.PengajarModel;
import apap.tutorial.belajarbelajar.model.PenyelenggaraModel;
import apap.tutorial.belajarbelajar.repository.PengajarDb;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PengajarServiceImpl implements PengajarService {
    @Autowired
    PengajarDb pengajarDb;

    @Override
    public void addPengajar(PengajarModel pengajar) {
        pengajarDb.save(pengajar);
    }

    @Override
    public void updatePengajar(PengajarModel pengajar) {
        pengajarDb.save(pengajar);
    }

    @Override
    public void deletePengajar(PengajarModel pengajar) {
        pengajarDb.delete(pengajar);
    }

    @Override
    public PengajarModel findPengajar(Long no) {
        Optional<PengajarModel> pengajar = pengajarDb.findById(no);
        if(pengajar.isPresent()) {
            return pengajar.get();
        }
        else return null;
    }
}
