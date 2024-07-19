package com.example.devs._core.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

public class DeltaController {
    public static String convertDeltaToHtml(String deltaJson) throws IOException {
        // 번들된 JavaScript 파일 읽기
        String script = new String(Files.readAllBytes(Paths.get("src/main/resources/static/js/deltaToHtml.bundle.js")));

        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            // JavaScript 파일 실행
            context.eval(Source.newBuilder("js", script, "deltaToHtml.bundle.js").build());

            // JavaScript 함수 호출
            Value convertModule = context.getBindings("js").getMember("convertModule");
            if (convertModule == null) {
                throw new IllegalStateException("The module 'convertModule' is not defined in the JavaScript context.");
            }
            Value convertFunction = convertModule.getMember("convert");
            if (convertFunction == null) {
                throw new IllegalStateException("The function 'convert' is not defined in the JavaScript context.");
            }
            Value result = convertFunction.execute(deltaJson);
            if (result.isNull()) {
                throw new IllegalStateException("The function 'convert' returned null.");
            }
            return result.asString();
        }
    }
    public static String addOps(String deltaJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode deltaNode = objectMapper.readTree(deltaJson);
            ArrayNode opsNode;

            // ops 노드가 있는지 확인
            if (deltaNode.has("ops")) {
                opsNode = (ArrayNode) deltaNode.get("ops");
            } else {
                opsNode = objectMapper.createArrayNode();
                ((ObjectNode) deltaNode).set("ops", opsNode);
            }

            // 다른 모든 필드를 ops 하위로 이동
            Iterator<Map.Entry<String, JsonNode>> fields = deltaNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if (!field.getKey().equals("ops")) {
                    ObjectNode newOps = objectMapper.createObjectNode();
                    newOps.set(field.getKey(), field.getValue());
                    opsNode.add(newOps);
                }
            }

            // 이동된 필드를 삭제
            ObjectNode cleanedNode = (ObjectNode) deltaNode;
            cleanedNode.retain("ops");

            return objectMapper.writeValueAsString(cleanedNode);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing delta JSON";
        }
    }

}