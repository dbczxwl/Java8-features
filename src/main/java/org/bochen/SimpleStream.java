package org.bochen;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class SimpleStream {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 120, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        System.out.println(getDishByCollection(menu));
        System.out.println(getDishByStream(menu));
    }

    public static List<String> getDishByCollection(List<Dish> menu) {

//        filter low Calories
        List<Dish> lowCalories = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCalories.add(d);
            }
        }

        //    sort by name
        lowCalories.sort(Comparator.comparingInt(Dish::getCalories));

        // get name list
        List<String> dishNameList = new ArrayList<>();

        for (Dish d : lowCalories) {
            dishNameList.add(d.getName());
        }
        return dishNameList;
    }

    public static List<String> getDishByStream(List<Dish> menu){
        return menu.stream().filter(d->d.getCalories()<400)
                .sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
    }

}
