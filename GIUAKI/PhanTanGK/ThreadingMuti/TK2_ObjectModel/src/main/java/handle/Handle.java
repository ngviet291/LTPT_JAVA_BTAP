/*
 * @ (#) Handle.java     1.0    3/13/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package handle;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/13/2026
 * @version:    1.0
 */

import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.*;
import java.util.Map;

public class Handle {
    private static final Map<String, ?> map = Map.of(JsonGenerator.PRETTY_PRINTING,true);
    private static JsonWriterFactory factory = Json.createWriterFactory(map);

    public static JsonArray readArray(File file){
        try(
                JsonReader readerArray = Json.createReader(new FileReader(file));

                ){
            return   readerArray.readArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void write(File file ,JsonArray ja) {
        try(
                JsonWriter w = factory.createWriter(new FileWriter(file))
        ) {
            w.writeArray(ja);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static  String toJson(JsonObject jo){
        try(
                StringWriter w = new StringWriter();
                JsonWriter writer =factory.createWriter(w);
                ) {
            writer.write(jo);
            return w.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

