package io.github.abevieiramota.pagila.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Language {
	
	@Id
	@Column(name = "language_id")
	private Integer id;
	
	public void setId(Integer id) {
		
		if(id == null) {
			
			throw new IllegalArgumentException("...");
		}
		
		this.id = id;
		
	}
}
