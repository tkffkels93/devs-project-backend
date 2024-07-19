package com.example.devs._core.utils;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class DeltaController {

    public String convertDeltaToHtml(String deltaJson) {
        String script = "const Delta = require('quill-delta-to-html').QuillDeltaToHtmlConverter;" +
                "const delta = " + deltaJson + ";" +
                "const converter = new Delta(delta, {});" +
                "converter.convert();";

        try (Context context = Context.create("js")) {
            Value result = context.eval("js", script);
            return result.asString();
        }
    }
}