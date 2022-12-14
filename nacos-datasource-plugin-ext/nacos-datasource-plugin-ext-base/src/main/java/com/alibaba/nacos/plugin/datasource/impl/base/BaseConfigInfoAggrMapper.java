/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.plugin.datasource.impl.base;

import com.alibaba.nacos.plugin.datasource.constants.TableConstant;
import com.alibaba.nacos.plugin.datasource.dialect.DatabaseDialect;
import com.alibaba.nacos.plugin.datasource.impl.mysql.ConfigInfoAggrMapperByMySql;
import com.alibaba.nacos.plugin.datasource.manager.DatabaseDialectManager;

/**
 * The base implementation of ConfigTagsRelationMapper.
 *
 * @author Long Yu
 **/
public class BaseConfigInfoAggrMapper extends ConfigInfoAggrMapperByMySql {
    
    private DatabaseDialect databaseDialect;
    
    public BaseConfigInfoAggrMapper() {
        databaseDialect = DatabaseDialectManager.getInstance().getDialect(getDataSource());
    }
    
    @Override
    public String getTableName() {
        return TableConstant.CONFIG_INFO_AGGR;
    }
    
    @Override
    public String findConfigInfoAggrByPageFetchRows(int startRow, int pageSize) {
        return databaseDialect.getLimitPageSqlWithOffset(
                "SELECT data_id,group_id,tenant_id,datum_id,app_name,content FROM config_info_aggr WHERE data_id= ? AND "
                        + "group_id= ? AND tenant_id= ? ORDER BY datum_id ", startRow, pageSize);
    }

}
