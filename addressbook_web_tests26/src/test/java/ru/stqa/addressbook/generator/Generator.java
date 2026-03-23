package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.stqa.addressbook.models.Group;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ru.stqa.addressbook.common.CommonFunctions.randomString;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;
    @Parameter(names={"--output", "-o"})
    String output;
    @Parameter(names={"--format", "-f"})
    String format;
    @Parameter(names={"--count", "-c"})
    int count;
    public static void main(String[] args) throws IOException {
        var generator=new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data=generate();
        save(data);
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT)
                    .writeValue(new File(output), data);
        }
        else throw new IllegalArgumentException("Неизестный формат данных "+type);
    }

    private Object generate() {
        if ("groups".equals(type)){
            return generateGroups();
        }
        else if ("contacts".equals(type)){
            return generateContacts();
        }
        else throw new IllegalArgumentException("Неизвестный тип данных "+type);
    }

    private Object generateContacts() {
        return null;
    }

    private Object generateGroups() {
        var result = new ArrayList<Group>();
        for (int i = 0; i < count; i++) {
            result.add(new Group().withName(randomString()).withHeader(randomString()).withFooter(randomString()));
        }
        return result;
    }
}
