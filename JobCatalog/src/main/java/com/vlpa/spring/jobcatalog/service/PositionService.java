package com.vlpa.spring.jobcatalog.service;

import com.vlpa.spring.jobcatalog.model.Position;

import java.util.List;

public interface PositionService {

    public void addPosition(Position position);
    public void updatePosition(Position position);
    public void removePosition(int id);
    public Position getPositionById(int id);
    public List<Position> listPositions();
}
