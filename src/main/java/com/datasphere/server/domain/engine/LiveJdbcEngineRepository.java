/*
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

package com.datasphere.server.domain.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;
import org.supercsv.prefs.CsvPreference;

import com.datasphere.engine.datasource.connections.jdbc.dialect.JdbcDialect;
import com.datasphere.server.common.GlobalObjectMapper;
import com.datasphere.server.datasource.data.QueryTimeExcetpion;
import com.datasphere.server.datasource.data.forward.CsvResultForward;
import com.datasphere.server.datasource.data.forward.JsonResultForward;
import com.datasphere.server.datasource.data.forward.ResultForward;
import com.datasphere.server.domain.dataconnection.DataConnection;
import com.datasphere.server.domain.dataconnection.DataConnectionHelper;
import com.datasphere.server.util.CustomCsvResultSetWriter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Preconditions;

/**
 * Created by aladin on 2019. 9. 8..
 */
@Component
public class LiveJdbcEngineRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(LiveJdbcEngineRepository.class);

  public Optional<JsonNode> query(DataConnection connection, String queryStr, ResultForward resultForward) {

    Preconditions.checkNotNull(queryStr, "Query string required.");

    DataSource dataSource = getDataSource(connection);
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    JsonNode resultNode = null;

    try {

      conn = dataSource.getConnection();

      stmt = conn.createStatement();
      stmt.setFetchSize(100000);

      rs = stmt.executeQuery(queryStr);

      // CSV 와 JSON 의 경우 ResultSet 에서 전달 받은 Timestamp 값 처리가 달라 추후 보정작업 필요
      if(resultForward == null) {
        resultNode = GlobalObjectMapper.getResultSetMapper().convertValue(rs, JsonNode.class);
      } else if(resultForward instanceof JsonResultForward) {
        File localPath = new File(EngineQueryProperties.getLocalResultDir() +
            File.separator + "MFD-" + UUID.randomUUID().toString() + ".json");

        ObjectMapper mapper = GlobalObjectMapper.getResultSetMapper();

        mapper.writeValue(localPath, rs);

        resultNode = createFileResultForward(localPath);

      } else if(resultForward instanceof CsvResultForward) {

        CsvResultForward csvResultForward = (CsvResultForward) resultForward;

        File localPath = new File(EngineQueryProperties.getLocalResultDir() +
            File.separator + "MFD-" + UUID.randomUUID().toString() + ".csv");

        CustomCsvResultSetWriter csvWriter = new CustomCsvResultSetWriter(new FileWriter(localPath), CsvPreference.STANDARD_PREFERENCE);
        if(csvResultForward.isHasHeader()) {
          csvWriter.write(rs);
        } else {
          csvWriter.writeNoHeader(rs);
        }
        csvWriter.flush();

        resultNode = createFileResultForward(localPath);

      } else {
        throw new QueryTimeExcetpion("Not Support forward type in case of live connection.");
      }

    } catch (DataAccessException e) {
      LOGGER.error("Fail to query for live connection : {}", e.getMessage());
      throw new QueryTimeExcetpion("Fail to query for live connection : " + e.getMessage());
    } catch (SQLException | IOException | RuntimeException e) {
      LOGGER.error("Fail to query for live connection : {}", e.getMessage());
      throw new QueryTimeExcetpion("Fail to query for live connection : " + e.getMessage());
    } finally {
      JdbcUtils.closeResultSet(rs);
      JdbcUtils.closeStatement(stmt);
      JdbcUtils.closeConnection(conn);
    }

    return Optional.ofNullable(resultNode);
  }

  private JsonNode createFileResultForward(File file) {
    ObjectMapper mapper = GlobalObjectMapper.getDefaultMapper();
    ObjectNode dataNode = mapper.createObjectNode();
    dataNode.put("numRows", 0);
    dataNode.set("data",
        mapper.createObjectNode().put("file://" + file.getAbsolutePath(), file.length()));

    return mapper.createArrayNode().add(dataNode);

  }

  private DataSource getDataSource(DataConnection connection) {

    JdbcDialect dialect = DataConnectionHelper.lookupDialect(connection);

    DriverManagerDataSource driverManagerDataSource =
        new DriverManagerDataSource(dialect.getConnectUrl(connection), connection.getUsername(), connection.getPassword());
    if (dialect.getDriverClass(connection) != null) {
      driverManagerDataSource.setDriverClassName(dialect.getDriverClass(connection));
    }
//    DataSource dataSource = DataSourceBuilder.create()
//            .url(connection.getConnectUrl())
//            .driverClassName(connection.getDriverClass())
//            .username(connection.getUsername())
//            .password(connection.getPassword())
//            .build();

    LOGGER.debug("Created datasource : {}", dialect.getConnectUrl(connection));

    return driverManagerDataSource;
  }
}
