package com.dpiqb.notes.storage;

import com.dpiqb.notes.NoteCreator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteCreatorRepository extends JpaRepository<NoteCreator, Long> {
  NoteCreator findByName(String name);
}
