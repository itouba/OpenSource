package com.itouba.obteamwork.mapper;

import com.itouba.obteamwork.entitry.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeamMapper {

    @Cacheable(value = "team", key = "#id")
    @Select("select * from team where id = #{id}")
    Team get(long id);
}
