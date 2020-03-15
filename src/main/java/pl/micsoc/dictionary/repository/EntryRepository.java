package pl.micsoc.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.micsoc.dictionary.model.Entry;
import pl.micsoc.dictionary.model.User;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {

    Entry findEntryById(Integer id);
}
