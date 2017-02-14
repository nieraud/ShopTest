package com.sombre.shop.models.repositories.adminRepository;

import com.sombre.shop.models.pojo.dto.admin.NewAdmin;

import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
public interface AdminRepository {
    boolean addAdmin(UUID userID, NewAdmin newAdmin);
}
