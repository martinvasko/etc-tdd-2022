package at.etc.employees.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import at.etc.employees.controllers.EmployeeController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import at.etc.employees.model.Employee;
import at.etc.employees.services.EmployeeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class StandaloneControllerTests {

	@MockBean
	EmployeeService employeeService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testfindAll() throws Exception {
		Employee employee = new Employee("Steve", "Jobs");
		List<Employee> employees = Arrays.asList(employee);

		Mockito.when(employeeService.findAll()).thenReturn(employees);

		mockMvc.perform(get("/employee"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].firstName", Matchers.is("Steve")));
	}

}
