package digital.b2w.planets;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import digital.b2w.planets.dto.request.PlanetaDTO;
import digital.b2w.planets.model.Planeta;
import digital.b2w.planets.service.PlanetaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlanetaControllerTest {
	
	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private PlanetaService mockService;
    
	@Before
    public void init() {
        Planeta planeta = new Planeta("1234AB", "Hoth", "clima", "terreno", 2);
        when(mockService.buscarPorId("1234AB")).thenReturn(planeta);
    }

    @Test
    public void buscar_planetaId_OK() throws Exception {

        mockMvc.perform(get("/planetas/1234AB"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1234AB")))
                .andExpect(jsonPath("$.nome", is("Hoth")))
                .andExpect(jsonPath("$.clima", is("clima")))
                .andExpect(jsonPath("$.terreno", is("terreno")))
        		.andExpect(jsonPath("$.quantidadeAparicoesEmFilmes", is(2)));

        verify(mockService, times(1)).buscarPorId("1234AB");
    }
    
    @Test
    public void listar_OK() throws Exception {

        List<Planeta> planetas = Arrays.asList(
                new Planeta("1234AB", "Planeta 1", "Clima 1", "Terreno 1", 1),
                new Planeta("5678CD", "Planeta 2", "Clima 2", "Terreno 2", 4));

        when(mockService.listar()).thenReturn(planetas);

        mockMvc.perform(get("/planetas"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultSize", is(2)))
                .andExpect(jsonPath("$.list[0].id", is("1234AB")))
                .andExpect(jsonPath("$.list[0].nome", is("Planeta 1")))
                .andExpect(jsonPath("$.list[0].clima", is("Clima 1")))
                .andExpect(jsonPath("$.list[0].terreno", is("Terreno 1")))
                .andExpect(jsonPath("$.list[0].quantidadeAparicoesEmFilmes", is(1)))
                .andExpect(jsonPath("$.list[1].id", is("5678CD")))
                .andExpect(jsonPath("$.list[1].nome", is("Planeta 2")))
                .andExpect(jsonPath("$.list[1].clima", is("Clima 2")))
                .andExpect(jsonPath("$.list[1].terreno", is("Terreno 2")))
                .andExpect(jsonPath("$.list[1].quantidadeAparicoesEmFilmes", is(4)));
                

        verify(mockService, times(1)).listar();
    }
    
    @Test
    public void adicionarPlaneta_OK() throws Exception {

        Planeta planeta = new Planeta("8901EF", "Novo Planeta", "Novo Clima", "Novo Terreno", 3);
        when(mockService.adicionar(any(PlanetaDTO.class))).thenReturn(planeta);

        mockMvc.perform(post("/planetas")
                .content(om.writeValueAsString(planeta))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("8901EF")))
                .andExpect(jsonPath("$.nome", is("Novo Planeta")))
                .andExpect(jsonPath("$.clima", is("Novo Clima")))
                .andExpect(jsonPath("$.terreno", is("Novo Terreno")))
        		.andExpect(jsonPath("$.quantidadeAparicoesEmFilmes", is(3)));

        verify(mockService, times(1)).adicionar(any(PlanetaDTO.class));
    }
    
    @Test
    public void removerPlaneta_OK() throws Exception {
        doNothing().when(mockService).remover("1234GH");

        mockMvc.perform(delete("/planetas/1234GH"))
                .andExpect(status().isOk());

        verify(mockService, times(1)).remover("1234GH");
    }
}