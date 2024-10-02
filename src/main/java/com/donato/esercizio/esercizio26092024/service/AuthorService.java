package com.donato.esercizio.esercizio26092024.service;

import com.donato.esercizio.esercizio26092024.entity.Author;
import com.donato.esercizio.esercizio26092024.mapper.AuthorMapper;
import com.donato.esercizio.esercizio26092024.model.AuthorDTO;
import com.donato.esercizio.esercizio26092024.model.BookDTO;
import com.donato.esercizio.esercizio26092024.model.CreateAuthorDTO;
import com.donato.esercizio.esercizio26092024.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthorService {

    Map<Long, Author> authorMap = new HashMap<>();
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookService bookService;

    public List<AuthorDTO> getAllAuthors(){
        return authorMapper.fromAuthorListToDTOList(authorRepository.findAll());
    }

    public AuthorDTO getAuthorById(Long id)throws Exception{
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Ciaoo"));
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
        bookService.deleteBookByIdAuthor(id);
        authorRepository.deleteById(id);
        return true;
    }
}
