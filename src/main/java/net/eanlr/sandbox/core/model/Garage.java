package net.eanlr.sandbox.core.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Garage {
	private String id;
	private String name;
	private boolean authorized;
	private List<String> makeList;
	private Address address;
}
