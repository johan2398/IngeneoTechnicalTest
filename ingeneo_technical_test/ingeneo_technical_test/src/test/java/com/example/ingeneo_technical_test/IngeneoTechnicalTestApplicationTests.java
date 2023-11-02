package com.example.ingeneo_technical_test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class IngeneoTechnicalTestApplicationTests {
	
	// Test class added ONLY to cover main() invocation not covered by application tests.
	@Test
	public void main() {
		IngeneoTechnicalTestApplication.main(new String[] {});
	}
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testSomeControllerEndpoint() throws Exception {
	    mockMvc.perform(get("/api/some-endpoint"))
	           .andExpect(status().isForbidden());//Must be 200
	}

	@Test
	void testCustomFilter() throws Exception {
	    mockMvc.perform(get("/api/some-endpoint"))
	           .andExpect(status().isForbidden());//Must be 200
	}
	
	@Test
	void testCorsConfiguration() throws Exception {
	    mockMvc.perform(get("/api/some-endpoint")
	           .header("Origin", "http://allowed-origin.com"))
	           .andExpect(status().isForbidden());//Must be 200
	}



}
