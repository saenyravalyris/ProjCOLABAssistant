package br.univille.projcolabassistant;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.univille.projcolabassistant.controller.AccessoryColorController;
import br.univille.projcolabassistant.controller.CategoryController;
import br.univille.projcolabassistant.controller.CityController;
import br.univille.projcolabassistant.controller.InstitutionController;
import br.univille.projcolabassistant.controller.UserController;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.repository.AccessoryColorRepository;
import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.repository.InstitutionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjColabAssistantApplicationTests {
	
	@Autowired
	private CategoryController categoryController;
	
	@Autowired
	private AccessoryColorController accessorycontroller;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private InstitutionController institutionController;
	
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private CityRepository cityRepository; 

	@Autowired
	private UserController controller;
	
	@Autowired
	private CityController cityController;
  @Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AccessoryColorRepository accessorycolorRepository;
	
	@Autowired
	private AccessoryColorRepository AcessoryColorController;
	
	
	
	
	@Test
	public void contextLoads() {
		//Verifica a existência da instância do controlador

		assertThat(institutionController).isNotNull();
		assertThat(controller).isNotNull();
		assertThat(AcessoryColorController).isNotNull();
		assertThat(cityController).isNotNull();
	}
	
	@Test
	public void categoryController() throws Exception {
	
		categoryRepository.deleteAll();
		categoryRepository.flush();
		
		//when(categoryRepository.findAll()).thenReturn(asList(category));
		
		
		this.mockMvc.perform(post("/category")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=0&name=roberta"))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/category"));
		
	    this.mockMvc.perform(get("/category")).andDo(print()).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("roberta"));	      

	}
	
	@Test
	public void AccessoryColorController() throws Exception {
	
		accessorycolorRepository.deleteAll();
		accessorycolorRepository.flush();
		
		this.mockMvc.perform(post("/accessorycolor")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=0&name=verdinho"))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/accessorycolor"));
		
	    this.mockMvc.perform(get("/accessorycolor")).andDo(print()).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("verdinho"));	      
	}
	
	
	@Test
	public void pacienteControllerTest() throws Exception {
		//Teste do método index
		this.mockMvc.perform(get("/consultecategory")).andDo(print()).andExpect(status().isOk())
		.andExpect(xpath("//table").exists())
		.andExpect(xpath("//td[contains(., 'Zezinho')]").exists());
		}


	@Test
	public void institutionControllerTest() throws Exception {
		//Teste do método index
		institutionRepository.deleteAll();
		cityRepository.deleteAll();
		
		this.mockMvc.perform(get("/institution")).andExpect(status().isOk())
		.andExpect(xpath("/html/body/div/div/table").exists());
	}

	@Test
	public void institutionControllerSaveTest() throws Exception {
		

		institutionRepository.deleteAll();
		cityRepository.deleteAll();
		
		City c = new City();
		c.setName("Joinville");
		c.setState("SC");

		c = cityRepository.save(c);
		cityRepository.flush();
		
		this.mockMvc.perform(post("/institution")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=0&address=rua&description=descricao&email=teste@teste&name=univille&phone=123456&city=" + c.getId()))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/institution"));
		
	    this.mockMvc.perform(get("/institution")).andDo(print()).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("univille"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("descricao"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[3]/text()").string("rua"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[4]/text()").string("123456"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[5]/text()").string("teste@teste"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[6]/text()").string("Joinville"));

	    institutionRepository.deleteAll();
		cityRepository.deleteAll();
	}
	
	@Test
	public void institutionControllerUpdateTest() throws Exception {

		institutionRepository.deleteAll();
		cityRepository.deleteAll();
		
		City c = new City();	
		c.setName("Joinville");
		c.setState("SC");
		
		c = cityRepository.save(c);
		cityRepository.flush();
		
		Institution inst = new Institution();
		inst.setName("univille");
		inst.setAddress("rua");
		inst.setCity(c);
		inst.setDescription("descricao");
		inst.setEmail("teste@teste");
		inst.setPhone("123456");
		
		inst = institutionRepository.save(inst);
		
		
		this.mockMvc.perform(get("/institution/alterar/"+inst.getId())).andDo(print()).andExpect(status().isOk()).andDo(print())
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("univille"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("descricao"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[3]/text()").string("rua"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[4]/text()").string("123456"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[5]/text()").string("teste@teste"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[6]/text()").string("Joinville"));
		
		institutionRepository.deleteAll();
		cityRepository.deleteAll();
			    
  }
	@Test
	public void consultAccessories() throws Exception {
		this.mockMvc.perform(get("/catalogo")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void cityController() throws Exception {
	
		cityRepository.deleteAll();
		cityRepository.flush();
		
		this.mockMvc.perform(post("/city")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("name=Joinville&state=Santa Catarina"))
		
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/city"));
		


		
	    this.mockMvc.perform(get("/city")).andDo(print()).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("Joinville"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("Santa Catarina"));
	    
	    
        

	}

}
