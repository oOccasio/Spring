package choco.box.service;

import choco.box.domain.Box;
import choco.box.repository.BoxRepository;

public class BoxService {

    private final BoxRepository boxRepository = new BoxRepository();

   public void make(Box box){
        boxRepository.save(box);
    }
}
