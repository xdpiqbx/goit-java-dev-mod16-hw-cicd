package com.dpiqb.notes.storage;

import java.util.LinkedHashMap;
import java.util.Map;

public class Helper {
  public static Map<String, String> getMockData(){
    Map<String, String> data = new LinkedHashMap<>();
    data.put(
        "Single Responsibility Principle",
        "a class should do one thing and therefore it should have only a single reason to change"
    );
    data.put(
        "Open-Closed Principle",
        "classes should be open for extension and closed to modification"
    );
    data.put(
        "Liskov Substitution Principle",
        "subclasses should be substitutable for their base classes"
    );
    data.put(
        "Interface Segregation Principle",
        "is about separating the interfaces"
    );
    data.put(
        "Dependency Inversion Principle",
        "classes should depend upon interfaces or abstract classes instead of concrete classes and functions"
    );
    return data;
  }
}
