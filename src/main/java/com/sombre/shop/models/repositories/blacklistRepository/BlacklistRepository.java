package com.sombre.shop.models.repositories.blacklistRepository;

import com.sombre.shop.models.pojo.dto.blacklistDto.input.AddBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.input.UpdateBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.output.GetBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.output.IntermediateBlacklistDto;
import com.sombre.shop.models.pojo.entity.Blacklist;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 20.02.17.
 */
public interface BlacklistRepository {

    GetBlacklistDto getBlacklistById(UUID listId);

    GetBlacklistDto getBlacklistByUserId(UUID userId);

    boolean addUserToBlacklist(AddBlacklistDto list, UUID adminId);

    boolean updateBlackList(UpdateBlacklistDto list);

    boolean deleteBlacklist(UUID blacklistId);

    List<IntermediateBlacklistDto> allBlacklist();

}
