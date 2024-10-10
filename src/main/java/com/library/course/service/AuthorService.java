package com.library.course.service;

import com.library.course.entity.Author;
import com.library.course.mapper.AuthorMapper;
import com.library.course.model.AuthorDTO;
import com.library.course.model.CreateAuthorDTO;
import com.library.course.repository.AuthorRepository;
import com.library.course.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookRepository bookRepository;


    public List<AuthorDTO> getAllAuthors(){
        return authorMapper.fromAuthorListToDTOList(authorRepository.findAll());
    }

    public AuthorDTO getAuthorById(Long id)throws Exception{
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Author with Id" + id + " not found"));
        return authorMapper.fromAuthorToDTO(author);
    }

    public AuthorDTO addNewAuthor(CreateAuthorDTO authorDTO) throws Exception{
        if(authorDTO.getName() == null || authorDTO.getSurname() == null){
            throw new Exception("Either name or surname is null");
        }
         Author author = authorMapper.fromDTOtoAuthor(authorDTO);
         authorRepository.save(author);
         return authorMapper.fromAuthorToDTO(author);
    }
    public boolean deleteAuthor(long id){
        bookRepository.deleteByAuthorId(id);
        authorRepository.deleteById(id);
        return true;
    }

    public AuthorDTO updateAuthor(CreateAuthorDTO createAuthorDTO, Long id) throws Exception {
        if(!authorRepository.existsById(id)){
           throw new Exception("This Author doesn't exist");
        }
        Author updateAuthor = authorMapper.fromDTOtoAuthor(createAuthorDTO);
        updateAuthor.setId(id);
        authorRepository.save(updateAuthor);
        return authorMapper.fromAuthorToDTO(updateAuthor);
    }
}
