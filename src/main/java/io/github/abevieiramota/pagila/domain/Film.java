package io.github.abevieiramota.pagila.domain;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Film {

	@Id
	@GeneratedValue(generator = "Film_SEQ")
	@SequenceGenerator(name = "Film_SEQ", sequenceName = "film_film_id_seq", allocationSize = 1)
	@Column(name = "film_id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setTitle(String title) {

		if (title == null) {

			throw new IllegalArgumentException("cara, por favor...");
		}

		this.title = title;
	}

	public String getTitle() {

		return title;
	}

	public String getDescription() {

		return description;
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		if (id == null) {

			throw new IllegalArgumentException("maxo, passa um id não null pow");
		}

		this.id = id;

	}

	@Override
	public String toString() {

		StringJoiner sj = new StringJoiner(" ");
		sj.add("" + id).add(title).add(description);

		return sj.toString();

	}

}
