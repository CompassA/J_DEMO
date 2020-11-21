package com.study.me.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author tomato
 * Created on 2020.11.16
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type",
        defaultImpl = SubType1.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SubType1.class, name = "sub1"),
        @JsonSubTypes.Type(value = SubType2.class, name = "sub2")
})
public abstract class BaseClass {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        BaseClass class2 = new SubType2();
        ((SubType2) class2).setVal1(23232);
        ((SubType2) class2).setStr1("23r32fdsafdaf");

        String sub1Json = "{ \"var0\": 1, \"var\": 2, \"num\": 3}";

        SubType1 sub1Clone = objectMapper.readValue(sub1Json, SubType1.class);
        SubType2 sub2Clone = objectMapper.readValue(
                objectMapper.writeValueAsString(class2), SubType2.class);
        System.out.println("done");
    }
}
