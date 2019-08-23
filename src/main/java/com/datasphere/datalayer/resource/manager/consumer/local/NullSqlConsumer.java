/*
 * Copyright 2019, Huahuidata, Inc.
 * DataSphere is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 */

package com.datasphere.datalayer.resource.manager.consumer.local;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datasphere.datalayer.resource.manager.SystemKeys;
import com.datasphere.datalayer.resource.manager.common.ConsumerException;
import com.datasphere.datalayer.resource.manager.model.Consumer;
import com.datasphere.datalayer.resource.manager.model.Registration;
import com.datasphere.datalayer.resource.manager.model.Resource;

public class NullSqlConsumer extends Consumer {

    private final static Logger _log = LoggerFactory.getLogger(NullSqlConsumer.class);

    public static final String TYPE = SystemKeys.TYPE_SQL;
    public static final String ID = "nullSql";

    private int STATUS;

    private Registration registration;

    public NullSqlConsumer() {
    }

    public NullSqlConsumer(Map<String, Serializable> properties) {
        _properties = properties;
    }

    // test properties
    private Map<String, Serializable> _properties;

    public Map<String, Serializable> getProperties() {
        return _properties;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public Registration getRegistration() {
        return registration;
    }

    /*
     * Init method - POST constructor since spring injects properties *after
     * creation*
     */
    @PostConstruct
    public void init() {
        _log.debug("init called");

        if (_properties == null) {
            _properties = new HashMap<>();
        }

        STATUS = SystemKeys.STATUS_READY;
    }

    @Override
    public int getStatus() {
        return STATUS;
    }

    @Override
    public void addResource(String scopeId, String userId, Resource resource) throws ConsumerException {
        _log.debug("add resource " + resource.toString());

    }

    @Override
    public void updateResource(String scopeId, String userId, Resource resource) throws ConsumerException {
        _log.debug("update resource " + resource.toString());

    }

    @Override
    public void deleteResource(String scopeId, String userId, Resource resource) throws ConsumerException {
        _log.debug("delete resource " + resource.toString());

    }

    @Override
    public void checkResource(String scopeId, String userId, Resource resource) throws ConsumerException {
        _log.debug("check resource " + resource.toString());

    }
}
