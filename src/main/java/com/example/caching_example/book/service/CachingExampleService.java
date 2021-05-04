package com.example.caching_example.book.service;

import com.example.caching_example.book.dto.CachingDataDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * The type Caching example service.
 */
@Service
public class CachingExampleService implements ICachingExampleService {

	/**
	 * The constant TOTAL_SLOTS.
	 */
	private static final String TOTAL_SLOTS = "totalSlots";

	/**
	 * The constant AVAILABLE_SLOTS.
	 */
	private static final String AVAILABLE_SLOTS = "availableSlots";

	/**
	 * The constant USED_SLOTS.
	 */
	private static final String USED_SLOTS = "usedSlots";

	/**
	 * The constant EXPIRATION_TIME.
	 */
	private static final String EXPIRATION_TIME = "expirationTime";

	/**
	 * The Context.
	 */
	@Autowired
	private ApplicationContext context;

	/**
	 * Client redisson client.
	 *
	 * @return the redisson client
	 * @throws IOException the io exception
	 */
	private RedissonClient client() throws IOException {
		Config config = Config.fromYAML(context.getResource("classpath:redisson.yaml").getInputStream());
		return Redisson.create(config);
	}

	/**
	 * Store object in cache object.
	 *
	 * @param key            the key
	 * @param cachingDataDto the caching data dto
	 * @return the object
	 * @throws IOException the io exception
	 */
	@Override
	public Object storeObjectInCache(String key, CachingDataDto cachingDataDto) throws IOException {
		RMapCache<String, Object> exampleCache = client().getMapCache(key);
		Gson gson = new Gson();
		/* Calculate expiration time */
		long periodInSeconds = 60;
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime expirationTime = currentTime.plusSeconds(periodInSeconds);
		Map<String, Object> cachingMap = gson.fromJson(gson.toJson(cachingDataDto.getData()), Map.class);
		cachingMap.put(EXPIRATION_TIME, Timestamp.valueOf(expirationTime).getTime());
		exampleCache.put(cachingDataDto.getId().toString(), cachingMap, periodInSeconds, TimeUnit.SECONDS);
		return exampleCache.get(cachingDataDto.getId().toString());
	}

	/**
	 * Read object from cache object.
	 *
	 * @param key the key
	 * @param id  the id
	 * @return the object
	 * @throws IOException the io exception
	 */
	@Override
	public Object readObjectFromCache(String key, String id) throws IOException {
		RMapCache<String, Object> exampleCache = client().getMapCache(key);
		return exampleCache.get(id);
	}

	/**
	 * Update in cache object.
	 *
	 * @param key the key
	 * @param id  the id
	 * @return the object
	 * @throws IOException the io exception
	 */
	@Override
	public Object updateInCache(String key, String id) throws IOException {
		RMapCache<String, Object> exampleCache = client().getMapCache(key);
		Object obj = exampleCache.get(id);
		if (obj != null) {
			Gson gson = new Gson();
			JsonElement jsonElement = gson.toJsonTree(obj);
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			Map<String, Object> map = gson.fromJson(jsonObject, Map.class);
			map.put(TOTAL_SLOTS, ((Double) map.get(TOTAL_SLOTS)).longValue());
			map.put(USED_SLOTS, ((Double) map.get(USED_SLOTS)).longValue() + 1);
			map.put(AVAILABLE_SLOTS, (Long) map.get(TOTAL_SLOTS) - (Long) map.get(USED_SLOTS));

			/* Calculate expiration time */
			long currentTime = System.currentTimeMillis();
			long expirationTime = ((Double) map.get(EXPIRATION_TIME)).longValue();
			long periodInSeconds = TimeUnit.MILLISECONDS.toSeconds(expirationTime - currentTime);

			exampleCache.put(id, map, periodInSeconds, TimeUnit.MINUTES);
			return exampleCache.get(id);
		} else {
			return obj;
		}
	}
}
