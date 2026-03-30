/*
 * @ (#) HandleSK.java     1.0    3/16/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit.handleAPI;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/16/2026
 * @version:    1.0
 */

import iuh.fit.entity.Category;
import iuh.fit.entity.Product;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HandleSK {
    public static List<Product> productList(File jsonFile){

        String currentObject ="";
        String keyName="";

        List<Product> products = new ArrayList<>();
        Product product = null;
        Category cate = null;

        try(
                FileReader reader = new FileReader(jsonFile);
                JsonParser parser = Json.createParser(reader);
        ) {
            while (parser.hasNext()){
                JsonParser.Event event = parser.next();

                switch (event){

                    case START_ARRAY -> {
                        if (currentObject.equals("")){
                            currentObject = "products";
                        } else if (keyName.equals("category")){
                            currentObject = "categories";
                            if(product != null)
                                product.setCate(new ArrayList<>());
                        }
                    }

                    case START_OBJECT -> {
                        if (currentObject.equals("products")){
                            product = new Product();
                            currentObject = "product";

                        } else if (currentObject.equals("categories")){
                            cate = new Category();
                            currentObject = "category";
                        }
                    }

                    case END_OBJECT -> {
                        if (currentObject.equals("category")){
                            product.getCate().add(cate);
                            currentObject = "categories";

                        } else if (currentObject.equals("product")){
                            products.add(product);
                            currentObject = "products";
                        }
                    }

                    case END_ARRAY -> {
                        if ("categories".equals(currentObject)){
                            currentObject = "product";
                        }
                    }

                    case KEY_NAME -> keyName = parser.getString();

                    case VALUE_STRING -> {
                        String value = parser.getString();

                        if ("name".equals(keyName)){
                            if ("product".equals(currentObject)){
                                product.setName(value);
                            } else if ("category".equals(currentObject)){
                                cate.setName(value);
                            }
                        }
                        else if ("type".equals(keyName)){
                            product.setType(value);
                        }
                        else if ("manufacturer".equals(keyName)){
                            product.setManufacturer(value);
                        }
                        else if ("id".equals(keyName)){
                            cate.setId(value);
                        }
                    }

                    case VALUE_NUMBER -> {
                        if ("price".equals(keyName)){
                            product.setPrice(parser.getBigDecimal().floatValue());
                        }
                        else if ("sku".equals(keyName)){
                            product.setSku(parser.getBigDecimal().longValue());
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    public static void writeJson(List<Product> products, String fileName){
        try(
                FileWriter writer = new FileWriter(fileName);
                JsonGenerator gen = Json.createGenerator(writer);
        ){
            gen.writeStartArray();

            for(Product p : products){
                gen.writeStartObject()
                        .write("name", p.getName())
                        .write("type", p.getType())
                        .write("manufacturer", p.getManufacturer())
                        .write("price", p.getPrice())
                        .write("sku", p.getSku());

                // category array
                gen.writeStartArray("category");

                if(p.getCate() != null){
                    for(Category c : p.getCate()){
                        gen.writeStartObject()
                                .write("id", c.getId())
                                .write("name", c.getName())
                                .writeEnd();
                    }
                }

                gen.writeEnd(); // end category array
                gen.writeEnd(); // end product
            }

            gen.writeEnd(); // end array

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        List<Product> ds = HandleSK.productList(new File("json/products.json"));
        ds.forEach(v->{
            System.out.println(v);
        });
        writeJson(ds,"json/vantruong.json");
    }
}
