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

package com.datasphere.engine.shaker.processor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datasphere.engine.core.common.BaseService;
import com.datasphere.engine.shaker.processor.factory.ComponentFactory;
import com.datasphere.engine.shaker.processor.instance.Component;
import com.datasphere.engine.shaker.processor.instance.model.ComponentInstance;
import com.datasphere.engine.shaker.processor.instance.service.ComponentInstanceService;

/**
 * 组件服务接口实现类
 */
@Service
public class ComponentService extends BaseService {
	@Autowired
	private ComponentInstanceService componentInstanceService;
	@Autowired
	private ComponentFactory componentFactory;
	
	public Component getById(String componentId){
		ComponentInstance instance=componentInstanceService.getComponentInstanceById(componentId);
		return componentFactory.getInstance(instance, this);
	}
	
	public List<Component> getAllComponentsWithPanel(String panelId){
		List<ComponentInstance> instances = componentInstanceService.getAllComponentInstancesWithPanel(panelId);
		return assembleComponentList(instances);
	}
	
	public List<Component> getAllComponentsFromCompIdWithPanel(String panelId,String fromComponentId){
		List<ComponentInstance> instances=componentInstanceService.getAllComponentInstancesFromCompInsIdWithPanel(panelId,fromComponentId);
		return assembleComponentList(instances);
	}
	
	public List<Component> getAllComponentsToCompIdWithPanel(String panelId,String toComponentId){
		List<ComponentInstance> instances=componentInstanceService.getAllComponentInstancesToCompInsIdWithPanel(panelId, toComponentId);
		return assembleComponentList(instances);
	}
	
	public List<Component> getBeginComponents(String panelId){
		List<ComponentInstance> instances = componentInstanceService.getBeginComponentInstances(panelId);
		return assembleComponentList(instances);
	}
	
	public List<Component> getBeginComponents(String panelId,String toComponentId){
		List<ComponentInstance> instances=componentInstanceService.getBeginComponentInstances(panelId, toComponentId);
		return assembleComponentList(instances);
	}
	
	public List<Component> getEndComponents(String panelId){
		List<ComponentInstance> instances = componentInstanceService.getEndComponentInstances(panelId);
		return assembleComponentList(instances);
	}
	
	public List<Component> getEndComponents(String panelId,String fromComponentId){
		List<ComponentInstance> instances=componentInstanceService.getEndComponentInstances(panelId, fromComponentId);
		return assembleComponentList(instances);
	}


	private List<Component> assembleComponentList(List<ComponentInstance> componentInstances){
		List<Component> components = new ArrayList<>();
		for(ComponentInstance componentInstance:componentInstances){
			Component component = componentFactory.getInstance(componentInstance, this);
			components.add(component);
		}
		return components;
	}

}
