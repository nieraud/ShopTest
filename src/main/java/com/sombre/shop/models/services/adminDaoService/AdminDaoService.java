package com.sombre.shop.models.services.adminDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.dto.adminDto.input.AddAdminDto;
import com.sombre.shop.models.pojo.dto.adminDto.input.UpdateAdminDto;
import com.sombre.shop.models.pojo.dto.adminDto.output.GetAdminDto;
import com.sombre.shop.models.pojo.entity.Admins;
import com.sombre.shop.models.repositories.adminRepository.AdminRepository;
import com.sombre.shop.models.services.AbstractDaoService;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
public class AdminDaoService extends AbstractDaoService implements AdminRepository {
    public AdminDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public boolean addAdmin(AddAdminDto admin) {

        String sql = "INSERT INTO admins VALUES(DEFAULT, :userId, :degree, :roledescr, DEFAULT);";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("userId", admin.getUserId())
                    .addParameter("degree", admin.getDegree())
                    .addParameter("roledescr", admin.getRoledescription())
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public Admins getAdminByUserId(UUID userId) {

        String sql = "SELECT * FROM admins WHERE id_user = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            Admins admin = connection.createQuery(sql, false)
                    .addParameter("id", userId)
                    .executeAndFetchFirst(Admins.class);
            if (admin != null) return admin;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean updateAdmin(UpdateAdminDto admin) {

        String sql = "UPDATE admins SET (degree, roledescription) = (:degree, :roledescription)" +
                " WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("degree", admin.getDegree())
                    .addParameter("roledescription", admin.getRoledescription())
                    .addParameter("id", admin.getUniqueid())
                    .executeUpdate();
            System.out.println(admin.toString());
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean deleteAdmin(UUID adminId) {

        String sql = "DELETE FROM admins WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("id", adminId)
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public GetAdminDto getAdminById(UUID adminId) {

        String sql = "SELECT admins.uniqueid, admins.id_user, admins.degree, " +
                "admins.roledescription, admins.dateadded, users.firstname, users.lastname, users.birthday, " +
                "users.phonenumber, users.datereg, users.useremail " +
                "FROM admins " +
                "INNER JOIN users ON admins.id_user = users.uniqueid " +
                "WHERE admins.uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            GetAdminDto admin = connection.createQuery(sql, false)
                    .addParameter("id", adminId)
                    .executeAndFetchFirst(GetAdminDto.class);

            if (admin != null) return admin;
            else return null;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public List<GetAdminDto> getAllAdmins() {

        String sql = "SELECT admins.uniqueid, admins.id_user, admins.degree, " +
                "admins.roledescription, admins.dateadded, users.firstname, users.lastname, users.birthday, " +
                "users.phonenumber, users.datereg, users.useremail " +
                "FROM admins " +
                "INNER JOIN users ON admins.id_user = users.uniqueid;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            List<GetAdminDto> admin = connection.createQuery(sql, false)
                    .executeAndFetch(GetAdminDto.class);

            if (admin.isEmpty()) return null;
            else return admin;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }
}
