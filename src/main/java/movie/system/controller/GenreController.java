package movie.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movie.system.entity.Genre;
import movie.system.service.GenreService;

@RestController
@RequestMapping("/api/gener")
public class GenreController 
{
	@Autowired
	private GenreService generesService;
	
	@PostMapping("/post")
	public ResponseEntity<Genre> createGeneres(@RequestBody Genre generes)
	{
		Genre genre = generesService.createGenre(generes);
		return new ResponseEntity<Genre>(genre, HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Genre>> getallGeneres()
	{
		List<Genre> generes = generesService.getAllGenres();
		return new ResponseEntity<List<Genre>>(generes, HttpStatus.OK);
	}
	
	@GetMapping("/get/{generesid}")
	public ResponseEntity<Genre> getbyGeneresId(@PathVariable Integer generesid)
	{
		Genre genre= generesService.getByIdGenre(generesid);
		return new ResponseEntity<Genre>(genre, HttpStatus.OK);
	}
	
	
	
	@PutMapping("/update/{generesId}")
	public ResponseEntity<Genre> updateGeneres(@RequestBody Genre generes, @PathVariable Integer generesid)
	{
		Genre genre = generesService.updateGenre(generesid, generes);
		return new ResponseEntity<Genre>(genre, HttpStatus.OK);
	}
	
	@DeleteMapping("/{generesId}")
	public ResponseEntity<String> deletebygeneredId(@PathVariable Integer generesId)
	{
		 generesService.deleteGenre(generesId);		
		return new ResponseEntity<String>("deleted Successfully", HttpStatus.OK);
	}
}
