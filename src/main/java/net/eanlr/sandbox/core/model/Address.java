package net.eanlr.sandbox.core.model;

import lombok.Data;

@Data
public class Address {
	String street;
	String building;
	String apartment;
	String postal;
	String city;
	String district;
}
