package com.poc.model;

import com.poc.ValueModel;

import java.util.EnumMap;
import java.util.Map;

public class PocModel {

    public Map<ModelProperties, ValueModel<?>> model = new EnumMap<>(ModelProperties.class);

    public PocModel() {
        model.put(ModelProperties.TEXT_AREA, new ValueModel<String>(null));
        model.put(ModelProperties.FIRST_NAME, new ValueModel<String>(null));
        model.put(ModelProperties.LAST_NAME, new ValueModel<String>(null));
        model.put(ModelProperties.DATE_OF_BIRTH, new ValueModel<String>(null));
        model.put(ModelProperties.ZIP, new ValueModel<String>(null));
        model.put(ModelProperties.ORT, new ValueModel<String>(null));
        model.put(ModelProperties.STREET, new ValueModel<String>(null));
        model.put(ModelProperties.IBAN, new ValueModel<String>(null));
        model.put(ModelProperties.BIC, new ValueModel<String>(null));
        model.put(ModelProperties.VALID_FROM, new ValueModel<String>(null));
        model.put(ModelProperties.MALE, new ValueModel<Boolean>(null));
        model.put(ModelProperties.FEMALE, new ValueModel<Boolean>(null));
        model.put(ModelProperties.DIVERSE, new ValueModel<Boolean>(null));
    }

    public void action() {
        for(var val : ModelProperties.values()) {
            System.out.println(val.toString() + ": " + model.get(val).getField());
        }
    }
}
