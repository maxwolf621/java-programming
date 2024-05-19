package org.example.fp;

/**
 * The decorator pattern is powerful, but programmers often hesitate to use it
 * due to the burdensome hierarchy of classes and interfaces
 * <p>
 * Using lambda expression
 * Avoiding creating implementation classes to support the delegate interface
 * By chaining delegates to add behavior
 */


import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class Camera {
    private Function<Color, Color> filter;

    public Color capture(final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);
        //... more processing of color...
        return processedColor;
    }

    //... other functions that use the filter ...

    public void setFilters(final Function<Color, Color>... filters) {
        filter =
                Stream.of(filters)
                        .reduce((filter, next) -> filter.compose(next))
                        .orElse(color -> color);
    }

    public Camera() {
        // new an empty filter
        // also called dummy filter
        setFilters();
    }

    public static void main(final String[] args) {
        final Camera camera = new Camera();
        final Consumer<String> printCaptured = (filterInfo) ->
                System.out.println(
                        String.format(
                                "with %s: %s", filterInfo,
                                camera.capture(new Color(200, 100, 200))
                        )
                );

//        System.out.println("//" + "START:NOFILTER_OUTPUT");
//        printCaptured.accept("no filter");
//        System.out.println("//" + "END:NOFILTER_OUTPUT");
//
//        System.out.println("//" + "START:BRIGHT_OUTPUT");
//        camera.setFilters(Color::brighter);
//        printCaptured.accept("brighter filter");
//        System.out.println("//" + "END:BRIGHT_OUTPUT");
//
////        System.out.println("//" + "START:DARK_OUTPUT");
//        camera.setFilters(Color::darker);
//        printCaptured.accept("darker filter");
//        System.out.println("//" + "END:DARK_OUTPUT");

        System.out.println("//" + "START:BOTH_OUTPUT");
        camera.setFilters(Color::brighter, Color::darker);
        printCaptured.accept("brighter & darker filter");
        System.out.println("//" + "END:BOTH_OUTPUT");
    }

}
