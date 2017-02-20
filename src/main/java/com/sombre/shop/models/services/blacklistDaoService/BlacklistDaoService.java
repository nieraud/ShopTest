package com.sombre.shop.models.services.blacklistDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.dto.blacklistDto.input.AddBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.input.UpdateBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.output.AdminForBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.output.GetBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.output.IntermediateBlacklistDto;
import com.sombre.shop.models.repositories.blacklistRepository.BlacklistRepository;
import com.sombre.shop.models.services.AbstractDaoService;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 20.02.17.
 */
public class BlacklistDaoService extends AbstractDaoService implements BlacklistRepository {
    public BlacklistDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public boolean addUserToBlacklist(AddBlacklistDto list, UUID adminId) {

        String sql = "INSERT INTO blacklist VALUES(DEFAULT, :userId, :adminId, " +
                ":notes, DEFAULT);";

        try (Connection connection = daoFactory.getDataSource().open()) {
            connection.createQuery(sql, false)
                    .addParameter("userId", list.getUserid())
                    .addParameter("adminId", adminId)
                    .addParameter("notes", list.getNotes())
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean updateBlackList(UpdateBlacklistDto list) {
        String sql = "UPDATE blacklist SET (id_user, notes) = (:userId, :notes) " +
                "WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {
            connection.createQuery(sql, false)
                    .addParameter("userId", list.getUserid())
                    .addParameter("notes", list.getNotes())
                    .addParameter("id", list.getUniqueid())
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean deleteBlacklist(UUID blacklistId) {
        String sql = "DELETE FROM blacklist WHERE id_user = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {
            connection.createQuery(sql, false)
                    .addParameter("id", blacklistId)
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public List<IntermediateBlacklistDto> allBlacklist() {

        String sql = "SELECT blacklist.uniqueid, " +
                "blacklist.notes, " +
                "blacklist.dateadded, " +
                "blacklist.id_user, " +
                "users.firstname, " +
                "users.lastname, " +
                "blacklist.id_adminadded " +
                "FROM blacklist " +
                "INNER JOIN users ON blacklist.id_user = users.uniqueid " +
                "INNER JOIN admins ON blacklist.id_adminadded = admins.uniqueid;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            return connection.createQuery(sql, false)
                    .executeAndFetch(IntermediateBlacklistDto.class);

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public GetBlacklistDto getBlacklistById(UUID listId) {

        String sql = "SELECT blacklist.uniqueid, " +
                "blacklist.notes, " +
                "blacklist.dateadded, " +
                "blacklist.id_user, " +
                "users.firstname, " +
                "users.lastname, " +
                "blacklist.id_adminadded " +
                "FROM blacklist " +
                "INNER JOIN users ON blacklist.id_user = users.uniqueid " +
                "INNER JOIN admins ON blacklist.id_adminadded = admins.uniqueid " +
                "WHERE blacklist.uniqueid = :id;";

        String adminSql = "SELECT firstname, lastname FROM users " +
                "WHERE admins.id_user = users.uniqueid " +
                "AND admins.uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().beginTransaction()) {

            IntermediateBlacklistDto intBlacklist = connection.createQuery(sql, false)
                    .addParameter("id", listId)
                    .executeAndFetchFirst(IntermediateBlacklistDto.class);

            System.out.println(intBlacklist.toString());

            AdminForBlacklistDto admin = connection.createQuery(adminSql, false)
                    .addParameter("id", intBlacklist.getId_adminadded())
                    .executeAndFetchFirst(AdminForBlacklistDto.class);

            connection.commit(true);

            return new GetBlacklistDto(intBlacklist.getUniqueid(),
                    intBlacklist.getNotes(),
                    intBlacklist.getDateadded(),
                    intBlacklist.getId_user(),
                    intBlacklist.getFirstname(),
                    intBlacklist.getLastname(),
                    intBlacklist.getId_adminadded(),
                    admin.getAdminsFirstname(),
                    admin.getAdminsLastname());

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public GetBlacklistDto getBlacklistByUserId(UUID userId) {

        String sql = "SELECT blacklist.uniqueid, " +
                "blacklist.notes, " +
                "blacklist.dateadded, " +
                "blacklist.id_user, " +
                "users.firstname, " +
                "users.lastname, " +
                "blacklist.id_adminadded " +
                "FROM blacklist " +
                "INNER JOIN users ON blacklist.id_user = users.uniqueid " +
                "INNER JOIN admins ON blacklist.id_adminadded = admins.uniqueid " +
                "WHERE blacklist.id_user = :id;";

        String adminSql = "SELECT firstname, lastname FROM users " +
                "WHERE users.uniqueid = admins.id_user " +
                "AND admins.uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            IntermediateBlacklistDto intBlacklist = connection.createQuery(sql, false)
                    .addParameter("id", userId)
                    .executeAndFetchFirst(IntermediateBlacklistDto.class);

            System.out.println(intBlacklist.toString());

            /*AdminForBlacklistDto admin = connection.createQuery(adminSql, false)
                    .addParameter("id", intBlacklist.getId_adminadded())
                    .executeAndFetchFirst(AdminForBlacklistDto.class);

            connection.commit(true);

            return new GetBlacklistDto(intBlacklist.getUniqueid(),
                    intBlacklist.getNotes(),
                    intBlacklist.getDateadded(),
                    intBlacklist.getId_user(),
                    intBlacklist.getFirstname(),
                    intBlacklist.getLastname(),
                    intBlacklist.getId_adminadded(),
                    admin.getAdminsFirstname(),
                    admin.getAdminsLastname());
*/ return null;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }


}
