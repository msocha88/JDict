package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.micsoc.dictionary.model.Entry;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.repository.EntryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryService {

    @Autowired
    EntryRepository entryRepository;

    public List<Entry> findEntriesFromUser(User user) {

        List<Entry> list = new ArrayList<>();

        for (Entry entry : entryRepository.findAll()) {

            if (entry.getUserEntry().getUserName().equals(user.getUserName())) {
                list.add(entry);
            }
        }

        return list;

    }

    public List<Entry> findByCategoryId(Long id) {

        List<Entry> list = new ArrayList<>();

        for (Entry entry : entryRepository.findAll()) {
            if (entry.getCategory().getId() == id) {
                list.add(entry);
            }
        }
        return list;
    }
}
