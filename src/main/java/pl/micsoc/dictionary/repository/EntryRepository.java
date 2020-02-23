package pl.micsoc.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.micsoc.dictionary.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
