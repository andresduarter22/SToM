package upb.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "libro")
public class Libro implements Serializable {

	@Id
	@Column(name = "libro_id", unique = true)
	private int id;

	@Column(name = "libro_name", nullable = false)
	private String name;

	@Column(name = "libro_autor", nullable = false)
	private String autor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + autor;
	}
}