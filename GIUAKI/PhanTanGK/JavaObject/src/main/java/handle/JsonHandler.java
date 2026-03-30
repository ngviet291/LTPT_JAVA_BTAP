/*
 * @ (#) JsonHandler.java     1.0    3/17/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package handle;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/17/2026
 * @version:    1.0
 */

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonHandler {
    public static JsonArray readFromFile(File fileJsonArr){
        try(
                JsonReader read = Json.createReader(new FileReader(fileJsonArr));

                ) {
           return read.readArray();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
