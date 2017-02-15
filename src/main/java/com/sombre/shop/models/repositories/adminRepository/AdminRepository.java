package com.sombre.shop.models.repositories.adminRepository;

import com.sombre.shop.models.pojo.dto.admin.input.AddAdmin;
import com.sombre.shop.models.pojo.dto.admin.input.UpdateAdmin;
import com.sombre.shop.models.pojo.dto.admin.output.GetAdmin;
import com.sombre.shop.models.pojo.entity.Admins;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
public interface AdminRepository {
    boolean addAdmin(AddAdmin newAdmin);
    Admins getAdminByUserId(UUID userId);
    boolean updateAdmin(UpdateAdmin admin);
    boolean deleteAdmin(UUID adminId);
    GetAdmin getAdminById(UUID adminId);
    List<GetAdmin> getAllAdmins();
}
