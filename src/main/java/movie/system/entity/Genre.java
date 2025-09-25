package movie.system.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Genre {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int generId;

	@Column(name = "genre_name", unique = true) 
    private String genre_name;

	public int getGenerId() {
		return generId;
	}

	public void setGenerId(int generId) {
		generId = generId;
	}

	public String getGenre_name() {
		return genre_name;
	}

	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}

    
	
}
