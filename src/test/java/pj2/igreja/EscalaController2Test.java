package pj2.igreja;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class EscalaController2Test {

    @Test
    void exibirEscalaDeveCarregarCsvMesmoForaDoDiretorioDoProjeto() throws Exception {
        Path original = Path.of("").toAbsolutePath();
        Path tempDir = Files.createTempDirectory("igreja-test");
        System.setProperty("user.dir", tempDir.toString());

        try {
            MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new EscalaController2()).build();

            mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("escala"))
                .andExpect(model().attribute("escala", hasSize(greaterThan(0))));
        } finally {
            System.setProperty("user.dir", original.toString());
        }
    }
}
