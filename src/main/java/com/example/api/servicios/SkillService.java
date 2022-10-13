package com.example.api.servicios;

import com.example.api.model.Skill;
import com.example.api.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skill;

    public List<Skill> getAll() {
        return this.skill.findAll();
    }

    public void delete(Long id) {
        this.skill.deleteById(id);
    }

    public Optional<Skill> getById(Long id) {
        return this.skill.findById(id);
    }

    public boolean save(Skill s) {
        this.skill.saveAndFlush(s);
        return true;
    }

}
