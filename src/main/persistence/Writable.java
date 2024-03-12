package persistence;

import org.json.JSONObject;

// ensures that all the class are able to write to the JSON file.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
