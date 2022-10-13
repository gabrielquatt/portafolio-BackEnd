package com.example.api.servicios;
import com.example.api.model.InformationEdu;
import com.example.api.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {

    @Autowired
    private TitleRepository title;

    public List<InformationEdu> getAll() {
        return this.title.findAll();
    }

    public void delete(Long id) {
        this.title.deleteById(id);
    }

    public Optional<InformationEdu> getById(Long id) {
        return this.title.findById(id);
    }

    public boolean save(InformationEdu t) {
        this.title.saveAndFlush(t);
        return true;
    }


}
