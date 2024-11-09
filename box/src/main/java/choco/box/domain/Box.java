package choco.box.domain;

public class Box {
    private Long boxId;
    private String boxName;

    public Box(Long boxId, String boxName) {
        this.boxId = boxId;
        this.boxName = boxName;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }
}
