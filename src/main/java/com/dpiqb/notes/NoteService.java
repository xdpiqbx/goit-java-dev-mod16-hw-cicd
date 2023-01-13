package com.dpiqb.notes;

import com.dpiqb.notes.storage.Helper;
import com.dpiqb.notes.storage.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NoteService {
  private final NoteRepository noteRepository;
  private final NoteValidateService noteValidateService;
  public List<Note> listAll(){
    return noteRepository.findAll();
  };
  public void add(Note note){
    if(noteValidateService.isNotEmptyNote(note)){
      noteRepository.save(note);
    }
  }
  public void deleteById(long id){
    noteRepository.deleteById(id);
  }
  public void update(Note note){
    if(noteValidateService.isNotEmptyNote(note)) {
      noteRepository.save(note);
    }
  }
  public Note getById(long id){
    return noteRepository.getReferenceById(id);
  }

  public void addMockData(){
    if(noteRepository.count() < 10){
      Map<String, String> mockData = Helper.getMockData();
      mockData.forEach((key, value) -> {
        Note note = new Note();
        note.setTitle(key);
        note.setContent(value);
        this.add(note);
      });
    }
  }
}
