package com.namoolelo.domain.locations.support;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.namoolelo.domain.Identifiable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Polygon implements Identifiable<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7212974507343954941L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Transient
	private double PI = Math.PI;
    private ArrayList<GeoCoord> coords;

	public boolean contains(GeoCoord point)
		{      double latitude = point.getLatitude();
			   double longitude = point.getLongitude();
		       int i;
		       double angle=0;
		       double point1_lat;
		       double point1_long;
		       double point2_lat;
		       double point2_long;
		       int n = coords.size();

		       for (i=0;i<n;i++) {
		    	  GeoCoord coord = coords.get(i);
		    	  GeoCoord coord2 = coords.get((i+1)%n);
		    	  
		          point1_lat = coord.getLatitude() - latitude;
		          point1_long = coord.getLongitude() - longitude;
		          
		          point2_lat = coord2.getLatitude() - latitude; 
		          point2_long = coord2.getLatitude() - longitude;
		          
		          angle += Angle2D(point1_lat,point1_long,point2_lat,point2_long);
		       }

		       if (Math.abs(angle) < PI)
		          return false;
		       else
		          return true;
		}

	private double Angle2D(double y1, double x1, double y2, double x2)
	{
	   double dtheta,theta1,theta2;
	   double TWOPI = 2 * PI;
	   
	   theta1 = Math.atan2(y1,x1);
	   theta2 = Math.atan2(y2,x2);
	   dtheta = theta2 - theta1;
	   while (dtheta > PI)
	      dtheta -= TWOPI;
	   while (dtheta < -PI)
	      dtheta += TWOPI;

	   return(dtheta);
	}
	
	
}
