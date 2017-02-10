package com.sombre.shop.models.factory;

import org.sql2o.Sql2o;

/**
 * Created by inna on 10.02.17.
 */
public interface DataSource {
    Sql2o getDataSource();
}
