package com.voidwhile.market.service;

import java.util.List;
import java.util.Map;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.PubImage;

public interface PubImageService extends IBaseService<PubImage> {
	
	public List<PubImage> findByMap(Map<String, Object> param);
	public void insertimage(PubImage image);
	public void deleteByBizId(PubImage record);
}
