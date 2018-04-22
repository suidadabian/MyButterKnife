package com.lixiaofeng.mbk_compiler;


import com.lixiaofeng.mbk_annotation.BindView;
import com.lixiaofeng.mbk_annotation.OnClick;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;

public class OnClickMethod {
    private ExecutableElement methodElement;
    private int[] resIds;

    public OnClickMethod(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.METHOD) {
            throw new IllegalArgumentException(String.format("Only methods can be annotated with @%s", BindView.class.getSimpleName()));
        }

        methodElement = (ExecutableElement) element;
        OnClick onClick = methodElement.getAnnotation(OnClick.class);
        resIds = onClick.value();
    }

    public ExecutableElement getMethodElement() {
        return methodElement;
    }

    public int[] getResIds() {
        return resIds;
    }
}
