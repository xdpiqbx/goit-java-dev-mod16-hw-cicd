package com.dpiqb.notes;

import org.springframework.stereotype.Service;

@Service
public class NoteValidateService {
  public boolean isNotEmptyNote(Note note){
    String title = note.getTitle().trim();
    String content = note.getContent().trim();
    if(note == null){
      return false;
    }
    if(title.equals("") && content.equals("")){
      return false;
    }
    return true;
  }
}
