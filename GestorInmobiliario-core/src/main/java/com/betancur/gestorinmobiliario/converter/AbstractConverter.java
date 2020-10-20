/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.betancur.gestorinmobiliario.converter;


import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Ariel
 */

public abstract class AbstractConverter <ENTITY,DTO> {

	public abstract ENTITY fromDto(DTO dto);
	
	public abstract DTO fromEntity(ENTITY entity);
	
	public List<ENTITY> fromDto(List<DTO> dtos){
		if(dtos == null) return null;
		return dtos.stream().map(dto -> fromDto(dto)).collect(Collectors.toList());
	}
	
	public List<DTO> fromEntity(List<ENTITY> entities){
		if(entities == null) return null;
		return entities.stream().map(entity -> fromEntity(entity)).collect(Collectors.toList());
	}
}
