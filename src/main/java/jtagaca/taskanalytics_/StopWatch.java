package jtagaca.taskanalytics_;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;



class StopWatch extends BorderPane {
//    private Text tTime;
//    private Button btControl;
//    private Button btClear;
    private int hours;
    private int minutes;
    private int seconds;
    private int allInSeconds;
    private boolean isRunning;
    private Timeline timeline;
    @FXML
    Label timerLabel;
    public StopWatch() {
    }

    public StopWatch(Label timerLabel) {
        hours = 0;
        minutes = 0;
        seconds = 0;
        allInSeconds = 0;
        this.timerLabel = timerLabel;


        // set up the time display
//        tTime = new Text();
//        tTime.setFont(Font.font("SansSerif", 50));
        setTime();

        // set up the control buttons
//        btControl = new Button("Start");
//        btClear = new Button("Clear");

//        btControl.setOnAction(e -> {
//            String text = btControl.getText();
//            if (text.equals("Start") || text.equals("Resume")) {
//                btControl.setText("Pause");
//                if (text.equals("Start")) {
//                    start();
//                } else {
//                    resume();
//                }
//            } else {
//                btControl.setText("Resume");
//                pause();
//            }
//        });

//        btClear.setOnAction(e -> {
//            btControl.setText("Start");
//            clear();
//        });

        // lay out the UI
//        HBox buttonBox = new HBox(10);
//        buttonBox.getChildren().addAll(btControl, btClear);
//        buttonBox.setAlignment(Pos.CENTER);
//
//        setCenter(tTime);
//        setBottom(buttonBox);
//        setMargin(tTime, new Insets(20, 50, 20, 50));
    }

    public void start() {
        isRunning = true;
        KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {
            seconds++;
            allInSeconds++;
            setTime();
            System.out.println(seconds);
        });

        timeline = new Timeline(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void stop() {
        timeline.pause();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void resume() {
        timeline.play();
    }

    public void clear() {
        timeline.stop();
        hours = 0;
        minutes = 0;
        seconds = 0;
        allInSeconds = 0;
        setTime();
    }

    public int getAllInSeconds() {
        return allInSeconds;
    }

    public void setTime() {
        // flip 60 seconds over to a minute
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }

        // flip 60 minutes over to an hour
        if (minutes == 60) {
            minutes = 0;
            hours++;
        }

        // ensure that values < 10 are padded with a 0
        String h = hours >= 10 ? String.valueOf(hours) : "0" + String.valueOf(hours);
        String m = minutes >= 10 ? String.valueOf(minutes) : "0" + String.valueOf(minutes);
        String s = seconds >= 10 ? String.valueOf(seconds) : "0" + String.valueOf(seconds);

        this.timerLabel.setText(h + ":" + m + ":" + s);
    }
    public String getTime() {
        return hours + ":" + minutes + ":" + seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}