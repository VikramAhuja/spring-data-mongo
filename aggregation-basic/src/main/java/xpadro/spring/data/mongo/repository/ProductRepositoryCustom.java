package xpadro.spring.data.mongo.repository;


import xpadro.spring.data.mongo.dto.WarehouseSummary;

import java.util.List;

public interface ProductRepositoryCustom {

    List<WarehouseSummary> aggregate(float minPrice, float maxPrice);
}
