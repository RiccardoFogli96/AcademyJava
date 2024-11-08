import com.library.course.entity.Author;
import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.MagazineMapper;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.model.TipologyMagazine;
import com.library.course.repository.MagazineRepository;
import com.library.course.service.AuthorService;
import com.library.course.service.MagazineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith( MockitoExtension.class)
class MagazineServiceTest {

	@InjectMocks
	private MagazineService magazineService;

	@Mock
	private MagazineRepository magazineRepository;

	@Mock
	private MagazineMapper magazineMapper;

	@Mock
	private AuthorService authorService;

	@Mock
	private AuthorMapper authorMapper;

	private Author author1;
	private Author author2;
	private Author author3;
	private CreateMagazineDTO createMagazineDTO;
	List < Long > authorIdList = new ArrayList <>();
	List < Author > authorList = new ArrayList <>();

	@BeforeEach
	void setUp() {
		author1 = new Author(1L, "Teste1", "Teste1", null, null, null);
		author2 = new Author(2L, "Teste2", "Teste2", null, null, null);
		author3 = new Author(3l, "Teste3", "Teste3", null, null, null);

		authorList.add(author1);
		authorList.add(author2);
		authorList.add(author3);

		authorIdList.add(1L);
		authorIdList.add(2L);
		authorIdList.add(3L);
		authorIdList.add(4L);
		createMagazineDTO = CreateMagazineDTO.builder().title("Teste").authorIdList(authorIdList).publishedDate(LocalDate.now()).tipologyMagazine(TipologyMagazine.DAILY).build();
	}

	@Test
		//getListAuthor test
	void getListAuthor_ShouldThrowException_WhenAuthorNotExists() throws Exception {

		when(authorService.findAllById(createMagazineDTO.getAuthorIdList())).thenReturn(authorList);


		Exception exception = assertThrows(Exception.class, () -> magazineService.getListAuthor(createMagazineDTO));
		assertEquals("Some authors could not be found !", exception.getMessage());
	}
}