package org.bochen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);
        createStreamFromFactory().forEach(System.out::println);
        createStreamFromArray().forEach(System.out::println);
        createStreamFromIterator().limit(10).forEach(System.out::println);
        createStreamFromGenerate().limit(10).forEach(System.out::println);
        createObjStreamFromGenerate().limit(10).forEach(System.out::println);
    }

    private static Stream<String> createStreamFromCollection() {
        List<String> strList = Arrays.asList("hello", "world", "who", "are", "you");
        return strList.stream();
    }

    private static Stream<String> createStreamFromFactory() {
        return Stream.of("fine", "thank", "you");
    }

    private static Stream<String> createStreamFromArray() {
        return Arrays.stream(new String[]{"you", "are", "welcome"});
    }

    private static Stream<String> createStreamFromFile() {
        Path path = Paths.get("C:\\hello.txt");
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(System.out::println);
            return stream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Integer> createStreamFromIterator() {
        return Stream.iterate(0, n -> n + 2);
    }

    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random);
    }

    private static Stream<Obj> createObjStreamFromGenerate(){
        return Stream.generate(new ObjSupplier());
    }

    static class ObjSupplier implements Supplier<Obj> {

        private final Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            int index = random.nextInt(100);
            return new Obj(index, "Name->" + index);
        }
    }

    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
