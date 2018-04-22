package com.lixiaofeng.mbk_compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

public class AnnotatedClass {
    public TypeElement classElement;
    public List<BindViewField> fields;
    public List<OnClickMethod> methods;
    public Elements elementUtils;

    public AnnotatedClass(TypeElement classElement, Elements elementUtils) {
        this.classElement = classElement;
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.elementUtils = elementUtils;
    }

    public String getFullClassName() {
        return classElement.getQualifiedName().toString();
    }

    public void addField(BindViewField field) {
        fields.add(field);
    }

    public void addMethod(OnClickMethod method) {
        methods.add(method);
    }

    public JavaFile generateFinder() {
        MethodSpec.Builder injectMethodBuild = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(classElement.asType()), "host", Modifier.FINAL)
                .addParameter(TypeName.OBJECT, "source")
                .addParameter(TypeUtil.PROVIDER, "provider");

        for (BindViewField field : fields) {
            injectMethodBuild.addStatement("host.$N = ($T)(provider.findViewById(source, $L))",
                    field.getFieldElement().getSimpleName(),
                    ClassName.get(field.getFieldElement().asType()),
                    field.getResId());
        }

        if (methods.size() > 0) {
            injectMethodBuild.addStatement("$T listener", TypeUtil.ON_CLICK_LISTENER);
        }

        for (OnClickMethod method : methods) {
            TypeSpec listener = TypeSpec.anonymousClassBuilder("")
                    .addSuperinterface(TypeUtil.ON_CLICK_LISTENER)
                    .addMethod(MethodSpec.methodBuilder("onClick")
                            .addAnnotation(Override.class)
                            .addModifiers(Modifier.PUBLIC)
                            .returns(TypeName.VOID)
                            .addParameter(TypeUtil.VIEW, "view")
                            .addStatement("host.$N($L)", method.getMethodElement().getSimpleName(), "view")
                            .build())
                    .build();
            injectMethodBuild.addStatement("listener = $L", listener);
            for (int resId : method.getResIds()) {
                injectMethodBuild.addStatement("provider.findViewById(source, $L).setOnClickListener(listener)", resId);
            }
        }

        TypeSpec finderClass = TypeSpec.classBuilder(classElement.getSimpleName() + "$$Finder")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.FINDER, TypeName.get(classElement.asType())))
                .addMethod(injectMethodBuild.build())
                .build();

        String packageName = elementUtils.getPackageOf(classElement).getQualifiedName().toString();
        return JavaFile.builder(packageName, finderClass).build();
    }
}
