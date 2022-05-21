package ru.specialist.demo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.specialist.demo.controller.TriangleController;
import ru.specialist.demo.entity.TriangleInfo;
import ru.specialist.demo.exceptions.TriangleError;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

	@Autowired
	TriangleController triangleController;

	@Autowired
	private TestRestTemplate restTemplate;

   
	@Test
	public void checkPerimeter(){
		TriangleInfo tet = triangleController.calculateSquareAndPerimeter(44,44,66);
		assertThat(tet).isNotNull();
		assertThat(tet.getPerimeter()).isEqualTo(154);
	}

	
	@Test
	public void checkSquare(){
		TriangleInfo tet = triangleController.calculateSquareAndPerimeter(3,4,5);
		assertThat(tet).isNotNull();
		assertThat(tet.getSquare()).isEqualTo(6);
	}


    @Test(expected = TriangleError.class)
    public void longTriangleSide_firstApproach() throws TriangleError {
        triangleController.calculateSquareAndPerimeter(2, 2, 100);
    }

    @Test
    public void longTriangleSide_secondApproach() {
        String actual = restTemplate.getForObject("http://localhost:8080/triangle?a=3&b=6&c=100", String.class);
        String excepted = "{\"message\":\"Triangle with these sides does not exist\",\"code\":500}";
        assertEquals(excepted, actual);
    }

}
