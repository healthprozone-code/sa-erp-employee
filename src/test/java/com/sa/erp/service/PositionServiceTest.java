package com.sa.erp.service;

import com.sa.erp.entities.Position;
import com.sa.erp.services.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    private Position auxTestPosition;

    private String auxTestID;

    private LocalDate currentDate;

    @BeforeEach
    public void initialize(){
        this.auxTestPosition = this.positionService.savePosition(new Position(null, "Dev", true, LocalDate.now(),LocalDate.now()));
        this.auxTestID = this.auxTestPosition.getId();
        this.currentDate = LocalDate.now();
    }

    @Test
    public void findPositionById(){
        Optional<Position> positionFinded = this.positionService.getPositionById(this.auxTestID);
        assertTrue(positionFinded.isPresent());
        assertNotNull(positionFinded.get().getId());
        assertEquals(positionFinded.get().getId(), this.auxTestID);
    }

    @Test
    public void findAllPoitions(){
        List<Position> positionList = this.positionService.getAllPositions();
        assertTrue(!positionList.isEmpty());
        assertTrue(positionList.size()>0);
    }

    @Test
    public void addPosition(){
        Position aux = new Position(null, "Dev", true, LocalDate.of(2022, Month.DECEMBER, 8),LocalDate.of(2022, Month.DECEMBER, 8));
        Position result = this.positionService.savePosition(aux);
        assertEquals(aux.getName(), result.getName());
        assertEquals(aux.isEnable(), result.isEnable());
        assertEquals(this.currentDate, result.getCreateDate());
        assertEquals(this.currentDate, result.getUpdateDate());
        assertNotNull(result.getId());
    }

    @Test
    public void updatePosition(){
        Position aux = this.positionService.getPositionById(this.auxTestID).get();
        aux.setName("Tester");
        aux.setUpdateDate(LocalDate.of(2022, Month.DECEMBER, 8));
        LocalDate currentDate = LocalDate.now();
        aux = this.positionService.updatePosition(aux.getId(), aux);
        assertEquals(aux.getName(), "Tester");
        assertEquals(this.currentDate, aux.getUpdateDate());
    }

    @Test
    public void updatePositionError(){
        Position aux = this.positionService.getPositionById(this.auxTestID).get();
        aux = this.positionService.updatePosition("", aux);
        assertNull(aux);
    }

    @Test
    public void deletePosition(){
        Position aux = this.positionService.getPositionById(this.auxTestID).get();
        aux = this.positionService.deletePosition(aux.getId());
        assertEquals(this.currentDate, aux.getUpdateDate());
        assertFalse(aux.isEnable());
    }

    @Test
    public void deletePositionError(){
        Position aux = this.positionService.deletePosition("");
        assertNull(aux);
    }


}
