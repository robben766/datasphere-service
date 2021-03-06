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

package com.datasphere.server.domain.notebook;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by aladin on 2019. 10. 22..
 */
@RepositoryRestResource(path = "connectors", itemResourceRel = "connector", collectionResourceRel = "connectors",
    excerptProjection = NoteBookConnectorProjections.DefaultProjection.class)
public interface NotebookConnectorRepository extends JpaRepository<NotebookConnector, String>, QuerydslPredicateExecutor<NotebookConnector> {

  @RestResource(path = "type")
  Page<NotebookConnector> findByType(@Param("type") String Type, Pageable pageable);

  @RestResource(path = "name")
  Page<NotebookConnector> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

  @RestResource(path = "nametype")
  Page<NotebookConnector> findByNameContainingIgnoreCaseAndType(@Param("name") String name, @Param("type") String Type, Pageable pageable);

}
