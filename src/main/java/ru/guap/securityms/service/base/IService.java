package ru.guap.securityms.service.base;

import java.util.List;
import java.util.Map;

public interface IService<T> {

     List<Map<String, String>> createJson(Iterable<T> var1);

     Map<String, String> createJsonNode(T var1);
}
