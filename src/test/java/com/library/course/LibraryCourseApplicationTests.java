package com.library.course;

import com.library.course.controller.AuthorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryCourseApplicationTests {

	@Autowired
	private AuthorController authorController;

	@Test
	void contextLoads() {
	}

}
