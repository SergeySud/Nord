package com.example.nord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinkService {
    public final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public UUID addLink(LinkEntity toSave) {
        linkRepository.save(toSave);
        return toSave.getUuid();
    }

    public String deleteLink(UUID uuid) {
        Optional<LinkEntity> entity = findLinkEntityByUuid(uuid);
        if (entity.isPresent()) {
            linkRepository.delete(entity.get());
            return "Ok";
        } else {
            return "None";

        }
    }

    public Optional<LinkEntity> findLinkEntityByUuid(UUID uuid) {
        return linkRepository.findLinkEntityByUuid(uuid);
    }

    public List<LinkEntity> findAll() {
        return linkRepository.getAll();
    }
}

