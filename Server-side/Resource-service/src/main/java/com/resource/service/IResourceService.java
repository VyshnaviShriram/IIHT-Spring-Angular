package com.resource.service;

import java.util.*;

import com.resource.entity.Resource;

public interface IResourceService {

	public List<Resource> getResourceForUsers(Long userID);
}
