package cn.luxw.app.mapper;

import cn.luxw.app.domain.db.OfferLandingPage;

public interface OfferLandingPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OfferLandingPage record);

    int insertSelective(OfferLandingPage record);

    OfferLandingPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OfferLandingPage record);

    int updateByPrimaryKey(OfferLandingPage record);
}