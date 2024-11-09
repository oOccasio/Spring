package choco.box.repository;

import choco.box.domain.Box;

import java.util.HashMap;
import java.util.Map;

public class BoxRepository {

    private static Map<Long,Box> store = new HashMap<>();


    public void save(Box box){
            store.put(box.getBoxId(),box);
    }

}
