package patron.mvc.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.entity.Domicilio;
import patron.mvc.mvc.repository.DomicilioRepository;

import java.util.Optional;

@Service
public class DomicilioService {
    @Autowired
    private DomicilioRepository domicilioRepository;

    public Domicilio findDomicilio(Long id) {
        return domicilioRepository.getReferenceById(id);
    }
    public Domicilio save(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    public Domicilio actualizarDomicilio(Domicilio domicilio) {
        Optional<Domicilio> optional = domicilioRepository.findById(domicilio.getId());
        if (optional.isPresent()) {
            return domicilioRepository.save(domicilio);
        }
        return null;
    }
}
