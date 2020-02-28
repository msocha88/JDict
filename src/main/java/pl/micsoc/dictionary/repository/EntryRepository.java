package pl.micsoc.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.micsoc.dictionary.model.Entry;
@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    Entry findEntryById(Long id);
}
