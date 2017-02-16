package com.sombre.shop.models.repositories.adminRepository;

import com.sombre.shop.models.pojo.dto.adminDto.input.AddAdminDto;
import com.sombre.shop.models.pojo.dto.adminDto.input.UpdateAdminDto;
import com.sombre.shop.models.pojo.dto.adminDto.output.GetAdminDto;
import com.sombre.shop.models.pojo.entity.Admins;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
public interface AdminRepository {
    boolean addAdmin(AddAdminDto newAdmin);
    Admins getAdminByUserId(UUID userId);
    boolean updateAdmin(UpdateAdminDto admin);
    boolean deleteAdmin(UUID adminId);
    GetAdminDto getAdminById(UUID adminId);
    List<GetAdminDto> getAllAdmins();
}
