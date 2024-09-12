package patron.mvc.mvc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.repository.OdontologoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class OdontologoService {

    @Autowired
    private  OdontologoRepository odontologoRepository;

    public Odontologo saveOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }
    public Odontologo updateOdontologo(Odontologo odontologo) throws Exception {
        Optional<Odontologo> odontologoToUpdate = odontologoRepository.findById(odontologo.getId());
        Odontologo odontologoUpdated;
        if(odontologoToUpdate.isPresent()) {
            odontologoUpdated= odontologoRepository.save(odontologo);
        }else {
            throw new Exception("Odontologo no encontrado");
        }
        return odontologoUpdated;
    }
    public boolean deleteOdontologo(Long id) {
        boolean deleted = false;
        odontologoRepository.deleteById(id);
        deleted = true;
        return deleted;
    }
    public Odontologo getOdontologoById(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        return odontologo.orElse(null);
    }
    public List<Odontologo> getAllOdontologos() {
        return odontologoRepository.findAll();
    }
}
