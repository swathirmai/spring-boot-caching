package com.springboot.caching.util;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CachingFieldsUtil {

	public List<CachingFields> cachingFields;
	
	@Cacheable("cachingFields")
	public List<CachingFields> getCachingFields() {
		return cachingFields;
	}

	public void setCachingFields(List<CachingFields> cachingFields) {
		this.cachingFields = cachingFields;
	}

}
