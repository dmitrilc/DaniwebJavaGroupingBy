package com.example;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Entry {
    public static void main(String[] args){
        var cakes = List.of(
            new Cake(Shape.TRIANGLE, Color.BLUE, BigDecimal.valueOf(4.99)),
            new Cake(Shape.CIRCLE, Color.RED, BigDecimal.valueOf(3.99)),
            new Cake(Shape.DIAMOND, Color.CYAN, BigDecimal.valueOf(5.25)),
            new Cake(Shape.CIRCLE, Color.GREEN, BigDecimal.valueOf(3.49)),
            new Cake(Shape.DIAMOND, Color.BLACK, BigDecimal.valueOf(5.99))
        );

        //groupByShape(cakes);
        //groupByShapeThenColor(cakes);
        groupByShapeThenColorCustomMap(cakes);
    }

    private static void groupByShape(List<Cake> cakes){
        Map<Shape, List<Cake>> cakeGroups = cakes.stream()
                .collect(Collectors.groupingBy(Cake::shape)
                );

        cakeGroups.entrySet().forEach(System.out::println);
    }

    private static void groupByShapeThenColor(List<Cake> cakes){
        Map<Shape, Map<Color, List<Cake>>> cakeGroups;
        cakeGroups = cakes.stream().collect(Collectors.groupingBy(
                Cake::shape,
                Collectors.groupingBy(Cake::color)
        ));

        cakeGroups.entrySet().forEach(System.out::println);
    }

    private static void groupByShapeThenColorCustomMap(List<Cake> cakes){
        var cakeGroups = cakes.stream().collect(Collectors.groupingBy(
                Cake::shape,
                TreeMap::new,
                Collectors.groupingBy(Cake::color)
        ));

        cakeGroups.entrySet().forEach(System.out::println);
    }

}

record Cake(Shape shape, Color color, BigDecimal price){}

enum Shape { TRIANGLE, CIRCLE, DIAMOND }