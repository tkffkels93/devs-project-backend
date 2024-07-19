package com.example.devs;

import com.example.devs._core.utils.DeltaController;
import com.example.devs.model.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;

@DataJpaTest
public class BoardTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void test() throws IOException {
        String deltaJson = "{\"ops\":[{\"insert\":\"Hello, world!\\n\"},{\"insert\":\"This is a bold text.\",\"attributes\":{\"bold\":true}},{\"insert\":\"\\n\"},{\"insert\":\"This is an italic text.\",\"attributes\":{\"italic\":true}},{\"insert\":\"\\n\"},{\"insert\":\"This is an underlined text.\",\"attributes\":{\"underline\":true}},{\"insert\":\"\\n\"}]}";
        deltaJson = "[{\"insert\":\"Hello, world!\\n\"},{\"insert\":\"This is a bold text.\",\"attributes\":{\"bold\":true}},{\"insert\":\"\\n\"},{\"insert\":\"This is an italic text.\",\"attributes\":{\"italic\":true}},{\"insert\":\"\\n\"},{\"insert\":\"This is an underlined text.\",\"attributes\":{\"underline\":true}},{\"insert\":\"\\n\"}]";
//        deltaJson = DeltaController.addOps(deltaJson);
//        System.out.println("deltaJson = " + deltaJson);
        String convertedHTML = DeltaController.convertDeltaToHtml(deltaJson);
        System.out.println("convertedHTML = " + convertedHTML);
    }
}
