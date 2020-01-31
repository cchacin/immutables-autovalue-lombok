package com.groupon.api.talks;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface NewModel {
    String a();
    List<String> b();
}
