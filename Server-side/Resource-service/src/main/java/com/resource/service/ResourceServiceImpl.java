package com.resource.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.resource.entity.Resource;

@Service
public class ResourceServiceImpl implements IResourceService {
	
	List<Resource> list = List.of(
			new Resource(1L, "peter@gmail.com", "peter", "React Dev", 1000L),
			new Resource(2L, "Nishant@gmail.com", "Nishant", "Java Dev", 1002L),
			new Resource(3L, "John@gmail.com", "John", "Python Dev", 1002L),
			new Resource(4L, "Rekha@gmail.com", "Rekha", "Node JS Dev", 1003L),
			new Resource(5L, "Lata@gmail.com", "Lata", "Angular Dev", 1004L),
			new Resource(6L, "Neha@gmail.com", "Neha", "React Dev", 1004L),
			new Resource(7L, "Suma@gmail.com", "Suma", "Python Dev", 1005L),
			new Resource(8L, "Durga@gmail.com", "Durga", "Node Dev", 1005L),
			new Resource(9L, "Mani@gmail.com", "Mani", "Java, Devops Dev", 1006L),
			new Resource(10L, "Shiva@gmail.com", "Shiva", "AWS, Jenkins", 1001L));

	@Override
	public List<Resource> getResourceForUsers(Long userID) {
		return list.stream().filter(resource->resource.getUserID().equals(userID)).collect(Collectors.toList());
	}

}
