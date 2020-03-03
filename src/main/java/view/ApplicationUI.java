package view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.PresentationModel;

public class ApplicationUI extends BorderPane implements ViewMixin{
    private final PresentationModel model;

    public ApplicationUI(PresentationModel model, Stage prevStage) {
        this.model = model;
//        initializeSelf();
//        initializeControls();
//        layoutControls();
//        setupValueChangedListeners();

        init();
    }

    @Override
    public void initializeSelf() {

    }

    @Override
    public void initializeControls() {

    }

    @Override
    public void layoutControls() {

        setCenter(new Button("Ikk"));
    }

    @Override
    public void setupEventHandlers() {

    }

    @Override
    public void setupValueChangedListeners() {

    }

    @Override
    public void setupBindings() {

    }
}
