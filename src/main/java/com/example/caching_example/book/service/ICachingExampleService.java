package com.example.caching_example.book.service;

import com.example.caching_example.book.dto.CachingDataDto;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * The interface Caching example service.
 */
@Service("CachingExampleService")
public interface ICachingExampleService {

	/**
	 * Store object in cache object.
	 *
	 * @param key            the key
	 * @param cachingDataDto the caching data dto
	 * @return the object
	 * @throws IOException the io exception
	 */
	Object storeObjectInCache(@NotBlank String key, @NotNull CachingDataDto cachingDataDto) throws IOException;

	/**
	 * Read object from cache object.
	 *
	 * @param key the key
	 * @param id  the id
	 * @return the object
	 * @throws IOException the io exception
	 */
	Object readObjectFromCache(@NotBlank String key, @NotBlank String id) throws IOException;

	/**
	 * Update in cache object.
	 *
	 * @param key the key
	 * @param id  the id
	 * @return the object
	 * @throws IOException the io exception
	 */
	Object updateInCache(@NotBlank String key, @NotBlank String id) throws IOException;
}
