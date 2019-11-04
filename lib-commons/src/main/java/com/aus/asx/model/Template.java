package com.aus.asx.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Template {
    private List<Nav> navs;

    @Data
    @AllArgsConstructor
    public static class Nav {
        private String name;
        private String code;
        private String href;
    }
}