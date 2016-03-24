package com.vlabs.medicinetracker.units;

import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.vlabs.medicinetracker.units.metric.mmHgArt;

/**
 * Created by vlad on 3/24/16.
 */
@com.raizlabs.android.dbflow.annotation.TypeConverter
public class mmHgArtConverter extends TypeConverter<Integer, mmHgArt> {

    @Override
    public Integer getDBValue(final mmHgArt model) {
        return model.getValue();
    }

    @Override
    public mmHgArt getModelValue(final Integer data) {
        return new mmHgArt(data);
    }
}
