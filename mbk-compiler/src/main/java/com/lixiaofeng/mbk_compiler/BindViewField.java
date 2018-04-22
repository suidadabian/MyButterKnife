package com.lixiaofeng.mbk_compiler;

import com.lixiaofeng.mbk_annotation.BindView;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.VariableElement;

public class BindViewField {
    private VariableElement fieldElement;
    private int resId;

    public BindViewField(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.FIELD) {
            throw new IllegalArgumentException(String.format("Only fields can be annotated with @%s", BindView.class.getSimpleName()));
        }

        fieldElement = (VariableElement) element;
        BindView bindView = fieldElement.getAnnotation(BindView.class);
        resId = bindView.value();
    }

    public VariableElement getFieldElement() {
        return fieldElement;
    }

    public int getResId() {
        return resId;
    }
}
