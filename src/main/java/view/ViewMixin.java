package view;

/**
 * @author Dieter Holz
 */
public interface ViewMixin {

	default void init() {
        initializeSelf();
        initializeControls();
		layoutControls();
		setupEventHandlers();
		setupValueChangedListeners();
		setupBindings();
	}

	default void initializeSelf(){
    }

	void initializeControls();

	void layoutControls();

	default void setupEventHandlers() {
	}

	default void setupValueChangedListeners() {
	}

	default void setupBindings() {
	}
}
