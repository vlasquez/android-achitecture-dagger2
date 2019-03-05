package com.vlasquez.poweradapter.item;

import dagger.MapKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@MapKey
@Target(ElementType.METHOD)
public @interface RenderKey {

  String value();
}
