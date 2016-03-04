package com.vlabs.medicinetracker.singleMeasurement;

/**
 * Created by vlad on 3/4/16.
 */
public class SingleInstancePresenterFactory {

    public static <> Presenter<T> createPresenter(SingleMeasurementPresenterInstance presenterInstance) {

        switch (presenterInstance) {
            case Height:
                return new HeightMeasurementPresenter();
            case Weight:
                return new WeightMeasurementPresenter();
            default:
                throw new RuntimeException("unknown presenter instance");
        }

    }

}
