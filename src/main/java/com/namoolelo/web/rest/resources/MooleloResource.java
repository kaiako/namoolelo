package com.namoolelo.web.rest.resources;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.domain.Place;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MooleloResource extends ResourceSupport{
	
	private Long rid;
	private String title;
	private String text;
	private String summary;
	private String estDate;
	private List<Place> places;

	public MooleloResource(Moolelo moolelo){
		this.rid = moolelo.getId();
		this.title = moolelo.getTitle();
		this.text = moolelo.getText();
		this.summary = moolelo.getSummary();
		this.estDate = moolelo.getEstDate();
		this.places = moolelo.getPlaces();
	}

    public Moolelo toMoolelo() {
        Moolelo moolelo = new Moolelo();
        moolelo.setTitle(title);
        moolelo.setText(text);
        moolelo.setSummary(summary);
        moolelo.setEstDate(estDate);
        moolelo.setPlaces(places);
        return moolelo;
    }

}
