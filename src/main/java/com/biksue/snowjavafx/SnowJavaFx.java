package com.biksue.snowjavafx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowJavaFx extends Application {
    private List<SnowParticle> particles;
    private static final int NUM_PARTICLES = 10000;
    private static final Random RANDOM = new Random();
    private GraphicsContext g;

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();
    }

    public Parent createContent() {
        particles = new ArrayList<>();
        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles.add(new SnowParticle(RANDOM.nextInt(1280), 0, Duration.millis(i * 10)));
        }
        var root = new Pane();

        root.setPrefSize(1280, 720);
        Canvas canvas = new Canvas(1280, 720);

        g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        root.getChildren().add(canvas);

        var timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private void onUpdate() {
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        g.setFill(Color.WHITE);

        particles.forEach(p -> {
            p.onUpdate();
            g.fillRect(p.x, p.y, 1, 1);
        });

    }

    private static class SnowParticle {
        int x;
        int y;
        Duration delay;
        double t = 0.0;

        SnowParticle(int x, int y, Duration delay) {
            this.x = x;
            this.y = y;
            this.delay = delay;
        }

        void onUpdate() {
            t += 0.016;
            if (t < delay.toSeconds())
                return;
            y += RANDOM.nextInt(15);

        }
    }

    public static void main(String[] args) {
        launch();
    }
}